package pagesJira;

import org.junit.Assert;

import java.time.Duration;

import static PageElements.FilterPageElements.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class FilterPage {

    public FilterPage checkTaskDetails(String name, String result) {
        String detailsResult = taskDetails(name)
                .waitUntil(exactText(result), 10000)
                .should(exist, Duration.ofSeconds(10))
                .getText().toLowerCase().trim();
        Assert.assertEquals("Ошибка, в поле задачи: " + name + " указано: " + detailsResult, result.toLowerCase(), detailsResult);
        return page(FilterPage.class);
    }

    public FilterPage find(String name) {
        buttonFilter.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        fieldInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(name);
        buttonSearch.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        taskBox(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(FilterPage.class);
    }

    public FilterPage choiceStatus(String name) {
        buttonStatus(name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(FilterPage.class);
    }

    public FilterPage choiceBusinessProcess(String nameProcess, String name) {
        buttonStatus(nameProcess).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        buttonBusinessProcess(nameProcess, name).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(FilterPage.class);
    }
}
