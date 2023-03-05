package JiraSteps;

import io.qameta.allure.Step;
import org.junit.Assert;

import java.time.Duration;

import static JiraSteps.BasePageJira.clickPrimaryButtons;
import static JiraSteps.BasePageJira.goPrimaryButtonsOptions;
import static PageElements.BasePageElements.profileIcon;
import static PageElements.HomePageElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.getLifecycle;

public class HomePage {

    @Step("Открываем страницу по ссылке: {url}")
    public static void openUrl(String url){
        open(url);
    }

    @Step("Авторизуемся в системе с учетными данными: {name}")
    public static void login(String name, String login) {
        getLifecycle().updateStep(stepResult -> stepResult.getParameters().remove(1));
        nameInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(name);
        passInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(login);
        loginButton.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        String userName = profileIcon.should(exist).shouldBe(visible, Duration.ofSeconds(10)).getAttribute("data-username");
        Assert.assertEquals("Пользователь" + name + "не зарегистрирован", name, userName);
    }

    @Step("Переходим в проект: {nameOptions}")
    public static void goProject(String nameButton, String nameOptions) {
        clickPrimaryButtons(nameButton);
        goPrimaryButtonsOptions(nameOptions);
    }
}
