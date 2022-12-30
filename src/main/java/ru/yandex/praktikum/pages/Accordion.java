package ru.yandex.praktikum.pages;

public class Accordion {
    private String header;
    private String content;

    public Accordion(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }
}
