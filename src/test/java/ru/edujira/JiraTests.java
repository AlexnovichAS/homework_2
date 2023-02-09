package ru.edujira;

import BaseTest.BaseTests;
import org.junit.jupiter.api.Test;
import pagesJira.*;

import static com.codeborne.selenide.Selenide.page;

public class JiraTests extends BaseTests {

    @Test
    public void loginTest() {
        page(HomePage.class)
                .login("aalehnovich", "Qwerty123")
                .goProject("Проекты", "Test (TEST)");
        page(RapidBoardPage.class)
                .goLeftPanelSections("Задачи");
        page(AllIssuesPage.class)
                .clickButton("Переключить фильтр")
                .clickButtonOptions("Все задачи")
                .checkingRunningTasks("8404");
    }
    @Test
    public void checkStatusTest() {
        page(HomePage.class)
                .login("aalehnovich", "Qwerty123")
                .find("TestSelenium");
        page(TaskPage.class)
                .checkTaskDetails("Статус","В РАБОТЕ")
                .checkTaskDetails("Исправить в версиях", "Version 2.0");
    }
    @Test
    public void createTaskTest() {
        page(HomePage.class)
                .login("aalehnovich", "Qwerty123")
                .goProject("Проекты", "Test (TEST)")
                .clickPrimaryButtons("Создать");
        page(HomePage.class)
                .completeRequiredFieldTask("Тип задачи","Ошибка");
        page(HomePage.class)
                .completeRequiredInputTask("Тема","Не работает кнопка оплаты на главной странице при ее нажатии");
        page(HomePage.class)
                .completeRequiredFieldText("Описание","На таком-то сайте, на странице оплаты рубашки, " +
                        "при нажатии кнопки оплатить ничего не происходит ");
        page(HomePage.class)
                .completeRequiredFieldText("Окружение","Windows 10 Google chrome Версия 101.0.4951.67 (Официальная сборка), (64 бит)");
        page(HomePage.class)
                .completeRequiredFieldTask("Исполнитель","n215401@yandex.ru");
        page(HomePage.class)
                .completeRequiredFieldTask("Спринт", "Спринт 1");
        page(HomePage.class)
                .createTask();
        page(RapidBoardPage.class)
                .goLeftPanelSections("Задачи");
        page(FilterPage.class)
                .find("Не работает кнопка оплаты на главной странице при ее нажатии")
                .checkTaskDetails("Статус","Сделать")
                .choiceStatus("В работе")
                .checkTaskDetails("Статус","В работе")
                .choiceBusinessProcess("Бизнес-процесс", "Выполнено")
                .checkTaskDetails("Статус","Готово");
    }
}
