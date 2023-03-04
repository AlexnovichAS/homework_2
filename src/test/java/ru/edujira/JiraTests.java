package ru.edujira;

import BaseTest.WebHooks;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static JiraSteps.AllIssuesPage.*;
import static JiraSteps.BasePageJira.*;
import static JiraSteps.FilterPage.*;
import static JiraSteps.HomePage.goProject;
import static JiraSteps.PrintPage.checkInformationAboutTasks;
import static JiraSteps.RapidBoardPage.goLeftPanelSections;
import static JiraSteps.TaskPage.checkNameTaskInTask;
import static JiraSteps.TaskPage.checkTaskDetailsInTask;

public class JiraTests extends WebHooks {

    @Description("Проверить общее количество заведенных задач в проекте")
    @Test
    @DisplayName("Проверить общее количество заведенных задач в проекте")
    public void loginTest() {
        goProject("Проекты", "Test (TEST)");
        goLeftPanelSections("Задачи");
        clickButton("Переключить фильтр");
        clickButtonOptions("Все задачи");
        String countTask = resultCountTasks();
        clickProfileButton();
        chooseProfileOptions("Навигатор задач");
        goForPrint("Для печати");
        checkInformationAboutTasks(countTask);
    }

    @Description("Проверить Статус задачи и привязку к затронутой версии")
    @Test
    @DisplayName("Проверить Статус задачи и привязку к затронутой версии")
    public void checkStatusTest() {
        findInQuickSearch("TestSelenium");
        checkNameTaskInTask("TestSelenium");
        checkTaskDetailsInTask("Статус", "В работе");
        checkTaskDetailsInTask("Исправить в версиях", "Version 2.0");
    }

    @Description("Создание типа задачи: 'Ошибка' и перевод задачи по статусам до закрытого")
    @Test
    @DisplayName("Создание типа задачи: 'Ошибка' и перевод задачи по статусам до закрытого")
    public void createTaskTest() {
        goProject("Проекты", "Test (TEST)");
        clickPrimaryButtons("Создать");
        completeRequiredFieldTask("Тип задачи", "Ошибка");
        completeRequiredInputTask("Тема", "Не работает кнопка оплаты на главной странице при ее нажатии");
        completeRequiredFieldText("Описание", "На таком-то сайте, на странице оплаты рубашки, " +
                "при нажатии кнопки оплатить ничего не происходит");
        completeRequiredFieldTask("Приоритет", "Highest");
        completeRequiredFieldText("Окружение", "Windows 10 Google chrome Версия 101.0.4951.67 (Официальная сборка), (64 бит)");
        completeRequiredFieldTask("Исполнитель", "n215401@yandex.ru");
        completeRequiredFieldTask("Спринт", "Спринт 1");
        createTask();
        goLeftPanelSections("Задачи");
        findInFilter("Не работает кнопка оплаты на главной странице при ее нажатии");
        checkNameTaskInFilter("Не работает кнопка оплаты на главной странице при ее нажатии");
        checkTaskDetailsInFilter("Статус", "Сделать");
        choiceStatus("В работе");
        checkTaskDetailsInFilter("Статус", "В работе");
        choiceBusinessProcess("Бизнес-процесс", "Выполнено");
        checkTaskDetailsInFilter("Статус", "Готово");
    }
}
