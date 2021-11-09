import com.Framework.Base.WebBrowserBase;
import org.testng.annotations.Test;
import java.io.IOException;

public class WebBrowserTests extends WebBrowserBase {

    @Test
    public void webTest() throws InterruptedException, IOException {
        driver().get("https://linkedin.com");
        Thread.sleep(5000);
    }
    //TODO example testing
}
