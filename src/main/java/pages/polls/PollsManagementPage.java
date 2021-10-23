package pages.polls;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.PagePreLoader;
import components.Pagination;
import components.Table;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.MainPage;
import pages.ParentPage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class PollsManagementPage extends ParentPage {
    private static final SelenideElement container = $(".admin-polls");
    int deleteElement                     = 1;

    public static SelenideElement btnCreatePoll() {
        return container.find(".admin-polls-header__button");
    }

    private SelenideElement findPoll(String name) {
        SelenideElement find = $(withText(name));

        int count = new Pagination().countOfPages();

        for (int i = 1; i<=count; i++) {
            if ($(withText(name)).isDisplayed()) {
                find = new Table().getRowByValue(name);
                break;
            }
            new Pagination().gotToPage(i);
        }

        return find;
    }

    private SelenideElement getActionButtonFor(String name, int action) {
        SelenideElement find = findPoll(name).parent().parent().parent();
        return find.findAll("td").get(4).findAll("button").get(action);
    }

    /**
     * Check if Polls Management Page opened
     */
    @Step("Check if page opened")
    public PollsManagementPage isPageOpened() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofMinutes(5)).isDisplayed(), "Polls Management Page");
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

    /**
     * Check if tooltip was created
     * @param language the language of the portal
     * @param pollName the name of tooltip
     */
    @Step("Check if poll was created {1}")
    public PollsManagementPage checkIfPollCreated(Language language, String pollName) {
        new MainPage().switchAppToLang(language);
        new PagePreLoader().waitToLoad();

        Assert.assertTrue(findPoll(pollName).should(Condition.appear, Duration.ofSeconds(60)).isDisplayed(), pollName);
        return this;
    }

    /**
     * Delete poll
     * @param language the language of the portal
     * @param pollName the name of tooltip
     */
    @Step("Delete poll {1}")
    public PollsManagementPage deletePoll(Language language, String pollName) {
        new MainPage().switchAppToLang(language);
        sleep(2000);
        getActionButtonFor(pollName, deleteElement).click();
        new ConfirmDialogBox().confirm(true);
        return this;
    }

    /**
     * Check if tooltip was deleted
     * @param language the language of the portal
     * @param pollName the name of tooltip
     */
    @Step("Check if tooltip was deleted")
    public PollsManagementPage checkIfPollDeleted(Language language, String pollName) {
        new MainPage().switchAppToLang(language);
        sleep(2000);
        new PagePreLoader().waitToLoad();
        Assert.assertFalse(findPoll(pollName).isDisplayed(), pollName);
        return this;
    }
}
