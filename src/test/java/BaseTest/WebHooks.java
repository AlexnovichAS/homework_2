package BaseTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static JiraSteps.HomePage.login;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class WebHooks {

    @BeforeEach
    public void option() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru");
        login("aalehnovich", "Qwerty123");
    }

    @AfterEach
    public void clear() {
        closeWebDriver();
    }
}
