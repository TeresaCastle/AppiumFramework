import org.testng.annotations.*;
import java.io.IOException;

public class iOSTests extends com.framework.base.Base {

    @Test (description = "Checking the text and function of the Alert Views button")
    public void clickAlertViews() throws IOException, InterruptedException {
    Thread.sleep(3000);
    //Finding an element based on ID and clicking on it
    driver.findElementByAccessibilityId("Alert Views").click();
    driver.navigate().back();
    softAssert.assertEquals(driver.findElementByAccessibilityId("Alert Views").getText(), "Alert View");
    softAssert.assertAll();
    }
}
