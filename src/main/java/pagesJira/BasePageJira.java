package pagesJira;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static PageElements.BasePageElements.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.switchTo;

public abstract class BasePageJira {


    public void clickPrimaryButtons(String nameButton) {
        primaryButtons(nameButton).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void goPrimaryButtonsOptions(String nameOptions) {
        primaryButtonsOptions(nameOptions).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void find(String task) {
        searchField.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(task);
        SelenideElement name = $$x("//div[@class='quick-search-result-group']//span").find(exactText(task));
        name.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void completeRequiredInputTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
    }

    public void completeRequiredFieldTask(String field, String input) {
        SelenideElement result = requiredField(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        result.doubleClick();
        result.sendKeys(input);
        result.pressEnter();
    }

    public void completeRequiredFieldText(String field, String input) {
        SelenideElement iframe = inputText(field).should(exist).shouldBe(visible, Duration.ofSeconds(10));
        switchTo().frame(iframe).findElement(By.xpath(".//body")).sendKeys(input);
        switchTo().parentFrame();
    }

    public void createTask() {
        buttonCreate.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
