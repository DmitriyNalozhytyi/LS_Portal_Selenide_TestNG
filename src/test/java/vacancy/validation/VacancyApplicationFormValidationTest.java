package vacancy.validation;

import constants.*;
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
public class VacancyApplicationFormValidationTest extends ParentTest {

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

    @Story("Empty field validation for Vacancy Application form")
    @Test(description = "Check if empty field phone leads to the validation")
    public void phoneFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond())
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", VacancyDetailPage.btnAgreement())
                .clickButton("Отправить", VacancyDetailPage.btnSendApplication())
                .checkValidationMessage("Телефон", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.inpJobApplicationPhone())
                .closeVacancyApplicationWindow();

    }

    @Story("Empty field validation for Vacancy Application form")
    @Test(description = "Check if empty field CV leads to the validation")
    public void cvFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeVacancyApplicationWindow()
                .clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond())
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", VacancyDetailPage.btnAgreement())
                .clickButton("Отправить", VacancyDetailPage.btnSendApplication())
                .checkValidationMessage("Телефон", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.btnUploadCV())
                .closeVacancyApplicationWindow();
    }

    @Story("Empty field validation for Vacancy Application form")
    @Test(description = "Check if empty field accompanying leads to the validation")
    public void accompanyingFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeVacancyApplicationWindow()
                .clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond())
                .clickButton("Откликнуться без резюме", VacancyDetailPage.btnApplyWithoutResume())
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", VacancyDetailPage.btnAgreement())
                .clickButton("Отправить", VacancyDetailPage.btnSendApplication())
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.REQUIRED_FIELD, VacancyDetailPage.fldAccompanyingTextValidation())
                .closeVacancyApplicationWindow();
    }

    @Story("Number of characters validation for Vacancy Application form")
    @Test(description = "Check that more than maximum number of characters of accompanying text leads to the validation")
    public void accompanyingFieldValidation_MaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeVacancyApplicationWindow()
                .clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond())
                .clickButton("Откликнуться без резюме", VacancyDetailPage.btnApplyWithoutResume())
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", VacancyDetailPage.btnAgreement())
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5001), VacancyDetailPage.fldAccompanyingText())
                .clickButton("Отправить", VacancyDetailPage.btnSendApplication())
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.MAXIMUM_CHARACTERS_5000, VacancyDetailPage.fldAccompanyingTextValidation())
                .closeVacancyApplicationWindow();
    }

    @Story("Number of characters validation for Vacancy Application form")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Check that less than maximum number of characters of accompanying text doesn't lead to the validation")
    public void accompanyingFieldValidation_LessThanMaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .closeVacancyApplicationWindow()
                .clickButton("Откликнуться", VacancyDetailPage.btnVacancyRespond())
                .clickButton("Откликнуться без резюме", VacancyDetailPage.btnApplyWithoutResume())
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", VacancyDetailPage.btnAgreement())
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5000), VacancyDetailPage.fldAccompanyingText())
                .clickButton("Отправить", VacancyDetailPage.btnSendApplication())
                .checkValidationMessageAbsence("Сопроводительный текст", VacancyDetailPage.fldAccompanyingTextValidation())
                .closeVacancyApplicationWindow();
    }

}
