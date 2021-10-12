package pages.polls;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.ParentPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class PollsManagementPage extends ParentPage {
    private static final SelenideElement container = $(".admin-polls");

    public static SelenideElement btnCreatePoll() {
        return container.find(".admin-polls-header__button");
    }

    /**
     * Check if Polls Management Page opened
     */
    @Step("Check if page opened")
    public PollsManagementPage isPageOpened() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed(), "Polls Management Page");
        return this;
    }

    /**
     * Click the button
     */
    @Step("Click the button {0}")
    public PollsManagementPage clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }
}
