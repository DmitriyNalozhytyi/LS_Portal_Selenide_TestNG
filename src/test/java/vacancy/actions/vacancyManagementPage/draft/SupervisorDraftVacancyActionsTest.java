package vacancy.actions.vacancyManagementPage.draft;

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
@Feature("Actions for vacancies in status DRAFT")
public class SupervisorDraftVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that supervisor can create a copy of a vacancy")
    public void supervisorCanCopyVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER15 + "_VACANCY_COPY_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsSupervisor(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .selectActionFor(vacancyName, VacancyAction.COPY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .checkForVacancyName(vacancyName)
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyNameCopied)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyNameCopied)
                .checkForVacancy(vacancyNameCopied);
    }

    @Story("Delete vacancy")
    @Test(description = "Verify that supervisor can delete a vacancy")
    public void supervisorCanDeleteVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER15 + "_VACANCY_DELETE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsSupervisor(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .selectActionFor(vacancyName, VacancyAction.DELETE)
                .search(vacancyName)
                .checkForVacancyAbsence();
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that supervisor can edit a vacancy")
    public void supervisorCanEditVacancy() {
        String vacancyName          = USERS.DEV_TESTUSER15 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsSupervisor(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyNameEdited)
                .clickButton("Сохранить", VacancyEditPage.btnSaveDraftVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyNameEdited)
                .checkForVacancy(vacancyNameEdited);
    }
}
