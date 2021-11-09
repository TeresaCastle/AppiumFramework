package pageObjects.Android;

import com.Framework.Base.AndroidBase;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class AppHomePageObjects extends AndroidBase {



	public static String getButtonText(String desc) throws MalformedURLException {
		//Providing the xpath as a string
		String xpath = "//android.widget.TextView[@text='"+desc+"']";
		//Waiting for the presence of the desired element
		waitForElement(driver.findElementByXPath(xpath), 10, 1);
		//Creating a Web Element object
		WebElement element = driver.findElementByXPath(xpath);
		//Getting the text from the specified webelemenmt
		String text = element.getText();
		//Returning the text for use in testing
		return text;
	}

	public static WebElement getViewsButton(){
		//Providing the xpath as a string
		String xpath = "//android.widget.TextView[@text='Views']";
		//Waiting for the presence of the desired element
		waitForElement(driver.findElementByXPath(xpath), 10, 1);
		//Creating a Web Element object
		WebElement element = driver.findElementByXPath(xpath);
		//Returning the element for use in testing
		return element;
	}

	public static WebElement getWebViewButton(){
		//Providing the xpath as a string
		String xpath = "//android.widget.TextView[@text='WebView']";
		//Waiting for the presence of the desired element
		waitForElement(driver.findElementByXPath(xpath), 10, 1);
		//Creating a Web Element object
		WebElement element = driver.findElementByXPath(xpath);
		//Returning the element for use in testing
		return element;
	}

	public static WebElement getHyperlink(){
		//Providing the xpath as a string
		String xpath = "//*[@id=\"i am a link\"]";
		//Waiting for the presence of the desired element
		waitForElement(driver.findElementByXPath(xpath), 10, 1);
		//Creating a Web Element object
		WebElement element = driver.findElementByXPath(xpath);
		//Returning the element for use in testing
		return element;
	}

	public static WebElement getWebViewTextElement(){
		//Providing the xpath as a string
		String xpath = "/html/body[contains(text(),'I am some other page content')]";
		//Waiting for the presence of the desired element
		waitForElement(driver.findElementByXPath(xpath), 10, 1);
		//Creating a Web Element object
		WebElement element = driver.findElementByXPath(xpath);
		//Returning the element for use in testing
		return element;
	}

}
