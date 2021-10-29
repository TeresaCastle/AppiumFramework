package pageObjects.IOS;

import com.Framework.Base.iOSBase;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class AppHomePageObjects extends iOSBase {

    public static WebElement getButtonsButton() throws MalformedURLException {
        String xpath = "//XCUIElementTypeApplication[@name='UIKitCatalog']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther";
        waitForElement(driver.findElementByXPath(xpath), 10, 1);
        WebElement homeButton = driver.findElementByXPath(xpath);
        return homeButton;
    }
}
