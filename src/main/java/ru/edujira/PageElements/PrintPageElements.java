package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PrintPageElements {

    public static SelenideElement resultCount = $x("(//div[contains(@class,'results-count')]//b)[3]");
}
