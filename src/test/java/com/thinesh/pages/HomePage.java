package com.thinesh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    private static HomePage homePage;

    private HomePage() {

    }

    public static HomePage getInstance() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    @FindBy(linkText = "Directory")
    private WebElement DIRECTORY;

//    public WebElement getDIRECTORY() {
//        return DIRECTORY;
//    }

    public void clickDirectory() {
        DIRECTORY.click();
    }
}
