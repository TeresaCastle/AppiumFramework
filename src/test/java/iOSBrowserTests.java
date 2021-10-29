import com.Framework.Base.iOSBrowserBase;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Browser.iOSPageObjects;

import java.io.IOException;

public class iOSBrowserTests extends iOSBrowserBase {

//    static AppiumDriver driver;
//
//    static {
//        try {
//            driver = iOSBrowserBase.capabilities();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    //TODO why must this be initialized again

    @Test (dataProvider= "BrowserTestData")
    public void checkWelcomeMessage(String expected) throws IOException, InterruptedException {
//TODO test description and general consistency
        // Telling the driver to navigate to a specific URL=
       driver.get("https://linkedin.com");
       //TODO: Context switching when the app supplies it's own URL or redirects to a browser
        // Using page objects to identify an element and click on that element
       safari.getHomeButton().click();
        //Comparing the text of that element to the expected text, and providing a message that describes the assertion
        softAssert.assertEquals(safari.getWelcomeMessage(), expected, "Checking the welcome message");

        //Causes an exception to be thrown if any assertions fail, failing the test and printing information on the failure
        //This should be included in any method that contains assertions. SoftAssert is preferred over regular assertions
        softAssert.assertAll();
    }

    @DataProvider(name="BrowserTestData")
    public static Object[][] getWelcomeMessage()
    {
        //You can store many values here as an array, or just one
        Object expected[][]=new Object[][]
                {
                        {"Welcome to your professional community"}
                };
        return expected;
    }
}
