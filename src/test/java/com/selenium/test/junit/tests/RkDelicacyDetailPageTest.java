package com.selenium.test.junit.tests;

import com.selenium.test.junit.rules.ScreenShotOnFailRule;
import com.selenium.test.pages.RkDelicacyDetailsPage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class RkDelicacyDetailPageTest {

    @Rule
    public ScreenShotOnFailRule screenShotOnFailRule = new ScreenShotOnFailRule();

    @Before
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    @Test
    public void testDetailsPage() {
        RkDelicacyDetailsPage rkDelicacyDetailsPage = new RkDelicacyDetailsPage();
        rkDelicacyDetailsPage.isPageOpened();
    }

    @After
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }

}
