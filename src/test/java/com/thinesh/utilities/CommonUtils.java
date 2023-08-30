package com.thinesh.utilities;

import com.thinesh.hooks.DriverManager;
import com.thinesh.hooks.WebDriverHooks;
import com.thinesh.pages.HomePage;
import com.thinesh.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.Properties;

public class CommonUtils {

    private static final Logger LOGGER = LogManager.getLogger(CommonUtils.class);

    private static CommonUtils commonUtils;

    private CommonUtils() {

    }

    public static CommonUtils getInstance() {
        if (commonUtils == null) {
            commonUtils = new CommonUtils();
        }
        return commonUtils;
    }


    public void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Constants.APP_URL = properties.getProperty("APP_URL");
        Constants.BROWSER = properties.getProperty("BROWSER");
        Constants.USERNAME = properties.getProperty("UserName");
        Constants.PASSWORD = properties.getProperty("Password");

    }

    public void iniitWebElements() {
        PageFactory.initElements(DriverManager.getDriver(), LoginPage.getInstance());
        PageFactory.initElements(DriverManager.getDriver(), HomePage.getInstance());
    }

    public void takeScreenshot() {
        File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(WebDriverHooks.getScenarioName() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void highlight(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].setAttribute('style','border: 3px solid red')", webElement);
    }

    public void selectFromDropdown(WebElement dropdown, String howTo, String value) {
        Select select = new Select(dropdown);
        switch (howTo) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            default:
                LOGGER.info("Please provide valid selection and the valid selections or index, value and visible text but you have provided " + howTo);
        }
    }
}
