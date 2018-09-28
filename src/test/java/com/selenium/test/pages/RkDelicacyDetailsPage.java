package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class RkDelicacyDetailsPage extends BasePage {

    private static final String PAGE_URL = "http://localhost:4200/receipe-details/Belgium";

    @FindBy(css = ".recipe-list > mat-card")
    public List<WebElement> receipeListElement;

    @FindBy(css = "app-receipe-details .header h2")
    public WebElement detailsTitleElement;

    @FindBy(css = ".receipedetails")
    public WebElement detailsContentElement;

    public RkDelicacyDetailsPage(){
        super(true);
    }

    public RkDelicacyDetailsPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    protected void openPage() {
        //do nothing
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return detailsTitleElement.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
