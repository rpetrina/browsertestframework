import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GetRate extends Base {

    private Boolean existinguser = false;
    private Cookie iceiceab = new Cookie("iceiceab_disabled", "true");

    @Step("Go to the LH Page")
    public void gotoLHpage() {
        webDriver.get("http://www.lh-stage.com/");
        webDriver.manage().addCookie(iceiceab);
        webDriver.get("http://www.lh-stage.com/");
    }

    @Step("Click the Get My Rate button")
    public void clickgetmyrate() {
        wait.until(ExpectedConditions.visibilityOf(map.findmyrate())).click();
    }

    @Step("Select number of properties sold <num_prop_sold>")
    public void selectpropertiessold(String arg0) {

        WebElement selectbar = map.selectbar();
        wait.until(ExpectedConditions.elementToBeClickable(selectbar));
        for (WebElement element : selectbar.findElements(By.xpath("//*/label"))) {
            if (element.getText().equals(arg0)) {
                element.click();
                break;
            }
        }
    }

    @Step("Select stage of purchase <purchasestage>")
    public void selectpurchasestage(String arg0) {
        findindropdown(arg0);
    }

    @Step("Select state <state>")
    public void selectpurchasestate(String arg0) {
        findindropdown(arg0);
    }

    public void findindropdown(String arg0) {
        wait.until(ExpectedConditions.visibilityOf(map.dropdownarrow()));

        webDriver.findElement(By.className("Select-control")).click();

        wait.until(ExpectedConditions.elementToBeClickable((By.className("Select-option"))));
        List<WebElement> options = webDriver.findElements(By.className("Select-option"));

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(arg0)) {
                option.click();
                break;
            }
        }
    }

    @Step("Fill in personal information First Name <firstname>, Last Name <lastname>, Email <email>, Phone <phone>")
    public void filloutpersonalinfo(String firstname, String lastname, String email, String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(map.firstname()));
        map.firstname().sendKeys(firstname);
        map.lastname().sendKeys(lastname);
        map.continuebutton().click();
        wait.until(ExpectedConditions.elementToBeClickable(map.email()));
        map.email().sendKeys(email);
        map.continuebutton().click();
        wait.until(ExpectedConditions.visibilityOf(map.continuebutton()));
        WebDriverWait waitforphonenumber = new WebDriverWait(webDriver, 5);
        try {
            waitforphonenumber.until(ExpectedConditions.elementToBeClickable(map.phoneNumber()));
        } catch (TimeoutException e) {
            Gauge.writeMessage("User already has an account. Logging in...");
            existinguser = true;
            wait.until(ExpectedConditions.elementToBeClickable(map.password()));
            map.password().sendKeys("abcdefg2");
            map.continuebutton().click();
        }
        if (!existinguser) {
            wait.until(ExpectedConditions.elementToBeClickable(map.phoneNumber()));
            map.phoneNumber().sendKeys(phone);
            map.continuebutton().click();
            wait.until(ExpectedConditions.elementToBeClickable(map.password()));
            map.password().sendKeys("abcdefg2");
            map.continuebutton().click();
        }

    }

    @Step("Validate that <Get your personalized rate in 3 minutes.> and the get my rate button are present")
    public void validatetextandbuttonarepresent(String arg0) {
        WebDriverWait waitforconfirmationpage = new WebDriverWait(webDriver, 30);
        if (!existinguser) {
            wait.until(ExpectedConditions.elementToBeClickable(map.continuebutton())).click();
        }

        waitforconfirmationpage.until((ExpectedConditions.visibilityOf(map.welcomesection())));
        waitforconfirmationpage.until(ExpectedConditions.visibilityOf(map.getmyratebutton()));
        Assert.assertTrue(map.welcomesection().findElement(By.xpath("//*/h1")).getText().equalsIgnoreCase(arg0));
        Assert.assertTrue(map.getmyratebutton().isDisplayed());
    }
    
}