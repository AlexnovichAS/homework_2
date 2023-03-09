package baseTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTests {

    @BeforeEach
    public void option() {
        Configuration.startMaximized = true;
        System.out.println("@Before");
    }

    @AfterEach
    public void clear() {
        System.out.println("@After");
        closeWebDriver();
    }
}
