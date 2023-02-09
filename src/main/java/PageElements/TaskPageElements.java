package PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPageElements {

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
