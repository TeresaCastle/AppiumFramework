package pageObjects;

import com.Framework.IOSBase;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.Framework.IOSBase.waitForElement;

public class iOSPageObjects extends IOSBase {
    public static IOSDriver driver;

    static {
        try {
            driver = DesiredCapabilities("UIKitCatalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebElement getButtonsButton() throws MalformedURLException {
        String xpath = "//XCUIElementTypeApplication[@name='UIKitCatalog']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther";
        waitForElement(driver.findElementByXPath(xpath), 10, 1);
        WebElement homeButton = driver.findElementByXPath(xpath);
        return homeButton;
    }
}
