package utils.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.net.MalformedURLException;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() { return driver; }

    Capabilities chromeCapabilities = new ChromeOptions();
    Capabilities firefoxCapabilities = new FirefoxOptions();
    private static String BROWSER = System.getenv("BROWSER");
    private static final String URL = System.getenv("APP_URL");

    @BeforeSuite
    public void setUp() {
        try {
            // Default to Chrome.
            if (BROWSER == null || BROWSER.length() == 0) {
                BROWSER = "Chrome";
            }
            switch (BROWSER.toUpperCase()) {
            case "IE":
                driver = new InternetExplorerDriver();
                break;
            case "FIREFOX":
                RemoteWebDriver firefox = new RemoteWebDriver(new URL("http://browsertestframework_hub_1:4444/wd/hub"), firefoxCapabilities);
                driver = firefox;
                driver.get(URL);
                break;
            default:
                RemoteWebDriver chrome = new RemoteWebDriver(new URL("http://browsertestframework_hub_1:4444/wd/hub"), chromeCapabilities);
                driver = chrome;
                driver.get(URL);
            }

        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
        driver.quit();
    }
    
}