package JiraSteps;

import org.junit.Assert;

import java.time.Duration;

import static PageElements.PrintPageElements.resultCount;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class PrintPage {

    public static void checkInformationAboutTasks(String count) {
        String infoTasks = resultCount.should(exist).shouldBe(visible, Duration.ofSeconds(10)).getText();
        Assert.assertEquals("Ошибка, не соответствие общего количества. На странице фильтров: " + count
                + " ,а на странице на печать: " + infoTasks, count, infoTasks);
    }
}
