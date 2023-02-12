package JiraSteps;

import org.junit.Assert;

import java.time.Duration;

import static PageElements.FilterPageElements.*;
import static com.codeborne.selenide.Condition.*;

public class FilterPage {

    public static void checkTaskDetailsInFilter(String name, String result) {
        String detailsResult = taskDetails(name)
                .waitUntil(exactText(result), 10000)
                .should(exist, Duration.ofSeconds(10))
                .getText().toLowerCase().trim();
        Assert.assertEquals("Ошибка, в поле задачи: " + name + " указано: " + detailsResult, result.toLowerCase(), detailsResult);
    }

    public static void findInFilter(String name) {
        buttonFilter.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        fieldInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(name);
        buttonSearch.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        taskBox(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void choiceStatus(String name) {
        buttonStatus(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void choiceBusinessProcess(String nameProcess, String name) {
        buttonStatus(nameProcess).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        buttonBusinessProcess(nameProcess, name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void goForPrint(String nameOptions) {
        buttonSaved.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        chooseButtonOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void checkNameTaskInFilter(String nameTask) {
        String header = headerH1.getText();
        Assert.assertEquals("Ошибка, найдена задача:" + header + "ожидалась: " + nameTask, nameTask, header);
    }
}
