package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StartPage {

    private final WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Адрес страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    //Список аккордионов FAQ
    private final By faqAccordionItems = By.xpath(".//div[@class='Home_FAQ__3uVm4']//div[@class='accordion__item']");
    //Текст аккордиона (заголовок)
    private final By faqAccordionHeader = By.xpath(".//div[@class='accordion__heading']");
    //Текст аккордиона (содержимое)
    private final By faqAccordionPanel = By.xpath(".//div[@class='accordion__panel']");
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

    public void accordionBlockHasElements(Accordion[] items) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqAccordionItems));
        WebElement panelToBeClosed=null;
        for (WebElement element : driver.findElements(faqAccordionItems)) {

            WebElement header = element.findElement(faqAccordionHeader);
            WebElement content = element.findElement(faqAccordionPanel);
            assertTrue(header.isDisplayed());
            assertFalse(content.isDisplayed());
            header.click();
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(content));
            assertTrue(content.isDisplayed());
            String headerText = header.getText();
            String contentText = content.getText();
            Accordion acc = Arrays.stream(items).filter(x -> x.getHeader().equals(headerText)).findAny().get();
            assertTrue(headerText.equals(acc.getHeader()));
            assertTrue(contentText.equals(acc.getContent()));
            if (panelToBeClosed!=null) {
                assertFalse(panelToBeClosed.isDisplayed());
            }
            panelToBeClosed = content;
        }
    }

    public boolean accordionHeaderIsDisplayed() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(faqAccordionHeader));
        return driver.findElement(faqAccordionHeader).isDisplayed();
    }

    public boolean accordionPanelIsDisplayed() {
        WebElement element = driver.findElement(faqAccordionPanel);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return driver.findElement(faqAccordionPanel).isDisplayed();
    }

    public boolean accordionPanelHasText(String text) {
        return driver.findElement(faqAccordionPanel).getText().equals(text);
    }

    public void clickMakeOrderHeaderButton() {
        driver.findElement(makeOrderHeaderButton).click();
    }

    public void clickMakeOrderFinishButton() {
        driver.findElement(makeOrderFinishButton).click();
    }


}


