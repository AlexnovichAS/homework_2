package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPageElements {

    public static SelenideElement headerH1 = $x("(//h1)[2]");

    public static SelenideElement taskDetails(String name) {
        return $x("//strong[@title='" + name + "']//following-sibling::span");
    }
}
