package ru.yandex.praktikum.constants;

public enum RentTime {
    ONE_DAY_RENT("сутки"),
    TWO_DAY_RENT("двое суток"),
    THREE_DAY_RENT("трое суток"),
    FOUR_DAY_RENT("четверо суток"),
    FIVE_DAY_RENT("пятеро суток"),
    SIX_DAY_RENT("шестеро суток"),
    SEVEN_DAY_RENT("семеро суток");

    private final String time;
    RentTime(String t){
        time = t;
    }
    public String getRentTime(){
        return time;
    }
}
