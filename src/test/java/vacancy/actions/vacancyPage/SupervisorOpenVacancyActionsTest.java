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
 * supervisorCanCopyVacancy()       - verify that supervisor can create a copy of a vacancy<br>
 * supervisorCanDeleteVacancy()     - verify that supervisor can delete a vacancy<br>
 * supervisorCanEditVacancy()       - verify that supervisor can edit a vacancy<br>
 */
@Epic("Vacancy")
@Feature("Actions for vacancies on the Vacancy Page")
public class SupervisorOpenVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that supervisor can create a copy of a vacancy")
    public void supervisorCanCopyVacancy() {
        String vacancyName       = USER.DEV_TESTUSER15 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyASSupervisor(vacancyName);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .copyVacancy();

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .checkForVacancyName(vacancyName)
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyNameCopied)
                .clickButton("На утверждение", CreateVacancyPage.btnSaveAndPublishVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyNameCopied)
                .checkForVacancy(vacancyNameCopied);
    }

    @Story("Delete vacancy")
    @Test(description = "Verify that supervisor can delete a vacancy")
    public void supervisorCanDeleteVacancy() {
        String vacancyName       = USER.DEV_TESTUSER15 + "_VACANCY_DELETE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyASSupervisor(vacancyName);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .deleteVacancy()
                .checkForVacancyAbsence();
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that supervisor can edit a vacancy")
    public void supervisorCanEditVacancy() {
        String vacancyName          = USER.DEV_TESTUSER15 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyASSupervisor(vacancyName);

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
