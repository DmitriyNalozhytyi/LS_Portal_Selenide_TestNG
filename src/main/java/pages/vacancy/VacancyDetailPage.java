package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.*;
import constants.*;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class VacancyDetailPage {
    private final static SelenideElement pageContainer = $(".vacancy");
    
    private final String vacancyName;

    public VacancyDetailPage(String vacancyName) {
        this.vacancyName = vacancyName;
        closeVacancyApplicationWindow();
        closeColleagueRecommendationWindow();
    }

    public static SelenideElement btnApplyWithoutResume() {
        return pageContainer.find(".mat-slide-toggle-input").parent();
    }

    public static SelenideElement btnUploadCV() {
        return pageContainer.find(".upload-element.mat-button");
    }

    public static SelenideElement btnSendApplication() {
        return pageContainer.find(".response-dialog__button.vacancy-publish-button.mat-button");
    }
    
    public static SelenideElement btnAgreement() {
        return pageContainer.find(".mat-checkbox-inner-container.mat-checkbox-inner-container-no-side-margin");
    }

    public static SelenideElement btnVacancyRespond() {
        return pageContainer.find(".vacancy-buttons__respond");
    }

    public static SelenideElement btnVacancyResponses() {
        return pageContainer.find(".vacancy-header__button.vacancy-header__button_share");
    }

    public static SelenideElement btnResponseShare() {
        return pageContainer.findAll(".vacancy-cancel-button.response-dialog__button").get(0);
    }

    public static SelenideElement btnVacancyShare_VMP() {
        return pageContainer.findAll(".vacancy-header__button.vacancy-header__button_share").get(1);
    }

    public static SelenideElement btnVacancyShare() {
        return pageContainer.find(".vacancy-header__button.vacancy-header__button_share");
    }

    public static SelenideElement btnRecommendColleague() {
        return pageContainer.find(".vacancy-buttons__recommend.vacancy-save-button.mat-button");
    }

    public static SelenideElement btnSendRecommendation() {
        return pageContainer.find(".recommend-dialog__button.vacancy-publish-button.mat-button");
    }

    public static SelenideElement tabVacancyRecommendations() {
        return pageContainer.find(".mat-tab-labels").findAll(".mat-tab-label").get(1);
    }

    //SelenideElement VACANCY_RECOMMENDATIONS         = $(".mat-tab-labels").findAll(".mat-tab-label").get(1);

    public static SelenideElement inpRecommendColleagueName() {
        return pageContainer.findAll(".main-input.vacancy-input").get(0);
    }

    public static SelenideElement inpJobApplicationPhone() {
        return pageContainer.find(".form-element.form-element__first.vacancy-field").find("input");
    }

    public static SelenideElement fldAccompanyingTextValidation() {
        return pageContainer.find(".vacancy-tinymce-field");
    }

    public static SelenideElement fldAccompanyingText() {
        return pageContainer.find(".mce-tinymce.mce-container.mce-panel").find("iframe");
    }

    /**
     * Get page title
     */
    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__info-title").waitUntil(Condition.appears,10000);
    }

    /**
     * Check if page opens
     */
    @Step("Verify that page opens")
    public VacancyDetailPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(pageTitle().getText(),  vacancyName, "The title of the page" );
        return this;
    }

    /**
     * Send the filled out application form
     */
    @Step("Send the application")
    public VacancyDetailPage sendApplication() {
        clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond());
        fillTheForm();
        clickButton("Отправить", VacancyDetailPage.btnSendApplication());
        Assert.assertEquals(new MessageDialogBox().getMessage(), SuccessMessages.APPLICATION_SENT, "The message:");
        new MessageDialogBox().close();
        return this;
    }

    /**
     * Fill in the application form
     */
    @Step("Fill in the application form")
    private VacancyDetailPage fillTheForm() {
        new Actions()
                .enterText(VacancyDetailPage.inpJobApplicationPhone(), "+380698956214", "Телефон")
                .enterTextInTinyMCE(VacancyDetailPage.fldAccompanyingText(), "Сопроводительный текст", "Сопроводительный текст")
                .click(VacancyDetailPage.btnApplyWithoutResume(), "Откликнуться без резюме")
                .click(VacancyDetailPage.btnAgreement(), "Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных");
        return this;
    }

    /**
     * Click the button
     * @param name a name of the button
     * @param element selector of the element. For buttons use Button.
     */
    @Step("Click the button {0}")
    public VacancyDetailPage clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

    /**
     * Open the vacancy management page
     */
    @Step("Go to vacancy management page")
    public void openVacancyManagementPage() {
        clickButton("Управление вакансиями", Button.VACANCY_MANAGEMENT);
    }

    /**
     * Verify the status of response or recommendation
     * @param status the status of response that should be status. The list of statuses can be found in interface Status.
     */
    @Step("Verify for status {0}")
    public VacancyDetailPage checkForResponseStatus(String status) {
        Assert.assertEquals(new Table().getCellValue(1,3), status, "The status");
        return this;
    }

    /**
     * Open the dialog box of candidate response
     */
    @Step("Open candidate response dialog box")
    public VacancyDetailPage openResponseDetails() {
        new Table().getElement(1,2).click();
        Assert.assertTrue(new DialogBox().getTitle().contains(WindowTitle.RESPONSE_OF_CANDIDATE),"The window title "+ WindowTitle.RESPONSE_OF_CANDIDATE);
        return this;
    }

    /**
     * Close the dialog box of candidate response
     */
    @Step("Close candidate response dialog box")
    public VacancyDetailPage closeResponseDetails() {
        new DialogBox().close();
        return this;
    }

    /**
     * Open the dialog box of candidate response
     */
    @Step("Open candidate response dialog box")
    public VacancyDetailPage openRecommendationDetails() {
        new Table().getElement(1,2).click();
        Assert.assertTrue(new DialogBox().getTitle().contains(WindowTitle.RECOMMENDATION_OF_CANDIDATE),"The window title "+ WindowTitle.RESPONSE_OF_CANDIDATE);
        return this;
    }

    /**
     * Close the dialog box of candidate response
     */
    @Step("Close candidate response dialog box")
    public VacancyDetailPage closeRecommendationDetails() {
        new DialogBox().close();
        return this;
    }

    /**
     * Actions for response
     * @param action the action for response. For actions use ResponseActions.
     */
    @Step("Select action {0}")
    public VacancyDetailPage responseActions(ResponseActions action) {
        switch (action) {
            case DECLINE:       declineResponse(); break;
            case ON_APPROVAL:   clickButton("На рассмотрение", Button.RESPONSE_ON_APPROVAL); break;
            case ACCEPT:        clickButton("Кандидат принят", Button.RESPONSE_CANDIDATE_ACCEPT); break;
        }

        new DialogBox().waitForClose();
        return this;
    }

    /**
     * Decline response
     */
    private void declineResponse() {
        clickButton("Отклонить", Button.RESPONSE_DECLINE);
        new Actions().selectRadioButton(Button.MISMATCHED_QUALIFICATION, "Опыт и квалификация кандидата не соответствуют заявленным требованиям к должности1","Выберите причину отклонения");
        clickButton("Отправить", VacancyDetailPage.btnSendApplication());
    }

    /**
     * Recommend a colleague
     * @param name the name of colleague to recommend
     */
    @Step("Recommend {0}")
    public VacancyDetailPage recommendColleague(String name) {
        clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague());
        new ColleagueRecommendationDialogBox()
                .isPageOpens()
                .selectColleague(name)
                .setValueFor("Телефон", "+380985987421", VacancyDetailPage.inpJobApplicationPhone())
                .setTextInMultiLine("Сопроводительный текст", "Сопроводительный текст", VacancyDetailPage.fldAccompanyingText())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation());

        Assert.assertEquals(new MessageDialogBox().getMessage(), SuccessMessages.RECOMMENDATION_SENT, "The message");
        new MessageDialogBox().close();

        return this;
    }

    /**
     * Verify if the candidate in the list of candidates
     * @param name the name of candidate. The list of candidates can be found in Data.
     */
    @Step("Check if recommendation added")
    public void checkIfRecommendationAdded(String name) {
        clickButton("Отклики", VacancyDetailPage.btnVacancyResponses());
        openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations());
        Assert.assertEquals(new Table().getCellValue(1,1), name, "The candidate ");
    }

    /**
     *  Open tabs oon the vacancy detail page
     * @param name name of tab "Рекомендации", "Отклики"
     * @param element selector of the element. For Tabs use Tabs.
     */
    @Step("Open tab {0}")
    public VacancyDetailPage openTab(String name, SelenideElement element) {
        clickButton(name, element);
        return this;
    }

    /**
     * Close the vacancy application window
     */
    public VacancyDetailPage closeVacancyApplicationWindow() {
        sleep(500);
        if (Button.VACANCY_APPLICATION_CANCEL.exists()) {
            new Actions().click(Button.VACANCY_APPLICATION_CANCEL, "Отменить");
        }
        return this;
    }

    public VacancyDetailPage closeColleagueRecommendationWindow() {
        sleep(500);
        if (Button.VACANCY_RECOMMENDATION_CANCEL.exists()) {
            new Actions().click(Button.VACANCY_RECOMMENDATION_CANCEL, "Отменить");
        }
        return this;
    }

    /**
     * Enter the text into Tiny MCE editor
     * @param field field name
     * @param text the text that should be inserted
     * @param element selector of the element. For Tiny MCE editor use Fields.
     */
    @Step("Enter the text {1} into {0}")
    public VacancyDetailPage setTinyMCEText(String field, String text, SelenideElement element) {
        new Actions().enterTextInTinyMCE(element, text, field);
        return this;
    }

    /**
     * Check field validation
     * @param field field name like "Телефон"
     * @param element Selenide element to find this field
     */
    @Step("Check the validation message for {0}")
    public VacancyDetailPage checkValidationMessage(String field, String expectedValidationMessage, SelenideElement element) {
            String actualValidationMessage = element.parent().find("mat-error").getText();
            Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Thr message for "+field);
        return this;
    }

    /**
     * Check if validation message absence. It uses for cases when it is need to check that validation message is not appeared. E.g.
     * @param field field name
     * @param element Selenide element to find this field
     */
    @Step("Check the validation message absence for {0}")
    public VacancyDetailPage checkValidationMessageAbsence(String field, SelenideElement element) {
        Assert.assertFalse(element.parent().find("mat-error").exists(), "The validation message for " + field);
        return this;
    }


    public void vacancyAction(VacancyAction action) {
        switch (action) {
            case COPY:      clickButton("Копироать Вакансию", Button.COPY_VACANCY_ON_VDP); break;
            case EDIT:      clickButton("Копироать Вакансию", Button.EDIT_VACANCY_ON_VDP); break;
            case DELETE:
                clickButton("Удалить Вакансию", Button.DELETE_VACANCY_ON_VDP);
                new ConfirmDialogBox().confirm(true);
                break;
            case DELETE_CLOSED_VACANCY:
                clickButton("Удалить Вакансию", Button.DELETE_CLOSED_VACANCY_ON_VDP);
                new ConfirmDialogBox().confirm(true);
                break;
        }
    }
}
