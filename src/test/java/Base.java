import utils.map.*;
import utils.driver.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    static String APP_URL = System.getenv("APP_URL");
    static String APP_API_URL = System.getenv("APP_API_URL");
    static String APP_USER = System.getenv("APP_USER");
    static String APP_PW = System.getenv("APP_PW");
    static WebDriver webDriver = DriverFactory.getDriver();
    static WebDriverWait wait = new WebDriverWait(webDriver, 10);
    static FrameworkMap map = MapBuilder.getInstance(webDriver, FrameworkMap.class);
}
