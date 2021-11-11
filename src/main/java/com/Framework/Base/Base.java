package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;
import pageObjects.PageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Base {

    //The driver can be called directly from any class that extends Base
    public static WebDriver driver;

    // The below objects can be used in your code to access methods from the respective Classes from any Class that extends Base.
// (e.g. assertions, page objects and commonly used methods)
    public static Common common = new Common();
    public static SoftAssert softAssert = new SoftAssert();
    public static PageObjects objects = new PageObjects();

    //Setting up the driver
    public static WebDriver driver() throws IOException {

        // Creates a FileInputStream by opening a connection to an actual file, the file named by the path name in the file system
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/Framework/global.properties");
        // Creates an empty property list with no default values.
        Properties prop = new Properties();
        //Reads a property list (key and element pairs) from the input stream.
        prop.load(fis);

        //Getting values for the driver property name and driver directory from Global Properties
        String property = (String) prop.get("property");
        String driverDir = (String) prop.get("driverDir");

        //The below checks the Global Properties file (Webdriver properties) and initializes the driver accordingly
        //If the Global Property is not correctly set to one of the below values, the driver will be null
        if (Objects.equals(property, "webdriver.chrome.driver")) {
            //Setting the property name and location for the chrome driver, and then creating the driver
            System.setProperty(property, driverDir);
            driver = new ChromeDriver();
        } else if (Objects.equals(property, "webdriver.gecko.driver")) {
            System.setProperty(property, driverDir);
            driver = new FirefoxDriver();
            } else if (Objects.equals(property, "webdriver.safari.driver")) {
                //Safari does not require a driver so we don't set the property and location for this browser, we only initialize the driver
                driver = new SafariDriver();
                } else if (Objects.equals(property, "webdriver.edge.driver")){
                    System.setProperty(property, driverDir);
                    driver = new EdgeDriver();
        }
        //TODO separate testng.xml for webdriver versus mobile, executing multiple browsers at once?
        //returning the driver for use in testing
        return driver;
    }

    // We use this AfterSuite annotation from TestNG to close the browser window once all tests have completed
    @AfterSuite
    public void quitDriver(){
        driver.quit();;
    }
}
