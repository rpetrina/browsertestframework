package utils.map;

import org.openqa.selenium.WebDriver;

public class Map {

    protected WebDriver driver;
    protected int DEFAULT_TIMEOUT_SECONDS = 30;

    protected void setWebDriver(WebDriver driver) {

        this.driver = driver;

    }

}