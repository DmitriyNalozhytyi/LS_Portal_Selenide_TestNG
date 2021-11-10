package pages.notifacations;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class NotificationPage {
    private static final SelenideElement container = $("app-notification-list").should(Condition.appear, Duration.ofMinutes(5));

    enum Period {
        TODAY,LATER
    }
    private SelenideElement getNotificationContainer() {
        return container.find(".notifications-wrapper");
    }

    private ElementsCollection getAllNotification() {
        return getNotificationContainer().findAll(".notifications-list");
    }

    private SelenideElement getAllNotificationFor(Period period) {
        switch(period) {
            case TODAY: return getAllNotification().get(0).should(Condition.appear, Duration.ofMinutes(3));
            default: return null;
        }
    }

    private SelenideElement searchNotification(String text) {
        return getAllNotificationFor(Period.TODAY).findAll(".notification-description__subtext").find(Condition.exactText(text));
    }

    /**
     * Check if page opened
     */
    @Step("Check if page opened")
    public NotificationPage isPageOpened() {
        Assert.assertTrue(getAllNotificationFor(Period.TODAY).isDisplayed(),"Notification page");
        return this;
    }

    /**
     * Check if notification is present
     * @param text text for notification
     */
    @Step("Check if notification ")
    public NotificationPage checkIfNotificationIsPresent(String text) {
        Assert.assertTrue(searchNotification(text).isDisplayed(), text);
        return this;
    }

    /**
     * Open vacancy
     * @param name the name of the vacancy
     */
    @Step("Open vacancy {0}")
    public void openVacancy(String name) {
        searchNotification(name).click();
    }
}
