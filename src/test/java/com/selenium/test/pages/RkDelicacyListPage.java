package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RkDelicacyListPage extends BasePage {

    private static final String PAGE_URL = "http://localhost:4200/";

    @FindBy(css = ".bg-txt h1")
    public WebElement listTitleElement;

    @FindBy(css = ".bg-txt h5")
    public WebElement listSubTitleElement;

    @FindBy(css = ".mat-input-element")
    private WebElement searchReceipeInputElement;

    @FindBy(css = ".recipe-list > mat-card")
    public List<WebElement> receipeListElement;

    public RkDelicacyListPage() {
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return listTitleElement
                .isDisplayed();
    }

    /**
     * perform navigation by clicking the item
     */
    public String doNavigateToDetailsPage(){
        List<WebElement> list = receipeListElement;
        list.get(0).click();
        return getDriver().getCurrentUrl();
    }

    /**
     * inserts search text in search string
     * @param text text for input
     */
    public void insertSearchString(String text) {
        searchReceipeInputElement.sendKeys(text);
    }

    /**
     * clears search string
     */
    public void clearSearchString(){
        searchReceipeInputElement.clear();
    }

    /**
     * getting search string text
     * @return text from search string
     */
    public String getSearchStringText(){
        return searchReceipeInputElement.getAttribute("value");
    }
}
