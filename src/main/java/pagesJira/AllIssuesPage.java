package pagesJira;


import org.junit.Assert;

import java.time.Duration;

import static PageElements.AllIssuesElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AllIssuesPage extends BasePageJira {

    public AllIssuesPage clickButton(String button) {
        chooseButton(button).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(AllIssuesPage.class);
    }

    public AllIssuesPage clickButtonOptions(String options) {
        chooseButtonOptions(options).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(AllIssuesPage.class);
    }

    public AllIssuesPage checkingRunningTasks(String number) {
        String resultNumber = tasksCheck.shouldHave(visible, Duration.ofSeconds(10)).getText();
        Assert.assertEquals("Ошибка, общее количество заведенных задач в проекте: " + resultNumber, number, resultNumber.replaceAll("1 из ", ""));
        return page(AllIssuesPage.class);
    }
}
