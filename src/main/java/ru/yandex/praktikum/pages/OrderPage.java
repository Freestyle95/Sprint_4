package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.constants.BikeColor;
import ru.yandex.praktikum.constants.RentTime;
import ru.yandex.praktikum.utils.PageObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class OrderPage {

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Текст основного заголовка
    public static final String MAIN_HEADER_TEXT = "Для кого самокат";
    //Текст заголовка аренды
    public static final String RENT_HEADER_TEXT = "Про аренду";
    //Адрес страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru/order";
    //Текст заголовка успешного оформления заказа
    private final String ORDER_FINISHED = "Заказ оформлен";
    //Регэксп текста успешного оформления заказа
    private final String ORDER_CONFIRMATION_REGEXP = "^Номер заказа: \\d+\\.  Запишите его:\\n" + "пригодится, чтобы отслеживать статус$";

    //Заголовок
    private final By header = By.className("Order_Header__BZXOb");

    //Step 1
    //Имя
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Метро
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Список станция метро
    private final By metroStationsDropdown = By.xpath(".//div[@class='select-search__select']");
    //Номер телефона
    private final By telephoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка следующего шага
    private final By nextStepButton = By.xpath(".//button[text()='Далее']");

    //Step 2
    //Дата заказа
    private final By when = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private final By rentTimeField = By.xpath(".//*[text()='* Срок аренды']");
    //Список сроков аренды
    private final By rentTimeDropdown = By.xpath(".//*[text()='* Срок аренды']/parent::*/parent::div/div[@class='Dropdown-menu']");
    //Цвет самоката
    private final By colorField = By.xpath(".//*[text()='Цвет самоката']/parent::div");
    //Комментарий
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка предыдущего шага
    private final By previousStepButton = By.xpath(".//button[text()='Назад']");
    //Кнопка оформления заказа
    private final By makeOrderButton = By.xpath(".//div[@class='Order_Content__bmtHS']//button[text()='Заказать']");
    //Диалоговое окно подтверждения заказа
    private final By confirmOrderDialog = By.xpath(".//div[@class='Order_Modal__YZ-d3']");
    //Кнопка Да в диалоге подтверждения заказа
    private final By confirmOrderDialogYesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");
    //Заголовок диалога подтверждения заказа
    private final By orderModalHeader = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ']");
    //Текст диалога подтверждения заказа
    private final By orderModalText = By.xpath(".//*[@class='Order_Text__2broi']");
    //Кнопка получения статуса заказа
    private final By getOrderStatusButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Посмотреть статус']");

    public void verifyPageFirstStepStaticElements() {
        assertEquals(MAIN_HEADER_TEXT, driver.findElement(header).getText());
        assertTrue(driver.findElement(nameField).isDisplayed());
        assertTrue(driver.findElement(surnameField).isDisplayed());
        assertTrue(driver.findElement(addressField).isDisplayed());
        assertTrue(driver.findElement(metroField).isDisplayed());
        assertTrue(driver.findElement(telephoneField).isDisplayed());
        assertTrue(driver.findElement(nextStepButton).isDisplayed());
    }

    public void setNameField(String value) {
        PageObjectUtils.setInputFieldValue(driver, nameField, value);
    }

    public void setSurnameField(String value) {
        PageObjectUtils.setInputFieldValue(driver, surnameField, value);
    }

    public void setAddressField(String value) {
        PageObjectUtils.setInputFieldValue(driver, addressField, value);
    }

    public void setTelephoneField(String value) {
        PageObjectUtils.setInputFieldValue(driver, telephoneField, value);
    }

    public void selectMetroStation(String stationName) {
        WebElement field = driver.findElement(metroField);
        field.click();
        driver.findElement(metroStationsDropdown).findElement(PageObjectUtils.withText(stationName)).click();
        assertEquals(stationName, field.getAttribute("value"));
    }

    public void clickOnNextStepButton() {
        driver.findElement(nextStepButton).click();
    }

    public void verifyPageSecondStepStaticElements() {
        assertEquals(RENT_HEADER_TEXT, driver.findElement(header).getText());
        assertTrue(driver.findElement(when).isDisplayed());
        assertTrue(driver.findElement(rentTimeField).isDisplayed());
        assertTrue(driver.findElement(colorField).isDisplayed());
        assertTrue(driver.findElement(commentField).isDisplayed());
        assertTrue(driver.findElement(previousStepButton).isDisplayed());
        assertTrue(driver.findElement(makeOrderButton).isDisplayed());
    }

    public void setWhen(String value) {
        PageObjectUtils.setInputFieldValue(driver, when, value);
        driver.findElement(when).sendKeys(Keys.RETURN);
    }

    public void setRentTime(RentTime rentTime) {
        WebElement field = driver.findElement(rentTimeField);
        field.click();
        driver.findElement(rentTimeDropdown).findElement(PageObjectUtils.withText(rentTime.getRentTime())).click();
        assertEquals(rentTime.getRentTime(), field.getText());
    }

    public void setColorField(BikeColor[] colors) {
        WebElement field = driver.findElement(colorField);
        for (BikeColor color : colors) {
            field.findElement(PageObjectUtils.withText(color.getColor())).click();
        }
        assertTrue(field.getAttribute("class").contains("Order_FilledContainer"));
    }

    public void setCommentField(String value) {
        PageObjectUtils.setInputFieldValue(driver, commentField, value);
    }

    public void clickOnPreviousStepButton() {
        driver.findElement(previousStepButton).click();
    }

    public void clickOnMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }

    public int finishOrder() {
        clickOnMakeOrderButton();
        WebElement dialog = driver.findElement(confirmOrderDialog);
        assertTrue(dialog.isDisplayed());
        driver.findElement(confirmOrderDialogYesButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.textToBePresentInElement(driver.findElement(confirmOrderDialog).findElement(orderModalHeader), ORDER_FINISHED));
        String confirmationText = driver.findElement(orderModalText).getText();
        assertTrue(confirmationText.matches(ORDER_CONFIRMATION_REGEXP));
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(confirmationText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }else{
            return -1;
        }
    }

    public void clickOnGetOrderStatusButton() {
        driver.findElement(getOrderStatusButton).click();
    }

}


