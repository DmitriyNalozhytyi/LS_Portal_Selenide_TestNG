package pages.polls;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class PollCreatePage {
    private static final SelenideElement container = $(".poll");

    public static SelenideElement btnSavePoll() {
        return container.find(".poll-save-button");
    }

    private SelenideElement getAnswerTypeContainer() {
        return container.find(".text-field-wrapper.poll-field.poll-field-radio");
    }

    private SelenideElement getTargetAudienceContainer() {
        return container.find(".datapicker-field-wrapper.poll-field");
    }

    private SelenideElement getAnswerTypeErrorContainer() {
        return getAnswerTypeContainer().find(".mat-error");
    }

    private SelenideElement getTargetAudienceErrorContainer() {
        return getTargetAudienceContainer().find(".mat-error");
    }

    public enum FieldValidation {TARGET_AUDIENCE, ANSWER_TYPE}

    public void checkValidationMessageFor(FieldValidation fieldValidation, String message) {
        switch (fieldValidation) {
            case ANSWER_TYPE: Assert.assertEquals(getAnswerTypeErrorContainer().getText(), message, message); break;
            case TARGET_AUDIENCE: Assert.assertEquals(getTargetAudienceErrorContainer().getText(), message, message); break;
        }
    }

    /**
     * Check if Poll Crete Page opened
     */
    @Step("Check if page opened")
    public PollCreatePage isPageOpened() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed(), "Polls Management Page");
        return this;
    }

    /**
     * Click the button
     */
    @Step("Click the button {0}")
    public PollCreatePage clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

}
