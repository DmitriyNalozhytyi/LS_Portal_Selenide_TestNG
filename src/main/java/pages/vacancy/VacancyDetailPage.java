package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.*;
import constants.*;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class VacancyDetailPage {
    private final SelenideElement pageContainer = $(".vacancy");
    
    private final String vacancyName;

    public VacancyDetailPage(String vacancyName) {
        this.vacancyName = vacancyName;
    }

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
    public VacancyDetailPage openResponseWindow() {
        new Table().getElement(1,2).click();
        Assert.assertTrue(new DialogBox().getTitle().contains(WindowTitle.RESPONSE_OF_CANDIDATE),"The window title "+ WindowTitle.RESPONSE_OF_CANDIDATE);
        return this;
    }

    /**
     * Close the dialog box of candidate response
     */
    @Step("Close candidate response dialog box")
    public VacancyDetailPage closeResponseWindow() {
        new DialogBox().close();
        return this;
    }

    /**
     * Open the dialog box of candidate response
     */
    @Step("Open candidate response dialog box")
    public VacancyDetailPage openRecommendationWindow() {
        new Table().getElement(1,2).click();
        Assert.assertTrue(new DialogBox().getTitle().contains(WindowTitle.RECOMMENDATION_OF_CANDIDATE),"The window title "+ WindowTitle.RESPONSE_OF_CANDIDATE);
        return this;
    }

    /**
     * Close the dialog box of candidate response
     */
    @Step("Close candidate response dialog box")
    public VacancyDetailPage closeRecommendationWindow() {
        new DialogBox().close();
        return this;
    }

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
}
