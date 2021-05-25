package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyManagementPage;
import base.ParentTest;
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

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", Button.CREATE_VACANCY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, CreateVacancyPage.ddCompany())
                .selectFor("Город", City.VINNYTSIA, CreateVacancyPage.ddCity())
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция",Function.AUDIT, CreateVacancyPage.ddFunction())
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, CreateVacancyPage.ddSchedule())
                .clickButton("На утверждение", CreateVacancyPage.btnOnApprovalVacancy());

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .checkForVacancy(vacancyName);

    }
}
