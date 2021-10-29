package com.Framework.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class WebBrowserBase {

    public static WebDriver driver() throws IOException {

        // Creates a FileInputStream by opening a connection to an actual file, the file named by the path name in the file system
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/Framework/global.properties");
        // Creates an empty property list with no default values.
        Properties prop = new Properties();
        //Reads a property list (key and element pairs) from the input stream.
        prop.load(fis);

        //Initializing the web driver
        WebDriver driver = null;

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
        //TODO separate testng.xml for webdriver versus mobile
        //returning the driver for use in testing
        return driver;
    }
}
