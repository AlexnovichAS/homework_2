package ru.edujira.JiraSteps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static ru.edujira.PageElements.BasePageElements.*;

public abstract class BasePageJira {

    @Step("Клик на верхней панели на значок: 'Пользовательский профиль'")
    public static void clickProfileButton() {
        profileButton.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Клик по значению: '{nameOptions}', в выпадающем меню 'Пользовательского профиля'")
    public static void chooseProfileOptions(String nameOptions) {
        profileOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Клик по кнопке: '{nameButton}', на верхней панели")
    public static void clickPrimaryButtons(String nameButton) {
        primaryButtons(nameButton).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Клик по значению: {nameOptions}, в выпадающем меню на верхней панели")
    public static void goPrimaryButtonsOptions(String nameOptions) {
        primaryButtonsOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Ввод значения: {input}, у поля: {field} в окне 'Создание задачи'")
    public static void completeRequiredInputTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
    }

    @Step("Выбор значения: '{input}', у поля: '{field}' в окне 'Создание задачи'")
    public static void completeRequiredFieldTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
        result.pressEnter();
    }

    @Step("Ввод текста: '{input}', у поля: '{field}' в окне 'Создание задачи'")
    public static void completeRequiredFieldText(String field, String input) {
        SelenideElement iframe = inputText(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        switchTo().frame(iframe).findElement(By.xpath(".//body")).sendKeys(input);
        switchTo().parentFrame();
    }

    @Step("Клик по кнопке: {nameOptions} в окне 'Создание задачи'")
    public static void createTask() {
        buttonCreate.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
