package baseTest;

import com.codeborne.selenide.Configuration;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTests {

    @Before
    public void option() {
        Configuration.startMaximized = true;
        System.out.println("@Before");
    }

    @After
    public void clear() {
        System.out.println("@After");
        closeWebDriver();
    }
}
