package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.*;
import config.Config;
import constants.ResponseActions;
import constants.SuccessMessages;
import constants.USERS;
import constants.VacancyAction;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

/**
 * Class to work with Vacancy detail Page like https://metinvest-intranet-test.azurewebsites.net/ru/vacancy/0be36181-5a55-422e-d15a-08d9a362dbaa
 */
public class VacancyDetailPage {
    private final static String RECOMMENDATION_OF_CANDIDATE_PAGE_TITLE  = "Рекомендация кандидата:";
    private final static String RESPONSE_OF_CANDIDATE_PAGE              = "Отклик кандидата:";
    private final static SelenideElement pageContainer                  = $(".vacancy");
    
    private String vacancyName;

    public VacancyDetailPage(String vacancyName) {
        this.vacancyName = vacancyName;
        closeVacancyApplicationWindow();
        closeColleagueRecommendationWindow();
    }

    public VacancyDetailPage() {
    }

    public static SelenideElement btnApplyWithoutResume() {
        return $(".mat-slide-toggle-input").parent();
    }

    public static SelenideElement btnUploadCV() {
        return pageContainer.find(".upload-element.mat-button");
    }

    public static SelenideElement btnSendApplication() {
        return $(".response-dialog__button.vacancy-publish-button.mat-button");
    }
    
    public static SelenideElement btnAgreement() {
        return $(".mat-checkbox-inner-container.mat-checkbox-inner-container-no-side-margin");
    }

    public static SelenideElement btnVacancyRespond() {
        return pageContainer.find(".vacancy-buttons__respond");
    }

    public static SelenideElement btnVacancyResponses() {
        return pageContainer.find(".vacancy-header__button.vacancy-header__button_share");
    }

    public static SelenideElement btnResponseShare() {
        return $$(".vacancy-cancel-button.response-dialog__button").get(0);
    }

    public static SelenideElement btnVacancyShare_VMP() {
        return $$(".vacancy-header__button.vacancy-header__button_share").get(1);
    }

    public static SelenideElement btnVacancyShare() {
        return $(".vacancy-header__button.vacancy-header__button_share");
    }

    public static SelenideElement btnRecommendColleague() {
        return $(".vacancy-buttons__recommend.vacancy-save-button.mat-button");
    }

    public static SelenideElement btnSendRecommendation() {
        return $(".recommend-dialog__button.vacancy-publish-button.mat-button");
    }

    public static SelenideElement btnVacancyManagement() {
        return pageContainer.find(".vacancy-nav-menu").findAll(".vacancy-nav-menu__item").get(1);
    }

    public static SelenideElement btnResponseDecline() {
        return pageContainer.find(".vacancy-cancel-button.response-dialog__button.response-dialog__button_cancel.mat-button.ng-star-inserted");
    }

    public static SelenideElement btnMismatchedQualification() {
        return pageContainer.find(".reject-card__radiobuttons.mat-radio-group").findAll("mat-radio-button").get(0);
    }

    public static SelenideElement btnResponseOnApproval() {
        return $(".response-dialog__button.vacancy-publish-button");
    }

    public static SelenideElement btnResponseCandidateAccept() {
        return $(".response-dialog__button.vacancy-publish-button");
    }

    public static SelenideElement btnVacancyApplicationCancel() {
        return $(".vacancy-cancel-button.response-dialog__button.response-dialog__button_cancel");
    }

    public static SelenideElement btnVacancyRecommendationCancel() {
        return $(".vacancy-cancel-button.recommend-dialog__button.recommend-dialog__button_cancel");
    }

    public static SelenideElement btnCopyVacancy() {
        return pageContainer.find(".vacancy-header__info-title").findAll(".icon.icon-frame").get(0);
    }

    public static SelenideElement btnEditVacancy() {
        return pageContainer.find(".vacancy-header__info-title").findAll(".icon.icon-frame").get(1);
    }

    public static SelenideElement btnDeleteVacancy() {
        return pageContainer.find(".vacancy-header__info-title").findAll(".icon.icon-frame").get(2);
    }

    public static SelenideElement btnDeleteClosedVacancy() {
        return pageContainer.find(".vacancy-header__info-title").findAll(".icon.icon-frame").get(1);
    }

    public static SelenideElement tabVacancyRecommendations() {
        return $(".mat-tab-labels").findAll(".mat-tab-label").get(1);
    }

    public static SelenideElement inpRecommendColleagueName() {
        return $$(".main-input.vacancy-input").get(0);
    }

    public static SelenideElement inpJobApplicationPhone() {
        return $(".form-element.form-element__first.vacancy-field").find("input");
    }

    public static SelenideElement fldAccompanyingTextValidation() {
        return $(".vacancy-tinymce-field");
    }

    public static SelenideElement fldAccompanyingText() {
        return $(".mce-tinymce.mce-container.mce-panel").find("iframe");
    }

    private ElementsCollection getData() {
        return $$(".info-card__field-value");
    }

    private SelenideElement getEmail() {
        return getData().get(1);
    }

    /**
     * Get page title
     */
    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__info-title").should(Condition.appear, Duration.ofSeconds(10));
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
    public VacancyDetailPage sendRespond() {
        clickButton("Откликнуться", btnVacancyRespond());
        fillTheForm();
        clickButton("Отправить", btnSendApplication());
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
                .enterText(inpJobApplicationPhone(), "+380698956214", "Телефон")
                .enterTextInTinyMCE(fldAccompanyingText(), "Сопроводительный текст", "Сопроводительный текст")
                .click(btnApplyWithoutResume(), "Откликнуться без резюме")
                .click(btnAgreement(), "Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных");
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
        clickButton("Управление вакансиями", btnVacancyManagement());
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
        Assert.assertTrue(new DialogBox().getTitle().contains(RESPONSE_OF_CANDIDATE_PAGE),"The window title "+ RESPONSE_OF_CANDIDATE_PAGE);
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
        Assert.assertTrue(new DialogBox().getTitle().contains(RECOMMENDATION_OF_CANDIDATE_PAGE_TITLE),"The window title "+ RESPONSE_OF_CANDIDATE_PAGE);
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
            case ON_APPROVAL:   clickButton("На рассмотрение", btnResponseOnApproval()); break;
            case ACCEPT:        clickButton("Кандидат принят", btnResponseCandidateAccept()); break;
        }

        new DialogBox().waitForClose();
        return this;
    }

    /**
     * Decline response
     */
    private void declineResponse() {
        clickButton("Отклонить", btnResponseDecline());
        new Actions().selectRadioButton(VacancyDetailPage.btnMismatchedQualification(), "Опыт и квалификация кандидата не соответствуют заявленным требованиям к должности1","Выберите причину отклонения");
        clickButton("Отправить", btnSendApplication());
    }

    /**
     * Recommend a colleague
     * @param name the name of colleague to recommend
     */
    @Step("Recommend {0}")
    public VacancyDetailPage recommendColleague(String name) {
        clickButton("Рекомендовать коллегу", btnRecommendColleague());
        new ColleagueRecommendationDialogBox()
                .isPageOpens()
                .selectColleague(name)
                .setValueFor("Телефон", "+380985987421", inpJobApplicationPhone())
                .setTextInMultiLine("Сопроводительный текст", "Сопроводительный текст", fldAccompanyingText())
                .clickButton("Отправить", btnSendRecommendation());

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
        clickButton("Отклики", btnVacancyResponses());
        openTab("Рекомендации", tabVacancyRecommendations());
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
        if (btnVacancyApplicationCancel().exists()) {
            new Actions().click(btnVacancyApplicationCancel(), "Отменить");
        }
        return this;
    }

    public VacancyDetailPage closeColleagueRecommendationWindow() {
        sleep(500);
        if (btnVacancyRecommendationCancel().exists()) {
            new Actions().click(btnVacancyRecommendationCancel(), "Отменить");
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


    /**
     * Action on vacancy
     * @param action action for vacancy COPY, DELETE, EDIT, DELETE_CLOSED_VACANCY
     */
    @Step("{0} vacancy")
    public void vacancyAction(VacancyAction action) {
        switch (action) {
            case COPY:      clickButton("Копироать Вакансию", btnCopyVacancy()); break;
            case EDIT:      clickButton("Редактировать Вакансию", btnEditVacancy()); break;
            case DELETE:
                clickButton("Удалить Вакансию", btnDeleteVacancy());
                new ConfirmDialogBox().confirm(true);
                break;
            case DELETE_CLOSED_VACANCY:
                clickButton("Удалить Вакансию", btnDeleteClosedVacancy());
                new ConfirmDialogBox().confirm(true);
                break;
        }
    }

    /**
     * Check if opened response contains email of relevant user
     * @param user user which email should be checked
     */
    @Step("Verify that response dialog display the user {0} ")
    public void checkForOpenedResponse(USERS user) {
        String actualEmail = getEmail().getText();
        String expectedEmail = "";
        switch (user) {
            case DEV_TESTUSER13: expectedEmail = Config.HostsData.METINVEST.value[13];
        }
        Assert.assertEquals(actualEmail, expectedEmail, expectedEmail);
        closeResponseDetails();
    }


}
