import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        features = "src/test/resources/features",
        glue = "StepDefinition",
        tags = "@all"
)
public class RunTest {
}
