import com.Framework.Base.iOSBase;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.*;
import pageObjects.IOS.AppHomePageObjects;
import java.io.IOException;

@Listeners(com.Framework.Listeners.Listeners.class)

public class iOSTests extends iOSBase {

    AppHomePageObjects ios = new AppHomePageObjects();
    AppiumDriver driver;

    {
        try {
            driver = iOSBase.capabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test (description = "Checking the text and function of the Alert Views button")
    public void clickAlertViews() throws IOException, InterruptedException {

    //TODO call the driver from the base as it isn't needed her or should at least be parameterized
    Thread.sleep(3000);
    //Finding an element based on ID and clicking on it
    driver.findElementByAccessibilityId("Alert Views").click();
    driver.navigate().back();
    softAssert.assertEquals(driver.findElementByAccessibilityId("Alert Views").getText(), "Alert View");
    softAssert.assertAll();
    }
    //TODO: Everything - assertion, examples of ios interactions, test dependecy, priorities, xpath etc
}
