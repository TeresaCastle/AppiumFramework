package App;

import java.net.MalformedURLException;
import com.framework.base.Base;
import com.framework.base.Common.Direction;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

//This class contains random tests executed from the landing page
public class LandingPageTests extends Base {

    //TODO test screenshots

    @Test (groups= {"smoke"}, enabled = true, dataProvider= "ClickTestData", description = "Both buttons on the home screen lead to the correct page")
    public void checkButtonFunction(String buttonName, String desc, String buttonText, String message) throws InterruptedException, MalformedURLException {
        //Searching for an element and clicking on it, based on the xpath which is partially supplied by the data provider
        WebElement element = driver.findElementByXPath("//android.widget.TextView[@content-desc='"+buttonName+"']");
        element.click();
        //Checking that the existing button text is the same as the expected text, and supplying a message for the Assertion
        //In this example, the message = "Checking Accessibility button text"
        //If the assertion fails it will print "Assertion: Checking animation button text <FAILED> "
        softAssert.assertEquals(android.getButtonText(desc), buttonText, message);
        //Pressing the "back" button on the device to return to the home screen
        driver.navigate().back();
        //Causes an exception to be thrown if any assertions fail, failing the test and printing information on the failure
        //This should be included in any method that contains assertions. SoftAssert is preferred over regular assertions
        softAssert.assertAll();
    }

    @DataProvider(name="ClickTestData")
    public static Object[][] getButton()
    {
        //Below you can see multiple data sets, which will run through the test multiple times
        Object[][] buttons=new Object[][]
                {
                        {"Accessibility", "Accessibility Node Provider", "Accessibility Node Provider", "Checking accessibility button text"},
                        {"Animation", "Bouncing Balls", "Bouncing Balls", "Checking animation button text"}
                };
        //Returning the data object
        return buttons;
    }

    // Below is another example of how a data provider could be structured
    @DataProvider(name="ClickTestData2")
    public static Object[][] getButton2()
    {
        Object[][] buttons=new Object[2][4]; //This shows the object as containing 2 rows and 4 columns

        // The first set of  data
        buttons[0][0]="Accessibility";
        buttons[0][1]="Accessibility Node Provider";
        buttons[0][2]="Accessibility Node Provider";
        buttons[0][3]="Checking accessibility button text";

        // The second set of data
        buttons[1][0]="Animation";
        buttons[1][1]="Bouncing Balls";
        buttons[1][2]="Bouncing Balls";
        buttons[1][3]="Checking animation button text";

        //Returning the data object
        return buttons;
    }

    //An example of using automation to fill text fields in a text entry dialog box
    @Test (groups= {"smoke"}, enabled = true, description = "We can enter text in the Text Entry Dialog")
    public void fillTextEntryDialog(){

        //Searching for an element and clicking on it, based on the page object
        android.getAppButton().click();
        android.getAlertDialogsButton().click();
        android.getTextEntryDialogButton().click();
        android.getUsernameField().click();

        //Filling the text field with a string
        android.getUsernameField().sendKeys("Username");
        // Checking that the element now contains the entered test
        softAssert.assertEquals(android.getUsernameField().getText(), "Username", "The username field is no longer empty");

        //Clicking the "ok" button to close the dialog
        android.getOkButton().click();

        //Returning to the landing page
        driver.navigate().back();
        driver.navigate().back();

        softAssert.assertAll();
    }
}

