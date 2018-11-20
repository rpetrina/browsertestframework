package utils.map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameworkMap extends Map {

    @FindBy(linkText = "Find Your Rate")
    private WebElement findmyrate;

    @FindBy(className = "select-bar")
    private WebElement selectbar;

    @FindBy(className = "Select-arrow")
    private WebElement dropdownarrow;

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(className = "is-button-next")
    private WebElement continuebutton;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "button--neutral")
    private WebElement getmyratebutton;

    @FindBy(className = "welcome")
    private WebElement welcomesection;

    public WebElement findmyrate() {
        return findmyrate;
    }

    public WebElement selectbar() {
        return selectbar;
    }

    public WebElement dropdownarrow() {
        return dropdownarrow;
    }

    public WebElement firstname() {
        return firstName;
    }

    public WebElement lastname() {
        return lastName;
    }

    public WebElement continuebutton() {
        return continuebutton;
    }

    public WebElement email() {
        return email;
    }

    public WebElement phoneNumber() {
        return phoneNumber;
    }

    public WebElement password() {
        return password;
    }

    public WebElement welcomesection() {
        return welcomesection;
    }

    public WebElement getmyratebutton() {
        return getmyratebutton;
    }

}