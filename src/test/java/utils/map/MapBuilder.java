package utils.map;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MapBuilder {

    public static <T> T getInstance(WebDriver driver, Class<T> clazz) {

        T genericPage = PageFactory.initElements(driver, clazz);
        Map page = (Map) genericPage;
        page.setWebDriver(driver);
        return (T) page;

    }

    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {

        try {
            try {

                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);

            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);

        }

    }

}