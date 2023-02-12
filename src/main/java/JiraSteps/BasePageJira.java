package JiraSteps;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static PageElements.BasePageElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BasePageJira {

    public static void clickProfileButton() {
        profileButton.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void chooseProfileOptions(String nameOptions) {
        profileOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void clickPrimaryButtons(String nameButton) {
        primaryButtons(nameButton).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void goPrimaryButtonsOptions(String nameOptions) {
        primaryButtonsOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void findInQuickSearch(String task) {
        searchField.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(task);
        resultInQuickSearch(task).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public static void completeRequiredInputTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
    }

    public static void completeRequiredFieldTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
        result.pressEnter();
    }

    public static void completeRequiredFieldText(String field, String input) {
        SelenideElement iframe = inputText(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        switchTo().frame(iframe).findElement(By.xpath(".//body")).sendKeys(input);
        switchTo().parentFrame();
    }

    public static void createTask() {
        buttonCreate.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
