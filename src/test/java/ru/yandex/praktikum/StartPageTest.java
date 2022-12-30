package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pages.Accordion;
import ru.yandex.praktikum.pages.OrderPage;
import ru.yandex.praktikum.pages.StartPage;

import static org.junit.Assert.assertEquals;

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
        Accordion[] items = {
                new Accordion(
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                new Accordion(
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                                "можете просто сделать несколько заказов — один за другим."),
                new Accordion(
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                                "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                                "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                new Accordion(
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                new Accordion(
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                new Accordion(
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                new Accordion(
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                new Accordion(
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        };
        startPage.accordionBlockHasElements(items);
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
