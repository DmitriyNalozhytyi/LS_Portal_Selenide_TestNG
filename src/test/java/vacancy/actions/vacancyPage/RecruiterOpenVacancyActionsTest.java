package vacancy.actions.vacancyPage;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyEditPage;
import pages.vacancy.VacancyManagementPage;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * recruiterCanCopyVacancy()       - verify that recruiter can create a copy of a vacancy<br>
 * recruiterCanEditVacancy()       - verify that recruiter can edit a vacancy<br>
 */
@Epic("Vacancy")
@Feature("Actions for vacancies on the Vacancy Page")
public class RecruiterOpenVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that recruiter can make a copy of a vacancy")
    public void recruiterCanCopyVacancy() {
        String vacancyName       = USER.DEV_TESTUSER14 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15,vacancyName);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .copyVacancy();

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .checkForVacancyName(vacancyName)
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyNameCopied)
                .clickButton("На утверждение", CreateVacancyPage.btnOnApprovalVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .search(vacancyNameCopied)
                .checkForVacancy(vacancyNameCopied);
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that recruiter can edit a vacancy on the Vacancy Page")
    public void recruiterCanEditVacancy() {
        String vacancyName          = USER.DEV_TESTUSER14 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15,vacancyName);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .editVacancy();

        new VacancyEditPage()
                .isPageOpens()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyNameEdited)
                .clickButton("На утверждение", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyNameEdited, "Введите название вакансии")
                .checkForVacancy(vacancyNameEdited);
    }
}
