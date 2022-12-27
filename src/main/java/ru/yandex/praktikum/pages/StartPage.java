package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StartPage {

    private final WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }
    //Адрес страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    //Текст аккордиона (заголовок)
    private final By faqAccordionHeader = By.className("accordion__heading");
    //Текст аккордиона (содержимое)
    private final By faqAccordionPanel = By.className("accordion__panel");
    //Кнопка принять куки
    private final By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");
    //Кнопка создания заказа в шапке
    private final By makeOrderHeaderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //Кнопка создания заказа в середине страницы
    private final By makeOrderFinishButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public void clickOnAccordionHeader() {
        driver.findElement(faqAccordionHeader).click();
    }

    public void clickOnAcceptCookies() {
        driver.findElement(acceptCookieButton).click();
    }

    public boolean accordionHeaderIsDisplayed() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(faqAccordionHeader));
        return driver.findElement(faqAccordionHeader).isDisplayed();
    }

    public boolean accordionPanelIsDisplayed() {
        WebElement element = driver.findElement(faqAccordionPanel);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return driver.findElement(faqAccordionPanel).isDisplayed();
    }

    public boolean accordionPanelHasText(String text) {
        return driver.findElement(faqAccordionPanel).getText().equals(text);
    }

    public void clickMakeOrderHeaderButton(){
        driver.findElement(makeOrderHeaderButton).click();
    }

    public void clickMakeOrderFinishButton(){
        driver.findElement(makeOrderFinishButton).click();
    }


}


