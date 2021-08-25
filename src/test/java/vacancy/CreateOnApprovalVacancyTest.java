package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * addVacancyOnApprovalAsRecruiter() - Verify that recruiter is able to create a vacancy and this vacancy has the status On Approval
 */

@Epic("Vacancy")
public class CreateOnApprovalVacancyTest extends ParentTest {
    private final String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_ON_APPROVAL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);


    @Story("Create vacancy")
    @Test(description = "Create and publish a vacancy")
    public void addVacancyOnApprovalAsRecruiter() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .clickButton("На утверждение", CreateVacancyPage.btnOnApprovalVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .checkForVacancy(vacancyName);

    }
}
