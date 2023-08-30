package com.thinesh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DirectoryPage {

    private static DirectoryPage directoryPage;

    private DirectoryPage() {

    }

    public static DirectoryPage getInstance() {
        if (directoryPage == null) {
            directoryPage = new DirectoryPage();
        }
        return directoryPage;
    }

    @FindBy(linkText = "Directory")
    private WebElement JOB_TITLE;

    public WebElement getJOB_TITLE() {
        return JOB_TITLE;
    }
}
