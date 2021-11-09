package com.Framework.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import com.Framework.Listeners.AssertionLogging;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import pageObjects.IOS.AppHomePageObjects;


public class iOSBase {
    public static AppiumDriverLocalService service;
    public static AssertionLogging softAssert = new AssertionLogging();
    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static AppHomePageObjects ios = new AppHomePageObjects();
    public static AppiumDriver driver;

    {
        try {
            driver = iOSBase.capabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppiumDriver capabilities() throws IOException {

        // Creates a FileInputStream by opening a connection to an actual file, the file named by the path name in the file system
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
        File app = new File(appDir, (String) prop.get("iOSApp"));
        // Retrieving the device name from Global Properties and storing it as a property
        String device = (String) prop.get("iOSDevice");

        //Setting all the properties below
        //Telling Appium which device to use based on the property we set earlier
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        //Getting the UDID value from global properties and storing it as a property
        String udid =(String) prop.get("UDID");
        //Providing the UDID for the device/simulator as required
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        //Telling Appium that we are using XCUI Test via XCode to run test automation on the app
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        //This defines how long Appium should wait for a new command from the client before assuming the client ended the session
        //Set to 0 to disable the timeout, but this isn't recommended as it allows automation sessions to continue indefinitely
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,30);
        //Telling Appium where to find the application for testing
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");

        //Setting up the driver
        String address=(String) prop.get("IP");
        AppiumDriver driver = new AppiumDriver(new URL(address), capabilities);
        return driver;
    }

    public static void swipeScreen(Direction dir) {
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
    }

}
