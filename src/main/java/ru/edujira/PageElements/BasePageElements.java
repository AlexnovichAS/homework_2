package ru.edujira.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasePageElements {

    public static SelenideElement searchField = $x("//input[@placeholder='Поиск']");
    public static SelenideElement profileButton = $x("//a[contains(@title,'Пользовательский профиль')]");
    public static SelenideElement buttonCreate = $x("//input[@value='Создать']");
    public static SelenideElement profileIcon = $x("//a[@id='header-details-user-fullname']");

    public static SelenideElement profileOptions(String options) {
        return $x("//a[text()='" + options + "']");
    }

    public static SelenideElement primaryButtons(String name) {
        return $x("//div[@class='aui-header-primary']//a[contains(text(),'" + name + "')]");
    }

    public static SelenideElement primaryButtonsOptions(String name) {
        return $x("//div[@class='aui-header-primary']//a[text()='" + name + "']");
    }

    public static SelenideElement requiredField(String field) {
        return $x("(//label[contains(text(),'" + field + "')]//parent::div//input)[1]");
    }

    public static SelenideElement inputText(String field) {
        return $x("//form[@name='jiraform']//label[contains(text(),'" + field + "')]//parent::div//iframe");
    }

    public static SelenideElement resultInQuickSearch(String task) {
        return $$x("//div[@class='quick-search-result-group']//span").find(exactText(task));
    }
}
