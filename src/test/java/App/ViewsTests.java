package App;

import com.framework.base.Base;
import com.framework.base.Common;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ViewsTests extends Base {
    //Below is an example of a test that uses context switching to automate a hybrid app
    @Test(groups= {"smoke"}, enabled = true, description = "We are able to switch from native views to web views")
    public void checkWebView() throws InterruptedException, MalformedURLException {

        //Clicking on the "Views" button from the landing page
        android.getViewsButton().click();

        //Swiping the screen twice to scroll
        common.swipeScreen(Common.Direction.UP);
        common.swipeScreen(Common.Direction.UP);

        //Clicking on the "WebView" button
        android.getWebViewButton().click();

        //Switching to WebView context so that we can interact in web view
        common.switchToWebContext();

        //Clicking the hyperlink in WebView
        android.getHyperlink().click();

        //Assertion to test that the correct text appears on the following Web View
        softAssert.assertEquals(android.getWebViewTextElement().getText(), "I am some other page content", "Checking that the web view text is correct");

        //Navigating back to the app
        driver.navigate().back();
        //Switching back to Native App Views for app interaction
        common.switchToNativeContext();
        //Navigating back to the landing page to reset for the next tests
        driver.navigate().back();

        //Causes an exception to be thrown if any assertions fail, failing the test and printing information on the failure
        //This should be included in any method that contains assertions. SoftAssert is preferred over regular assertions
        softAssert.assertAll();
    }
}
