package ru.yandex.praktikum.pages;

import org.openqa.selenium.WebDriver;


public class OrderTrackPage {

    private final WebDriver driver;

    public OrderTrackPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String SUPPORT_NUMBER = "0101";

    public static final String COURIER_DELAYED_HEADER = "Для кого самокат";
    public static final String COURIER_DELAYED_DETAILS = "Не успеем привезти самокат вовремя. Чтобы уточнить статус заказа, позвоните в поддержку: " + SUPPORT_NUMBER;
    public static final String COURIER_ON_THE_WAY_HEADER = "Курьер едет к вам";
    public static final String COURIER_ON_THE_WAY_DETAILS = "Номер для связи: " + SUPPORT_NUMBER;
    public static final String COURIER_ON_SITE_HEADER = "Курьер на месте";
    public static final String COURIER_ON_SITE_DETAILS = "Заберите самокат и оплатите аренду";
    public static final String TIME_OVER_HEADER = "Время аренды кончилось";
    public static final String TIME_OVER_DETAILS = "Скоро курьер заберёт самокат";

    public static final String URL = "https://qa-scooter.praktikum-services.ru/track";



}


