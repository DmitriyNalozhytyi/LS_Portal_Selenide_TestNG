package vacancy.validation;

import constants.SiteMenu;
import constants.USERS;
import constants.ValidationMessage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Vacancy")
@Feature("Responses and Recommendations")
public class ColleagueRecommendationFormValidationTest extends ParentTest {

    private final String vacancyName = "FIELD_VALIDATION_"+CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @BeforeClass(description = "Create a vacancy")
    public void createVacancyForFieldValidation() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
        new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);
    }

    @Story("Empty field validation for Colleague Recommendation form")
    @Test(description = "Check if empty field name leads to the validation")
    public void phoneFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeColleagueRecommendationWindow()
                .clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .checkValidationMessage("ФИО", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.inpRecommendColleagueName())
                .closeColleagueRecommendationWindow();
    }

    @Story("Empty field validation for Colleague Recommendation form")
    @Test(description = "Check if empty field phone leads to the validation")
    public void cvFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeColleagueRecommendationWindow()
                .clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .checkValidationMessage("Телефон", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.inpJobApplicationPhone())
                .closeColleagueRecommendationWindow();
    }

    @Story("Empty field validation for Colleague Recommendation form")
    @Test(description = "Check if empty field accompanying leads to the validation")
    public void accompanyingFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeColleagueRecommendationWindow()
                .clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.fldAccompanyingTextValidation())
                .closeColleagueRecommendationWindow();
    }

    @Story("Number of characters validation for Colleague Recommendation form")
    @Test(description = "Check that more than maximum number of characters of accompanying text leads to the validation")
    public void accompanyingFieldValidation_MaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeColleagueRecommendationWindow()
                .clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5001), VacancyDetailPage.fldAccompanyingText())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.MAXIMUM_CHARACTERS_5000, VacancyDetailPage.fldAccompanyingTextValidation())
                .closeColleagueRecommendationWindow();
    }

    @Story("Number of characters validation for Colleague Recommendation form")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Check that less than maximum number of characters of accompanying text doesn't lead to the validation")
    public void accompanyingFieldValidation_LessThanMaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeColleagueRecommendationWindow()
                .clickButton("Рекомендовать коллегу", VacancyDetailPage.btnRecommendColleague())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5000), VacancyDetailPage.fldAccompanyingText())
                .clickButton("Отправить", VacancyDetailPage.btnSendRecommendation())
                .checkValidationMessageAbsence("Сопроводительный текст", VacancyDetailPage.fldAccompanyingTextValidation())
                .closeColleagueRecommendationWindow();
    }

}
