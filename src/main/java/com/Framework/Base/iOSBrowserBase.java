package com.Framework.Base;

import com.Framework.Listeners.AssertionLogging;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import pageObjects.Browser.iOSPageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class iOSBrowserBase {
    public static AppiumDriverLocalService service;
    public static AssertionLogging softAssert = new AssertionLogging();
    public static iOSPageObjects safari = new iOSPageObjects();

    public static AppiumDriver driver;

    {
        try {
            driver = iOSBrowserBase.capabilities();
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

        // Initializing Desired Capabilities, which is what we utilize to set properties for our testing.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Setting all the properties below
        // Retrieving the device name from Global Properties and storing it as a property
        String device = (String) prop.get("iOSDevice");
        //Telling Appium which device to use
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        //Telling Appium that we are using Android Studio UI Automator to access the browser and run test automation on the app
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
        //Telling Appium which browser we are using for testing
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        //Providing platform name and version (Maybe not needed?)
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        //Getting the UDID value from global properties and storing it as a property
        String udid =(String) prop.get("UDID");
        //Providing the UDID for the device/simulator as required
        capabilities.setCapability(MobileCapabilityType.UDID, udid);

        //Setting up the driver
        String address =(String) prop.get("IP");
        URL url = new URL(address);
        AppiumDriver driver = new AppiumDriver(url, capabilities);
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
    }

}
