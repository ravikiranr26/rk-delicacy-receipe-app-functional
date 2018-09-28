package com.selenium.test.junit.tests;

import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.RkDelicacyListPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RkDelicacyListPageTest {

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void testHasAllCorrectDetails() {
        RkDelicacyListPage rkDelicacyListPage = new RkDelicacyListPage();
        assertTrue("Title had been found on the page", rkDelicacyListPage.isPageOpened());
        assertEquals("Title equals", "Rk Cooking simplified.", rkDelicacyListPage.listTitleElement.getText());
        assertEquals("Sub Title equals", "The best recipe organizer for home cooks.",
                rkDelicacyListPage.listSubTitleElement.getText());
        List<WebElement> list = rkDelicacyListPage.receipeListElement;
        assertEquals("Receipe List equals", 5, list.size());
    }

    @Test
    public void testSearchReceipe() {
        String toSearch = "Cake";
        RkDelicacyListPage rkDelicacyListPage = new RkDelicacyListPage();
        rkDelicacyListPage.insertSearchString(toSearch);
        List<WebElement> list = rkDelicacyListPage.receipeListElement;
        assertEquals("Receipe List equals", 1, list.size());
        rkDelicacyListPage.clearSearchString();
    }

    @Test
    public void testNavigationToDetailsPage() {
        RkDelicacyListPage rkDelicacyListPage = new RkDelicacyListPage();
        assertEquals("Details Url", "http://localhost:4200/",  rkDelicacyListPage.doNavigateToDetailsPage());
    }

    @After
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
