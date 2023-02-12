package JiraSteps;

import java.time.Duration;

import static PageElements.AllIssuesElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class AllIssuesPage {

    public static void clickButton(String button) {
        chooseButton(button).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void clickButtonOptions(String options) {
        chooseButtonOptions(options).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static String resultCountTasks() {
        return tasksCheck.shouldHave(visible, Duration.ofSeconds(10)).getText().replaceAll("1 из ", "");
    }
}
