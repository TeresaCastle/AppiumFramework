import com.Framework.Base.iOSBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.*;
import java.io.IOException;

public class iOSTests extends iOSBase {

    @Test (description = "Checking the text and function of the Alert Views button")
    public void clickAlertViews() throws IOException, InterruptedException {
    Thread.sleep(3000);
    //Finding an element based on ID and clicking on it
    driver.findElementByAccessibilityId("Alert Views").click();
    driver.navigate().back();
    softAssert.assertEquals(driver.findElementByAccessibilityId("Alert Views").getText(), "Alert View");
    softAssert.assertAll();
    }
    //TODO: Everything - assertion, examples of ios interactions, test dependecy, priorities, xpath etc
}
