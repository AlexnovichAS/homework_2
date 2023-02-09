package PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FilterPageElements {
    public static SelenideElement fieldInput = $x("//input[@placeholder='Содержит текст']");
    public static SelenideElement buttonSearch = $x("//button[text()='Поиск']");
    public static SelenideElement buttonFilter = $x("//a[text()='Посмотреть все задачи и фильтры']");

    public static SelenideElement taskBox(String task) {
        return $$x("//div[@class='search-results']//span").find(exactText(task));
    }

    public static SelenideElement taskDetails(String name) {
        return $x("//strong[@title='" + name + "']//following-sibling::span");
    }

    public static SelenideElement buttonStatus(String name) {
        return $x("//span[text()='" + name + "']");
    }

    public static SelenideElement buttonBusinessProcess(String nameProcess, String name) {
        return $x("//span[text()='" + nameProcess + "']//parent::a//following::span[text()='" + name + "']");
    }
}
