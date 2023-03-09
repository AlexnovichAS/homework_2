package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RapidBoardPageElements {

    public static SelenideElement chooseSection(String section) {
        return $x("//span[text()='" + section + "']//parent::a");
    }
}
