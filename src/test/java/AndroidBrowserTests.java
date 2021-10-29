import com.Framework.Base.AndroidBrowserBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class AndroidBrowserTests extends AndroidBrowserBase {

    @Test (dataProvider= "BrowserTestData")
    public void checkWelcomeMessage(String expected) throws IOException, InterruptedException {
        //TODO test description and general consistency
        //Allowing the browser to open
        Thread.sleep(5000);
        // Telling the driver to navigate to a specific URL
       driver.get("https://linkedin.com");
       //TODO: Context switching when the app supplies it's own URL or redirects to a browser
        // Using page objects to identify an element and click on that element
       chrome.getHomeButton().click();
        //Comparing the text of that element to the expected text, and providing a message that describes the assertion
        softAssert.assertEquals(chrome.getWelcomeMessage(), expected, "Checking the welcome message");

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
