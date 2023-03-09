package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AllIssuesElements {

    public static SelenideElement tasksCheck = $x("//*[@class='showing']//span");

    public static SelenideElement chooseButton(String button) {
        return $x("//button[text()='" + button + "']");
    }

    public static SelenideElement chooseButtonOptions(String options) {
        return $x("//a[text()='" + options + "']");
    }
}
