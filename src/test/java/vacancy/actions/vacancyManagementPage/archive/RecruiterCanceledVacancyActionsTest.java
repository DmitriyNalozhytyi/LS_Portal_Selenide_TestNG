package vacancy.actions.vacancyManagementPage.archive;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * recruiterCanCopyVacancy()       - verify that recruiter can create a copy of a vacancy<br>
 */
@Epic("Vacancy")
@Feature("Actions for vacancies in status CANCELED")
public class RecruiterCanceledVacancyActionsTest extends ParentTest {

    @Story("Copy vacancy")
    @Test(description = "Verify that recruiter can create a copy of a vacancy")
    public void recruiterCanCopyVacancy() {
        String vacancyName       = USER.DEV_TESTUSER14 + "_VACANCY_COPY_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        String vacancyNameCopied = vacancyName + "_COPIED";

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);
        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createVacancyForArchiveASRecruiter(vacancyName, "Приостановлена", VacancyStatus.CANCELED_ON_OPENED);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", VacancyManagementPage.tbVacancyArchive())
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

}
