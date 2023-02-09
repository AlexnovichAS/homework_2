package pagesJira;

import org.junit.Assert;

import java.time.Duration;

import static PageElements.TaskPageElements.taskDetails;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.page;

public class TaskPage extends BasePageJira {

    public TaskPage checkTaskDetails(String name, String result) {
        String detailsResult = taskDetails(name)
                .waitUntil(exactText(result), 10000)
                .should(exist, Duration.ofSeconds(10))
                .getText().toLowerCase().trim();
        Assert.assertEquals("Ошибка, в поле задачи: " + name + " указано: " + detailsResult, result.toLowerCase(), detailsResult);
        return page(TaskPage.class);
    }
}
