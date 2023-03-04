package JiraSteps;

import io.qameta.allure.Step;
import org.junit.Assert;

import java.time.Duration;

import static PageElements.TaskPageElements.headerH1;
import static PageElements.TaskPageElements.taskDetails;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;

public class TaskPage {

    @Step("Проверка значения: {result}, у свойства {name} в деталях задачи")
    public static void checkTaskDetailsInTask(String name, String result) {
        String detailsResult = taskDetails(name)
                .waitUntil(exactText(result), 10000)
                .should(exist, Duration.ofSeconds(10))
                .getText().toLowerCase().trim();
        Assert.assertEquals("Ошибка, в поле задачи: " + name + " указано: " + detailsResult, result.toLowerCase(), detailsResult);
    }

    @Step("Проверка перехода в задачу: {nameTask}")
    public static void checkNameTaskInTask(String nameTask) {
        String header = headerH1.getText();
        Assert.assertEquals("Ошибка, найдена задача:" + header + "ожидалась: " + nameTask, nameTask, header);
    }
}
