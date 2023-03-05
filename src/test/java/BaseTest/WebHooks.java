package BaseTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static JiraSteps.HomePage.login;
import static JiraSteps.HomePage.openUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class WebHooks {

    @BeforeAll
    public static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @BeforeEach
    public void option() {
        Configuration.startMaximized = true;
        openUrl("https://edujira.ifellow.ru");
        login("aalehnovich", "Qwerty123");
    }

    @AfterEach
    public void clear() {
        closeWebDriver();
    }
}
