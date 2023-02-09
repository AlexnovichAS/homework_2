package pagesJira;

import org.junit.Assert;

import java.time.Duration;

import static PageElements.BasePageElements.profileIcon;
import static PageElements.HomePageElements.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends BasePageJira {

    public HomePage login(String name, String login) {
        nameInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(name);
        passInput.should(exist).shouldBe(visible, Duration.ofSeconds(10)).sendKeys(login);
        loginButton.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        String userName = profileIcon.should(exist).shouldBe(visible, Duration.ofSeconds(10)).getAttribute("data-username");
        Assert.assertEquals("Пользователь не зарегистрирован", name, userName);
        return page(HomePage.class);
    }

    public HomePage goProject(String nameButton, String nameOptions) {
        clickPrimaryButtons(nameButton);
        goPrimaryButtonsOptions(nameOptions);
        return page(HomePage.class);
    }
}
