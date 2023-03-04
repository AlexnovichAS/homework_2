package JiraSteps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.time.Duration;

import static JiraSteps.BasePageJira.clickPrimaryButtons;
import static JiraSteps.BasePageJira.goPrimaryButtonsOptions;
import static PageElements.BasePageElements.profileIcon;
import static PageElements.HomePageElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class HomePage {

    @Step("Авторизуемся в системе с учетными данными: {name}, {login}")
    public static void login(String name, String login) {
        Allure.step("Параметры", (step)-> {
            step.parameter("Имя",name);
            step.parameter("Пароль", login);
        });
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
