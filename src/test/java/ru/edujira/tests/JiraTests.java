package ru.edujira.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.edujira.BaseTest.WebHooks;

import static ru.edujira.JiraSteps.AllIssuesPage.*;
import static ru.edujira.JiraSteps.BasePageJira.*;
import static ru.edujira.JiraSteps.FilterPage.*;
import static ru.edujira.JiraSteps.HomePage.goProject;
import static ru.edujira.JiraSteps.PrintPage.checkInformationAboutTasks;
import static ru.edujira.JiraSteps.RapidBoardPage.goLeftPanelSections;
import static ru.edujira.JiraSteps.TaskPage.checkNameTaskInTask;
import static ru.edujira.JiraSteps.TaskPage.checkTaskDetailsInTask;

@DisplayName("Проверить работу Jira")
public class JiraTests extends WebHooks {

    @Test
    @DisplayName("Проверить общее количество заведенных задач в проекте")
    @Description("Проверяет общее количество заведенных задач в проекте")
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
    @DisplayName("Проверить Статус задачи и привязку к затронутой версии")
    @Description("Проверяет Статус задачи и привязку к затронутой версии")
    public void checkStatusTest() {
        goProject("Проекты", "Test (TEST)");
        goLeftPanelSections("Задачи");
        findInFilter("Задача", "TestSelenium");
        checkNameTaskInTask("TestSelenium");
        checkTaskDetailsInTask("Статус", "В работе");
        checkTaskDetailsInTask("Исправить в версиях", "Version 2.0");
    }

    @Test
    @DisplayName("Создать задачу")
    @Description("Создает задачу: 'Ошибка' и переводит задачу по статусам до закрытого")
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
        findInFilter("Ошибка", "Не работает кнопка оплаты на главной странице при ее нажатии");
        checkNameTaskInFilter("Не работает кнопка оплаты на главной странице при ее нажатии");
        checkTaskDetailsInFilter("Статус", "Сделать");
        choiceStatus("В работе");
        checkTaskDetailsInFilter("Статус", "В работе");
        choiceBusinessProcess("Бизнес-процесс", "Выполнено");
        checkTaskDetailsInFilter("Статус", "Готово");
    }
}
