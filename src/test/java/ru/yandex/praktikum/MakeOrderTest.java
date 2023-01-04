package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.constants.BikeColor;
import ru.yandex.praktikum.constants.RentTime;
import ru.yandex.praktikum.pages.OrderPage;
import ru.yandex.praktikum.pages.OrderTrackPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MakeOrderTest {

    private WebDriver driver;

    private String browser;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String metroStation;
    private String orderDate;
    private RentTime rentTime;
    private BikeColor[] bikeColors;
    private String comment;

    private OrderPage orderPage;

    public MakeOrderTest(String browser, String name, String surname, String phoneNumber, String address, String metroStation, String orderDate, RentTime rentTime, BikeColor[] bikeColors, String comment) {

        this.browser = browser;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.orderDate = orderDate;
        this.rentTime = rentTime;
        this.bikeColors = bikeColors;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Make order [{0}]")
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        "firefox",
                        "Кирилл", "Гаранов", "+79999999999",
                        "Красная площадь", "Площадь Революции", "10.10.2023",
                        RentTime.ONE_DAY_RENT, new BikeColor[]{BikeColor.GRAY_COLOR},
                        "Комментарий"
                },
                {
                        "chrome",
                        "Кирилл", "Гаранов", "+79999999999",
                        "Красная площадь", "Площадь Революции", "10.10.2023",
                        RentTime.ONE_DAY_RENT, new BikeColor[]{BikeColor.GRAY_COLOR},
                        "Комментарий"
                },
                {
                        "firefox",
                        "Иван", "Иванов", "+79999999999",
                        "Москва", "Третьяковская", "10.10.2023",
                        RentTime.ONE_DAY_RENT, new BikeColor[]{BikeColor.BLACK_COLOR},
                        ""
                },
                {
                        "chrome",
                        "Иван", "Иванов", "+79999999999",
                        "Москва", "Третьяковская", "10.10.2023",
                        RentTime.ONE_DAY_RENT, new BikeColor[]{BikeColor.BLACK_COLOR},
                        ""
                }
        };
    }

    @Before
    public void setUp() {
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "C:\\firefoxWebDriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\chromeWebDriver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        orderPage = new OrderPage(driver);
        driver.get(OrderPage.URL);
    }

    @Test
    public void test() {
        //First step
        orderPage.verifyPageFirstStepStaticElements();
        orderPage.setNameField(name);
        orderPage.setSurnameField(surname);
        orderPage.setAddressField(address);
        orderPage.setTelephoneField(phoneNumber);
        orderPage.selectMetroStation(metroStation);
        orderPage.clickOnNextStepButton();

        //Second step
        orderPage.verifyPageSecondStepStaticElements();
        orderPage.setWhen(orderDate);
        orderPage.setRentTime(rentTime);
        orderPage.setColorField(bikeColors);
        orderPage.setCommentField(comment);
        int orderNumber = orderPage.finishOrder();

        //Finish
        orderPage.clickOnGetOrderStatusButton();
        assertEquals(OrderTrackPage.URL + "?t=" + orderNumber, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
