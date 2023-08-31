package com.thinesh.hooks;

import com.thinesh.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static WebDriver driver = null;


    public static void launchBrowser() throws Exception {
        if (Constants.BROWSER.toLowerCase().contains("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            getFirefoxCapabilities(firefoxOptions);
            if (Constants.BROWSER.toLowerCase().contains("firefoxlocal")) {
                if (Constants.BROWSER.toLowerCase().contains("_hl")) {
                    LOGGER.info("Launching headless firefox browser");
                    firefoxOptions.addArguments("-headless");
                }
                LOGGER.info("Launching firefox browser");
                driver = new FirefoxDriver(firefoxOptions);
            }
        } else if (Constants.BROWSER.toLowerCase().contains("chrome")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            ChromeOptions chromeOptions = getChromeOptions(capabilities);
            if (Constants.BROWSER.toLowerCase().contains("chromelocal")) {
                if (Constants.BROWSER.equals("chromelocal_hl")) {
                    LOGGER.info("Launching headless chrome browser");
                    setChromeHeadlessCapabilities(chromeOptions);
                    driver = launchBrowser(capabilities, 0);
                }
                LOGGER.info("Launching chrome browser");
                driver = new ChromeDriver(chromeOptions);
            } else if (Constants.BROWSER.toLowerCase().contains("chromedocker")) {
                ChromeOptions options = new ChromeOptions();
                chromeOptions.merge(capabilities);
                options.setBinary("/opt/chromium/chrome-linux/chrome");
                options.addArguments("--disk-cache-size=500000000");
                options.addArguments("--unsafely-treat-insecure-origin-as-secure=https://wwwist3.dev");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("disable-infobars");
                options.addArguments("--disable-extensions");
                options.addArguments("disable-notifications");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }

        }

//        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//        scenarioContext.addToContext("browserName", caps.getBrowserName());
//        scenarioContext.addToContext("browserName", caps.getBrowserVersion());
//        scenarioContext.addToContext("browserName", caps.getPlatformName());
//
//        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//        System.out.println("Browser version is " + cap.getBrowserVersion());
//        System.out.println(scenarioContext);


    }








    private static WebDriver launchBrowser(DesiredCapabilities capabilities, int attempt) throws Exception {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        System.out.println("++++++++++++++++++++++++");
        if (attempt < 10) {
            try {
                if (Constants.BROWSER.equals("chromelocal_hl")) {
                    driver = new ChromeDriver(options);
                } else {
                    //driver = new RemoteWebDriver(new URL(gridUrl));
                }
            } catch (Exception e) {
                driver = launchBrowser(capabilities, ++attempt);
            }
        } else {
            throw new Exception();
        }
        return driver;

    }

    private static void setChromeHeadlessCapabilities(ChromeOptions chromeOptions) {
        chromeOptions.addArguments("--headless");
        DesiredCapabilities headlessCapabilities = new DesiredCapabilities();
        headlessCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        headlessCapabilities.setCapability("acceptInsecureCerts", true);
    }

    private static void getFirefoxCapabilities(FirefoxOptions options) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("newtwork.proxy.type", Proxy.ProxyType.PAC.ordinal());
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
//        profile.setPreference("browser.download.dir", f.getAbsolutePath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv, application/x-msexcel, application/excel, application/x-excel");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.userWindow", false);
        profile.setPreference("browser.download.manager.showA;ertOnComplete", false);
        profile.setPreference("browser.download.manager.clsoeWhenDone", false);
        //profile.setPreference("browser.download.dir", f.getAbsolutePath());
        profile.setPreference("newtwork.proxy.type", Proxy.ProxyType.PAC.ordinal());
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        options.setProfile(profile);

    }

    private static void getBlueGridChromeCapabilities(DesiredCapabilities capabilities) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disk-cache-size=500000000");
        options.addArguments("--unsafely-treat-insecure-origin-as-secure=https://wwwist3.dev");
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        options.setCapability(CapabilityType.PROXY, proxy);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    private static ChromeOptions getChromeOptions(DesiredCapabilities cap) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(cap);
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        String strDownloadDir = System.getProperty("user.dir");
        String strFilepath = strDownloadDir + "\\target\\downloadsFolder\\";
        chromePrefs.put("download.default_directory", strFilepath);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;


    }

    public static WebDriver getDriver() {
        return driver;
    }
}
