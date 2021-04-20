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
        new MainPage().goToVacancyManagementPage();

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
                .clickButton("Откликнуться", Button.VACANCY_RESPOND)
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", Button.AGREEMENT)
                .clickButton("Отправить", Button.SEND_APPLICATION)
                .checkValidationMessage("Телефон", ValidationMessage.REQUIRED_FIELD, Fields.JOB_APPLICANT_PHONE)
                .closeVacancyApplicationWindow();

    }

    @Story("Empty field validation for Vacancy Application form")
    @Test(description = "Check if empty field CV leads to the validation")
    public void cvFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Откликнуться", Button.VACANCY_RESPOND)
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", Button.AGREEMENT)
                .clickButton("Отправить", Button.SEND_APPLICATION)
                .checkValidationMessage("Телефон", ValidationMessage.REQUIRED_FIELD, Fields.CV)
                .closeVacancyApplicationWindow();
    }

    @Story("Empty field validation for Vacancy Application form")
    @Test(description = "Check if empty field accompanying leads to the validation")
    public void accompanyingFieldValidation_EmptyField() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Откликнуться", Button.VACANCY_RESPOND)
                .clickButton("Откликнуться без резюме", Button.APPLY_WITHOUT_RESUME)
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", Button.AGREEMENT)
                .clickButton("Отправить", Button.SEND_APPLICATION)
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.REQUIRED_FIELD, Fields.ACCOMPANYING_TEXT_VALIDATION)
                .closeVacancyApplicationWindow();
    }

    @Story("Number of characters validation for Vacancy Application form")
    @Test(description = "Check that more than maximum number of characters of accompanying text leads to the validation")
    public void accompanyingFieldValidation_MaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Откликнуться", Button.VACANCY_RESPOND)
                .clickButton("Откликнуться без резюме", Button.APPLY_WITHOUT_RESUME)
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", Button.AGREEMENT)
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5001), Fields.ACCOMPANYING_TEXT)
                .clickButton("Отправить", Button.SEND_APPLICATION)
                .checkValidationMessage("Сопроводительный текст", ValidationMessage.MAXIMUM_CHARACTERS_5000, Fields.ACCOMPANYING_TEXT_VALIDATION)
                .closeVacancyApplicationWindow();
    }

    @Story("Number of characters validation for Vacancy Application form")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(description = "Check that less than maximum number of characters of accompanying text doesn't lead to the validation")
    public void accompanyingFieldValidation_LessThanMaxNumber() {
        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Откликнуться", Button.VACANCY_RESPOND)
                .clickButton("Откликнуться без резюме", Button.APPLY_WITHOUT_RESUME)
                .clickButton("Отправляя отклик на вакансию, я даю согласие на обработку моих персональных данных", Button.AGREEMENT)
                .setTinyMCEText("Сопроводительный текст", CustomRandom.getText(5000), Fields.ACCOMPANYING_TEXT)
                .clickButton("Отправить", Button.SEND_APPLICATION)
                .checkValidationMessageAbsence("Сопроводительный текст", Fields.ACCOMPANYING_TEXT_VALIDATION)
                .closeVacancyApplicationWindow();
    }

}
