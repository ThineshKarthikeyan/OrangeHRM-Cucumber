package com.thinesh.hooks;

import com.thinesh.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver = null;

    public static void launchBrowser() {
        switch (Constants.BROWSER) {
            case "chrome":
                LOGGER.info("Launching " + Constants.BROWSER);
                driver = new ChromeDriver();
                break;
            case "firefox":
                LOGGER.info("Launching " + Constants.BROWSER);
                driver = new FirefoxDriver();
                break;
            case "edge":
                LOGGER.info("Launching " + Constants.BROWSER);
                driver = new EdgeDriver();
                break;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
