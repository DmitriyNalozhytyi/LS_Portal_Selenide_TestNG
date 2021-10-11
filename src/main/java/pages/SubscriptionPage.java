package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.SUBSCRIPTION;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SubscriptionPage extends ParentPage{
    private final SelenideElement container = $("app-subscriptions");

    private ElementsCollection listOfTabs() {
        return container.find(".mat-tab-labels").findAll(".mat-tab-label");
    }

    private SelenideElement tbTag() {
        return listOfTabs().get(2);
    }

    private SelenideElement listOfTags() {
        return container.find(".settings-wrapper").should(Condition.appear,Duration.ofSeconds(60));
    }

    /**
     * Check if page opens
     */
    @Step("Check if tooltip page opened")
    public SubscriptionPage isPageOpens() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).exists(), "Tooltip page");
        return this;
    }

    /**
     * Switch to tab. List of tabs you cac find in the SUBSCRIPTION: TAGS
     * @param subscription the name of tab
     */
    @Step("Switch to {0}")
    public SubscriptionPage switchTo(SUBSCRIPTION subscription) {
        switch (subscription) {
            case TAGS: tbTag().click();
        }
        return this;
    }


    /**
     * Get list of tags from subscription page from tags tab
     * @return List<String> list of tags
     */
    @Step("Get list of tags")
    public List<String> getAllTags() {
        return listOfTags().findAll(".settings-item__title").texts();
    }
}
