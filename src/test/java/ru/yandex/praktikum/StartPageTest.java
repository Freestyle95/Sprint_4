package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pages.OrderPage;
import ru.yandex.praktikum.pages.StartPage;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StartPageTest {

    private WebDriver driver;
    private String browser;

    private StartPage startPage;

    public StartPageTest(String browser){
        this.browser=browser;
    }

    @Parameterized.Parameters(name = "Start page [{0}]")
    public static String[] getBrowser() {
        return new String[]{
                "firefox",
                "chrome"
        };
    }

    @Before
    public void setUp() {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\firefoxWebDriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromeWebDriver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        startPage = new StartPage(driver);
        driver.get(startPage.URL);
        startPage.clickOnAcceptCookies();
    }

    @Test
    public void accordionExpansionTest() {
        assertTrue(startPage.accordionHeaderIsDisplayed());
        assertFalse(startPage.accordionPanelIsDisplayed());
        startPage.clickOnAccordionHeader();
        assertTrue(startPage.accordionPanelIsDisplayed());
        assertTrue(startPage.accordionPanelHasText("Сутки — 400 рублей. Оплата курьеру — наличными или картой."));
    }

    @Test
    public void makeMainOrderTest() {
        startPage.clickMakeOrderHeaderButton();
        assertEquals(OrderPage.URL, driver.getCurrentUrl());
    }

    @Test
    public void makeFinishOrderTest() {
        startPage.clickMakeOrderFinishButton();
        assertEquals(OrderPage.URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
