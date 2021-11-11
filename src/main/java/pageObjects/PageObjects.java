package pageObjects;

import com.framework.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class PageObjects extends Base {
    public static WebElement getWelcomeMessage() {
        String xpath = "//h1[contains(text(), 'Welcome to your professional community')]";
        common.waitForElement(driver.findElement(By.xpath(xpath)), 10, 1);
        WebElement welcomeMessage = driver.findElement(By.xpath(xpath));
        return welcomeMessage;
    }
}
