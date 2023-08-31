package com.thinesh.steps;

import com.thinesh.hooks.DriverManager;
import com.thinesh.pages.LoginPage;
import com.thinesh.utilities.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.Map;

public class LoginSteps {
    private static final Logger LOGGER = LogManager.getLogger(LoginSteps.class);

    @Given("User launch the application")
    public void user_launch_the_application() {
        DriverManager.getDriver().get(Constants.APP_URL);
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("User login with valid credentials")
    public void user_login_with_valid_credentials(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        String username = data.get("username");
        String password = data.get("password");

        LoginPage.getInstance();
        LoginPage.getInstance().enterUsername(username);
        LoginPage.getInstance().enterPassword(password);
        LoginPage.getInstance().clickLoginBtn();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        if (currentUrl.contains("dashboard")) {
            LOGGER.info("User logged in successfully and is on Homepage");
        }

//        HomePage.getInstance().clickDirectory();
//        LOGGER.info("User clicks on the directory option from the Menu");
//        Thread.sleep(2000);

    }

    @Then("User login with invalid credentials")
    public void user_login_with_invalid_credentials(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        String username = data.get("username");
        String password = data.get("password");

        LoginPage.getInstance();
        LoginPage.getInstance().enterUsername(username);
        LoginPage.getInstance().enterPassword(password);
        LoginPage.getInstance().clickLoginBtn();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        if (!currentUrl.contains("dashboard")) {
            LOGGER.info("User unable to login");
        }

    }
}
