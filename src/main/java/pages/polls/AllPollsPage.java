package pages.polls;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AllPollsPage {
    private final SelenideElement container = $(".all-polls");

    private SelenideElement tbPassed() {
        return listOfTabs().get(1);
    }

    private SelenideElement tbNotPassed() {
        return listOfTabs().get(0);
    }

    private ElementsCollection listOfTabs() {
        return container.find(".mat-tab-labels").findAll(".mat-tab-label");
    }

    public enum Tab {NOT_PASSED, PASSED}


    /**
     * Check if the page All Polls opened
     */
    @Step("Check if the page All Polls opened")
    public AllPollsPage isPageOpened() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofMinutes(3)).isDisplayed(), "Polls");
        return this;
    }

    /**
     * Switch to tab PASSED, NOT_PASSED
     * @param tab name of tab PASSED, NOT_PASSED
     */
    @Step("Switch to {0}")
    public AllPollsPage switchTo(Tab tab) {
        switch (tab) {
            case PASSED:        clickButton("Пройденные", tbPassed()); break;
            case NOT_PASSED:    clickButton("Непройденные", tbNotPassed()); break;
        }
        return this;
    }

    /**
     * Click the button
     * @param name text on the button
     * @param element Selenide element to find this button
     */
    @Step("Click the button {0}")
    public AllPollsPage clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

    /**
     * Check if poll with name in the list of polls
     * @param name name of the poll
     */
    @Step("Check if poll {0} presents in the list")
    public void checkIfPollPresent(String name) {
        SelenideElement pollTitle = container.find(".body-title").should(Condition.appear, Duration.ofMinutes(1));
        SelenideElement btnNextPoll = container.find(".all-polls-actions").findAll("button").get(1).should(Condition.appear, Duration.ofMinutes(1));

        while(!pollTitle.getText().equals(name)) {
            if (!btnNextPoll.isEnabled()) {
                btnNextPoll.click();
            } else break;
        }

        sleep(2000);
        Assert.assertEquals(name, pollTitle.getText(), name);
    }

}
