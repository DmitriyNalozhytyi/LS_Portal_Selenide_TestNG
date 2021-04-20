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
    private final SelenideElement pageContainer = $(".vacancy");
    
    private final String vacancyName;

    public VacancyDetailPage(String vacancyName) {
        this.vacancyName = vacancyName;
        closeVacancyApplicationWindow();
        closeColleagueRecommendationWindow();
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
        clickButton("Откликнуться", Button.VACANCY_RESPOND);
        fillTheForm();
        clickButton("Отправить", Button.SEND_APPLICATION);
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
                .enterText(Fields.JOB_APPLICANT_PHONE, "+380698956214", "Телефон")
                .enterTextInTinyMCE(Fields.ACCOMPANYING_TEXT, "Сопроводительный текст", "Сопроводительный текст")
                .click(Button.APPLY_WITHOUT_RESUME, "Откликнуться без резюме")
                .click(Button.AGREEMENT, "Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных");
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
        clickButton("Отправить", Button.SEND_APPLICATION);
    }

    /**
     * Recommend a colleague
     * @param name the name of colleague to recommend
     */
    @Step("Recommend {0}")
    public VacancyDetailPage recommendColleague(String name) {
        clickButton("Рекомендовать коллегу", Button.RECOMMEND_COLLEAGUE);
        new ColleagueRecommendationDialogBox()
                .isPageOpens()
                .selectColleague(name)
                .setValueFor("Телефон", "+380985987421", Fields.JOB_APPLICANT_PHONE)
                .setTextInMultiLine("Сопроводительный текст", "Сопроводительный текст", Fields.ACCOMPANYING_TEXT)
                .clickButton("Отправить", Button.SEND_RECOMMENDATION);

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
        clickButton("Отклики", Button.VACANCY_RESPONSES);
        openTab("Рекомендации", Tabs.VACANCY_RECOMMENDATIONS);
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




}
