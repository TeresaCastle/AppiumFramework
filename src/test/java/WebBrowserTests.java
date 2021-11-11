import com.framework.base.Base;
import org.testng.annotations.Test;
import java.io.IOException;

public class WebBrowserTests extends Base {

    //The following tests show an example of parameterization, scrolling, use of page objects and use of data provider
    @Test
    public void webTest() throws InterruptedException, IOException {
        //Navigating to a specific URL
        driver().get("https://linkedin.com");
        //TODO Parameterize URL
        //I have inserted a sleep here just for visual demonstration, it would be removed for efficiency down the line
        Thread.sleep(1000);
        //Scrolling up by 250 pixels
        common.scroll(250);
        //I have inserted a sleep here just for visual demonstration, it would be removed for efficiency down the line
        Thread.sleep(2000);
        //Scrolling down by 250 pixels
        common.scroll(-250);
        //I have inserted a sleep here just for visual demonstration, it would be removed for efficiency down the line
        Thread.sleep(2000);

        //Using an assertion to check that the message is displayed
        softAssert.assertTrue(objects.getWelcomeMessage().isDisplayed(), "The welcome message is displayed on the landing page");
        //Causes an exception to be thrown if any assertions fail, failing the test and printing information on the failure
        //This should be included in any method that contains assertions. SoftAssert is preferred over regular assertions
        softAssert.assertAll();
    }
    //TODO add page object
    //TODO example testing
    //TODO Parameterize URL?
    //TODO AfterSuite? class that closes the browser when tests complete
}
