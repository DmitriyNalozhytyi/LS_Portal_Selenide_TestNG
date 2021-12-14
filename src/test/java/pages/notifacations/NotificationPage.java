package pages.notifacations;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.notification.REJECTION_REASON;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
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
        return getAllNotificationFor(Period.TODAY).findAll(".notification-description__subtext").find(Condition.exactText(text)).isDisplayed()
                ? getAllNotificationFor(Period.TODAY).findAll(".notification-description__subtext").find(Condition.exactText(text))
                : getAllNotificationFor(Period.TODAY).findAll(".notification-description__text").find(Condition.exactText(text));
    }

    private ElementsCollection searchNotifications(String text) {
        return getAllNotificationFor(Period.TODAY).findAll(withText(text));
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
     * Click on the notification
     * @param text the text of the notification
     */
    @Step("Open vacancy {0}")
    public void clickOnNotification(String text) {
        searchNotification(text).click();
    }

    /**
     * Check notification for declined response
     * @param name name of notification
     * @param rejectionReason reason why it was rejected
     * @param text text in notification
     */
    public NotificationPage checkNotificationForDeclinedResponse(String name, REJECTION_REASON rejectionReason, String text) {
        String  expectedReason = "";
        boolean find = false;

        switch (rejectionReason) {
            case MISMATCHED_QUALIFICATION: expectedReason = "На данный момент мы не готовы предложить Вам данную вакансию, поскольку Ваш опыт и квалификация, к сожалению, не соответствуют заявленным требованиям к должности."; break;
            case OTHER_REASON:             expectedReason = text; break;
        }

        for (int i = 0; i <= searchNotifications(name).size(); i++) {
            if (searchNotifications(name).get(i).parent().find(".notification-description__text.notification-comment")
                    .getText().contains(expectedReason)) {
                find = true;
                break;
            }
        }

        Assert.assertTrue(find, expectedReason);

        return this;
    }

    public void openRejectedVacancy(String name, REJECTION_REASON rejectionReason, String text) {
        String  expectedReason = "";

        switch (rejectionReason) {
            case MISMATCHED_QUALIFICATION: expectedReason = "На данный момент мы не готовы предложить Вам данную вакансию, поскольку Ваш опыт и квалификация, к сожалению, не соответствуют заявленным требованиям к должности."; break;
            case OTHER_REASON: expectedReason = text; break;
            case ANOTHER_CANDIDATE: expectedReason = "На данный момент мы не готовы предложить данную вакансию рекомендованному Вами кандидату, поскольку принято решение в пользу другого кандидата."; break;
        }

        for (int i = 0; i <= searchNotifications(name).size(); i++) {
            if (searchNotifications(name).get(i).parent()
                    .find(".notification-description__text.notification-comment").should(Condition.appear, Duration.ofMinutes(1))
                    .getText().contains(expectedReason)) {
                searchNotifications(name).get(i).click();
                break;
            }
        }
    }

}
