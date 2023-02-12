package ru.edujira;

import BaseTest.WebHooks;
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

    @Test
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

    @Test
    public void checkStatusTest() {
        findInQuickSearch("TestSelenium");
        checkNameTaskInTask("TestSelenium");
        checkTaskDetailsInTask("Статус", "В работе");
        checkTaskDetailsInTask("Исправить в версиях", "Version 2.0");
    }

    @Test
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
