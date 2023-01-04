package ru.yandex.praktikum.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public abstract class PageObjectUtils {

    public static By withText(String value) {
        return By.xpath(".//*[text()='" + value + "']");
    }

    public static void setInputFieldValue(WebDriver driver, By element, String value) {
        WebElement field = driver.findElement(element);
        field.clear();
        field.sendKeys(value);
        assertEquals(value, field.getAttribute("value"));
    }
}
