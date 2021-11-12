package vacancy.actions.vacancyDetailPage.draft;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyEditPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * recruiterCanCopyVacancy()       - verify that recruiter can create a copy of a vacancy<br>
 * recruiterCanDeleteVacancy()     - verify that recruiter can delete a vacancy<br>
 * recruiterCanEditVacancy()       - verify that recruiter can edit a vacancy<br>
 */
@Epic("Vacancy")
@Feature("Actions for vacancies in status DRAFT on vacancy details page")
public class RecruiterDraftVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that recruiter can create a copy of a vacancy")
    public void recruiterCanCopyVacancy() {
        String vacancyName       = USER.DEV_TESTUSER14 + "_VACANCY_COPY_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsRecruiter(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .vacancyAction(VacancyAction.COPY);

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
    @Test(description = "Verify that recruiter can delete a vacancy")
    public void recruiterCanDeleteVacancy() {
        String vacancyName       = USER.DEV_TESTUSER14 + "_VACANCY_DELETE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsRecruiter(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .vacancyAction(VacancyAction.DELETE);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyName)
                .checkForVacancyAbsence();
    }

    @Story("Edit vacancy")
    @Test(description = "Verify that recruiter can edit a vacancy")
    public void recruiterCanEditVacancy() {
        String vacancyName          = USER.DEV_TESTUSER14 + "_VACANCY_EDIT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameEdited    = vacancyName + "_EDITED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createDraftVacancyAsRecruiter(vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновик", VacancyManagementPage.tbVacancyDraft())
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .vacancyAction(VacancyAction.EDIT);

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
