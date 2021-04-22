package vacancy.actions.onApproval;

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
@Feature("Actions for vacancies in status ON APPROVAL")
public class SupervisorOnApprovalVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that supervisor can create a copy of a vacancy")
    public void supervisorCanCopyVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER14 + "_VACANCY_COPY_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyAsRecruiter(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.COPY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .checkForVacancyName(vacancyName)
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyNameCopied)
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("На утверждение", Button.SAVE_AND_PUBLISH_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", Tabs.VACANCY_OPENED)
                .search(vacancyNameCopied)
                .checkForVacancy(vacancyNameCopied);
    }

    @Story("Delete vacancy")
    @Test(description = "Verify that supervisor can delete a vacancy")
    public void supervisorCanDeleteVacancy() {
        String vacancyName       = USERS.DEV_TESTUSER14 + "_VACANCY_DELETE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyAsRecruiter(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.DELETE)
                .search(vacancyName)
                .checkForVacancyAbsence(vacancyName);
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that supervisor can edit a vacancy")
    public void supervisorCanEditVacancy() {
        String vacancyName          = USERS.DEV_TESTUSER14 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyAsRecruiter(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyNameEdited)
                .clickButton("На утверждение", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .search(vacancyNameEdited)
                .checkForVacancy(vacancyNameEdited);
    }
}
