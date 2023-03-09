package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$x;

public class YandexBeforeSearch {

    public YandexAfterSearch find(String search) {
        $x("//input[@aria-label='Запрос']").sendKeys(search);
        $x("//button[@type='submit']").click();
        return Selenide.page(YandexAfterSearch.class);
    }
}
