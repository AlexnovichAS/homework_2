package BaseTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTests {

    @BeforeEach
    public void option() {
        Configuration.startMaximized = true;
        open("https://edujira.ifellow.ru");
    }

    @AfterEach
    public void clear() {
        closeWebDriver();
    }
}
