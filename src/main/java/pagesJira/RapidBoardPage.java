package pagesJira;

import java.time.Duration;

import static PageElements.RapidBoardPageElements.chooseSection;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RapidBoardPage extends BasePageJira {

    public RapidBoardPage goLeftPanelSections(String section) {
        chooseSection(section).should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(RapidBoardPage.class);
    }
}
