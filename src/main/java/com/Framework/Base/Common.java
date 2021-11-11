package com.framework.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Common extends Base {

    //Capturing a screenshot and saving it in the test-output/Screenshots folder in the project. It is also logged to the Emailable-Report in test-output
    public static void getScreenshot(String testName) throws IOException {
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
        //TODO test screenshots for web
    }

    //Waiting for an element to appear with a defined timeout and polling time
    public static WebElement waitForElement(WebElement element, int timeOutSec, int pollingSec) {
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

    //Scrolls based on a defined number of pixels. Positive numbers scroll down, negative numbers scroll up.
    // 250 pixels is a good place to start if you are uncertain
    public static void scroll(int pixels){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixels+")", "");
    }
}
