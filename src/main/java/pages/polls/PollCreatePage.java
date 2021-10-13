package pages.polls;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PollCreatePage {
    private static final SelenideElement container = $(".poll");

    public static SelenideElement btnSavePoll() {
        return container.find(".poll-save-button");
    }

    public static SelenideElement btnSaveAndPublishPoll() {
        return container.find(".poll-publish-button");
    }

    public static SelenideElement btnDisplayOnMainPage() {
        return container.find(".toggle-block.mat-slide-toggle.mat-accent.poll-field.poll-slider");
    }

    public static SelenideElement btnMetInvestGroup() {
        return container.find(".poll-slider-isMetinvest");
    }

    private SelenideElement btnOneButton() {
        return getAnswerButtons().get(0);
    }

    public static SelenideElement btnAddNewAnswer() {
        return getGeneralInformationSection().find(".add-new");
    }

    public static SelenideElement inputPollTitle() {
        return getGeneralInformationSection().find(".main-input.poll-input");
    }

    public SelenideElement inputAnswer(int i) {
        if (!getGeneralInformationSection().findAll(".input-container").get(i-1).isDisplayed()) {
            clickButton("Add a new answer", btnAddNewAnswer());
        }
        return getGeneralInformationSection().findAll(".input-container").get(i-1).find("input");
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

    private ElementsCollection getAnswerButtons() {
        return $$(".radio-button");
    }

    private SelenideElement tbRU() {
        return getAllLanguageTab().get(1);
    }

    private SelenideElement tbUA() {
        return getAllLanguageTab().get(0);
    }

    private ElementsCollection getAllLanguageTab() {
        return getTabContainer().findAll(".mat-tab-label");
    }

    private SelenideElement getTabContainer() {
        return getGeneralInformationSection().find(".mat-tab-labels");
    }

    private static SelenideElement getGeneralInformationSection() {
        return container.find(".mat-tab-group");
    }

    public enum ANSWER {ONE_ANSWER}
    public enum FieldValidation {TARGET_AUDIENCE, ANSWER_TYPE}

    /**
     * Select type of answer
     * @param answer the type of answer: ONE_ANSWER, MULTIPLE_ANSWERS
     */
    @Step("Select type of answer {0}")
    public PollCreatePage selectAnswer(ANSWER answer) {
        switch (answer) {
            case ONE_ANSWER: clickButton("Один Вариант", btnOneButton());
        }
        return this;
    }

    /**
     * Toggle a button.
     * @param name string field name
     * @param element Selenide element to indicate this button
     * @param check boolean. true - if this button should be switched on, false - if this button should be switched off
     */
    @Step("Toggle the button {0}")
    public PollCreatePage toggle(String name, SelenideElement element, boolean check) {
        boolean isChecked = element.getAttribute("class").contains("met-checked");

        if ((check && !isChecked) || (!check && isChecked)) {
            clickButton(name, element.find(".mat-slide-toggle-bar"));
        }
        return this;
    }

    /**
     * Switch tabs on Poll Create page
     * @param language tab name like "RU", "UA"
     */
    @Step("Switch to tab {0}")
    public PollCreatePage switchToLanguage(Language language) {
        switch (language) {
            case RU: clickButton("RU", tbRU()); break;
            case UA: clickButton("UA", tbUA()); break;
        }
        return this;
    }

    /**
     * Enter text in the input field
     * @param fieldName the name of field that should be displayed in allure report
     * @param element selector to find input field
     * @param text text that should be entered in the input field
     */
    @Step("Enter the text '{2}' into the field '{0}'")
    public PollCreatePage setTextFor(String fieldName, SelenideElement element, String text) {
        new Actions().enterText(element, text, fieldName);
        return this;
    }

    /**
     * Check validation message for required fields
     * @param fieldValidation field that should be verified
     * @param message the message that should be appeared
     */
    @Step("Check validation message for {0}")
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
