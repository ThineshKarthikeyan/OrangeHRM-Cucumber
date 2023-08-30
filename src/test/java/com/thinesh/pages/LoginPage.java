package com.thinesh.pages;

import com.thinesh.utilities.CommonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    private static LoginPage loginPage;

    private LoginPage() {

    }

    public static LoginPage getInstance() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    @FindBy(name = "username")
    private WebElement USERNAME;


    @FindBy(name = "password")
    private WebElement PASSWORD;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement LOGIN_BTN;

//    public WebElement getUSERNAME() {
//        return USERNAME;
//    }
//
//    public WebElement getPASSWORD() {
//        return PASSWORD;
//    }
//
//    public WebElement getLOGIN_BTN() {
//        return LOGIN_BTN;
//    }

    public void enterUsername(String username) {
        CommonUtils.getInstance().highlight(USERNAME);
        USERNAME.sendKeys(username);
    }

    public void enterPassword(String password) {
        CommonUtils.getInstance().highlight(PASSWORD);
        PASSWORD.sendKeys(password);
    }

    public void clickLoginBtn() {
        CommonUtils.getInstance().highlight(LOGIN_BTN);
        LOGIN_BTN.click();
    }
}
