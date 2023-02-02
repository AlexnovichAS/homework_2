package pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class YandexBeforeSearch {

    public YandexAfterSearch openService(String search) {
        $x("//input[@aria-label='Запрос']").sendKeys(search);
        $x("//button[@type='submit']").click();
        return page(YandexAfterSearch.class);
    }
}
