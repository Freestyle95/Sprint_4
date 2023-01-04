package ru.yandex.praktikum.constants;

public enum BikeColor {

    BLACK_COLOR("чёрный жемчуг"),
    GRAY_COLOR("серая безысходность");

    private final String color;
    BikeColor(String c){
        color = c;
    }
    public String getColor(){
        return color;
    }
}
