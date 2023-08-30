package com.thinesh.hooks;

import com.thinesh.utilities.CommonUtils;
import com.thinesh.utilities.Constants;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHooks {

    public static String scenarioName = null;

    public static String getScenarioName() {
        return scenarioName;
    }

    private static final Logger LOGGER = LogManager.getLogger(WebDriverHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        scenarioName = scenario.getName();
        LOGGER.info("Execution Started");
        LOGGER.info("Instantiation of CommonUtils");
        LOGGER.info("Loading the properties file");
        CommonUtils.getInstance().loadProperties();
        LOGGER.info("Checking the Driver is NULL or NOT?");
        if (DriverManager.getDriver() == null) {
            LOGGER.info("Driver is NULL. Instantiating itr");
            DriverManager.launchBrowser();
            CommonUtils.getInstance().iniitWebElements();
        }
    }

    @After
    public void tearDown() {
        CommonUtils.getInstance().takeScreenshot();
        //DriverManager.getDriver().quit();
    }

    @AfterStep
    public void attach_screenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, scenarioName, "errorScreen");
        }
    }




}
