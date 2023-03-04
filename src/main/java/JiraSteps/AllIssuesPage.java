package JiraSteps;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.time.Duration;

import static PageElements.AllIssuesElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class AllIssuesPage {

    @Step("Клик по кнопке: {button}")
    public static void clickButton(String button) {
        chooseButton(button).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
    @Step("Клик по значению: {options}, в выпадающем меню")
    public static void clickButtonOptions(String options) {
        chooseButtonOptions(options).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Получение количества задач на странице 'Все задачи'")
    public static String resultCountTasks() {
        String result = tasksCheck.waitUntil(visible,10000)
                .shouldHave(exist, Duration.ofSeconds(10)).getText()
                .replaceAll("1 из ", "");
        Allure.addAttachment("Общее количество задач",result);
        return result;
    }
}
