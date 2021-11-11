import com.framework.base.Base;
import org.testng.annotations.Test;
import java.io.IOException;

public class WebBrowserTests extends Base {

    @Test
    public void webTest() throws InterruptedException, IOException {
        driver().get("https://linkedin.com");
        //TODO Parameterize URL
        Thread.sleep(5000);
    }
    //TODO add page object
    //TODO example testing
    //TODO AfterSuite? class that closes the browser when tests complete
}
