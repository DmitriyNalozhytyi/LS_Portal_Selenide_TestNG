package vacancy.actions.vacancyManagementPage.archive;

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
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * supervisorCanCopyVacancy()       - verify that supervisor can create a copy of a vacancy<br>
 * supervisorCanDeleteVacancy()     - verify that supervisor can delete a vacancy<br>
 * supervisorCanEditVacancy()       - verify that supervisor can edit a vacancy<br>
 */
@Epic("Vacancy")
@Feature("Actions for vacancies in status SUSPENDED")
public class SupervisorSuspendedVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that supervisor can create a copy of a vacancy")
    public void supervisorCanCopyVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER15 + "_VACANCY_COPY_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyForArchiveASSupervisor(vacancyName, "Приостановлена", VacancyStatus.SUSPENDED);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .selectActionFor(vacancyName, VacancyAction.COPY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .checkForVacancyName(vacancyName)
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyNameCopied)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", Tabs.VACANCY_DRAFT)
                .search(vacancyNameCopied)
                .checkForVacancy(vacancyNameCopied);
    }

    @Story("Delete vacancy")
    @Test(description = "Verify that supervisor can delete a vacancy")
    public void supervisorCanDeleteVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER15 + "_VACANCY_DELETE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyForArchiveASSupervisor(vacancyName, "Приостановлена", VacancyStatus.SUSPENDED);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .selectActionFor(vacancyName, VacancyAction.DELETE)
                .search(vacancyName)
                .checkForVacancyAbsence(vacancyName);
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that supervisor can edit a vacancy")
    public void supervisorCanEditVacancy() {
        String vacancyName          = USERS.DEV_TESTUSER15 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyForArchiveASSupervisor(vacancyName, "Приостановлена", VacancyStatus.SUSPENDED);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyNameEdited)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .search(vacancyNameEdited)
                .checkForVacancy(vacancyNameEdited);
    }
}