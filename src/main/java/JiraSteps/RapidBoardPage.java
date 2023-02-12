package JiraSteps;

import java.time.Duration;

import static PageElements.RapidBoardPageElements.chooseSection;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class RapidBoardPage {

    public static void goLeftPanelSections(String section) {
        chooseSection(section).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
