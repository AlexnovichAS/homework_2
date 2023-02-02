package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YandexAfterSearch {

    public OpenMainPage goLink(String search) {
        SelenideElement element = $$x("//div[contains(@class,'organic__title-wrapper')]").find(text(search));
        element.$x(".//a[@href]").click();
        switchTo().window(1);
        return page(OpenMainPage.class);
    }
}
