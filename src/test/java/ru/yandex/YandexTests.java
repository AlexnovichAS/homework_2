package ru.yandex;

import baseTest.BaseTests;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import pages.YandexBeforeSearch;

import static com.codeborne.selenide.Selenide.open;

public class YandexTests extends BaseTests {

    @Test
    public void oneSearchYandexTest() {
        Selenide.open("https://ya.ru", YandexBeforeSearch.class)
                .find("Банки ру")
                .goLink("Банки.ру - YouTube")
                .checkPage("Банки.ру - YouTube");
    }

    @Test
    public void secondSearchYandexTest() {
        open("https://ya.ru", YandexBeforeSearch.class)
                .find("Хабр")
                .goLink("Хабр — Википедия")
                .checkPage("Все публикации подряд / Хабр");
    }
}
