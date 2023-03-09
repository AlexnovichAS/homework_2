package ru.edujira.JiraSteps;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static ru.edujira.PageElements.RapidBoardPageElements.chooseSection;

public class RapidBoardPage {

    @Step("Клик на боковой панели на значок меню: {section}")
    public static void goLeftPanelSections(String section) {
        chooseSection(section).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
