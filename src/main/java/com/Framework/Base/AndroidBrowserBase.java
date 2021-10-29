package com.Framework.Base;

import com.Framework.Listeners.AssertionLogging;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pageObjects.Browser.AndroidPageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class AndroidBrowserBase {
    public static AndroidPageObjects chrome = new AndroidPageObjects();
    public static AssertionLogging softAssert = new AssertionLogging();

    public static AppiumDriver driver;

    {
        try {
            driver = capabilities();
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
        String device = (String) prop.get("androidDevice");
        //Telling Appium which device to use
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        //Telling Appium that we are using Android Studio UI Automator to access the browser and run test automation on the app
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        //Providing the capability name we are setting (Chrome Driver Executable ) as well as the file path to the chrome driver
        capabilities.setCapability("chromedriverExecutable","/Users/madison.vincent/IdeaProjects/DemoFramework/src/main/resources/chromedriver");
        //Providing platform name
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //Telling Appium not to re-install the browser app every time we test
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        //Getting the mobile browser property from Global Properties and saving it to a string
        String mobileBrowser =(String) prop.get("mobileBrowser");
        //Telling Appium which browser we are using for testing
        if (Objects.equals(mobileBrowser, "Samsung")) {
            //This sets up Samsung Internet Browser
            capabilities.setCapability("appPackage", "com.sec.android.app.sbrowser");
            capabilities.setCapability("appActivity", "com.sec.android.app.sbrowser.SBrowserMainActivity");
        } else if (Objects.equals(mobileBrowser, "Chrome")){
            //This sets up Chrome
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        }
        //TODO testing on samsung internet capabilities, uncertain if driver capabilities are the same...

        //Setting up the driver
        String address=(String) prop.get("IP");
        driver = new AppiumDriver(new URL(address), capabilities);
        return driver;
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
