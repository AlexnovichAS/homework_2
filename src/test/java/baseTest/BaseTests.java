package baseTest;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTests {

    @Before
    public void option() {
        Configuration.startMaximized = true;
    }

    @After
    public void clear() {
        closeWebDriver();
    }
}
