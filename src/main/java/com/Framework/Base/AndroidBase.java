package com.Framework.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.Framework.Listeners.AssertionLogging;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import pageObjects.Android.AppHomePageObjects;

public class AndroidBase {

    public static AppiumDriverLocalService service;
    public static AppiumDriver driver;
    public static AssertionLogging softAssert = new AssertionLogging();
    public static AppHomePageObjects android = new AppHomePageObjects();

    static {
        try {
            driver = capabilities();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //This is where we set up Appium to run tests. Driver, Automation Environment, Device, App, etc.
    public static AppiumDriver capabilities() throws IOException, InterruptedException {
        // Creates a FileInputStream by opening a connection to an actual file, the file named by the path name in the file system
        //This defines where our Global Properties live, which we will reference in the code that follows
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/Framework/global.properties");
        // Creates an empty property list with no default values.
        Properties prop=new Properties();
        //Reads a property list (key and element pairs) from the input stream.
        prop.load(fis);
        //Retrieving the pathname for the folder containing the app file and storing it as a property
        String directory=(String) prop.get("appDir");
        //Supplying the pathname for the app file as stored previously
        File appDir = new File(directory);
        //Retrieving the App Name from Global Properties and storing it as a property
        File app = new File(appDir, (String) prop.get("androidApp"));
        // Retrieving the device name from Global Properties and storing it as a property
        String device=(String) prop.get("androidDevice");

        // Initializing Desired Capabilities, which is what we utilize to set properties for our testing.
        //Setting all the properties below
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Telling Appium which device to use based on the property we set earlier
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        //Telling Appium that we are using the Android Studio UI Automator to run test automation on the app
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        //Telling Appium where to find the application for testing
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //Providing platform name
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //This defines how long Appium should wait for a new command from the client before assuming the client ended the session
        //Set to 0 to disable the timeout, but this isn't recommended as it allows automation sessions to continue indefinitely
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,30);

        //EXPERIMENTAL and not needed potentially
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

        //Setting up the driver
        String address=(String) prop.get("IP");
        driver = new AppiumDriver(new URL(address), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        //Checking to see if the Appium server is running and returning a true or false value
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public AppiumDriverLocalService startServer() {
        //Checking if the Appium server is running and starting it if it is not
        boolean flag=	checkIfServerIsRunning(4723);
        if(!flag)
        {
            service=AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public AppiumDriverLocalService stopServer() {
        //Checking if the Appium server is running and stopping it if it is
        boolean flag=	checkIfServerIsRunning(4723);
        if(flag)
        {
            service=AppiumDriverLocalService.buildDefaultService();
            service.stop();
        }
        return service;
    }

    public static void getScreenshot(String testName) throws IOException {
        //Capturing a screenshot and saving it in the test-output/Screenshots folder in the project. It is also logged to the Emailable-Report in test-output
        //Take a screenshot and save it as a file
        File scrFile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Copy the screenshot and save it as a file in the project directory
        FileUtils.copyFile(scrFile,new File (System.getProperty("user.dir")+"/test-output/Screenshots/"+testName+".png"));

        // Creates a FileInputStream by opening a connection to an actual file, the file named by the path name in the file system
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/Framework/global.properties");
        // Creates an empty property list with no default values.
        Properties prop=new Properties();
        //Reads a property list (key and element pairs) from the input stream.
        prop.load(fis);
        //Retrieving the project name from Global Properties and storing it as a string
        String project=(String) prop.get("projectName");

        //Adding the screenshot to emailable-report.html in test-output folder.
        //Project string is used to define the image location as a 404 error is returned without this
        Reporter.log("<a href='/"+project+"/test-output/Screenshots/"+testName+".png'> <img src='/"+project+"/test-output/Screenshots/"+testName+".png' height='400' width='200'/> </a>");

        //Logging that a screenshot was captured in test output
        System.out.println("***** Screenshot Captured *****");
    }

    public static WebElement waitForElement(WebElement element, int timeOutSec, int pollingSec) {
        //Waiting for an element to appear with a defined timeout and polling time
        //Setting up the FluentWait
        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOutSec))
                .pollingEvery(Duration.ofMillis(pollingSec));
        //Waiting for the element to be visible and interactable
        for (int i = 0; i < 2; i++) {
            try {
                fWait.until(ExpectedConditions.visibilityOf(element));
                fWait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (Exception e) {
                //Logging information for errors
                System.out.println("Element Not found trying again - " + element.toString().substring(70));
                e.printStackTrace();
            }
        }
        //Returning the element for use in other methods
        return element;
    }//TODO look into making this dynamic and less redundant

    public void swipeToElement(WebElement element1, WebElement element2){
        //TODO add a loop to the below swipeScreen method that checks for the presence of a specified element
    }

    //TODO add partial screen swipe method

    //Performs swipe from the center of screen. Directions can be UP, DOWN, LEFT, and RIGHT
    //Found on appium.io/docs - http://appium.io/docs/en/writing-running-appium/tutorial/swipe-tutorial/
    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; //it's better to avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public boolean switchToWebContext() {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }

    public boolean switchToNativeContext() {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if (context.contains("NATIVE")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }
}

