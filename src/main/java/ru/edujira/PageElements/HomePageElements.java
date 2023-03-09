package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HomePageElements {
    public static SelenideElement nameInput = $x("//input[contains(@name,'username')]");
    public static SelenideElement passInput = $x("//input[contains(@name,'password')]");
    public static SelenideElement loginButton = $x("//input[@value='Войти']");
}
