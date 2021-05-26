package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyEditPage;
import pages.vacancy.VacancyManagementPage;
import base.ParentTest;
import utils.CustomRandom;

/**
 * addVacancyAndOpenAsRecruiter() - create vacancy as recruiter and that change the status to Open as admin and check if this vacancy is in the list of Opened tab.
 * addVacancyAsAdminAndPublish()  - create vacancy as admin and that change the status to Open as admin and check if this vacancy is in the list of Opened tab.
 */

@Epic("Vacancy")
public class CreateAndApproveVacancyTest extends ParentTest {

    @Story("Create vacancy")
    @Test(description = "Create vacancy as recruiter and approve as admin")
    public void addVacancyAsRecruiterAndPublish() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

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
                .search(vacancyName)
                .checkForVacancy(vacancyName);


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .changeStatus("Статус", "Открытая", VacancyStatus.OPEN)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }

    @Story("Create vacancy")
    @Test(description = "Create vacancy as admin and approve as admin")
    public void addVacancyAsAdminAndPublish() {
        String vacancyName = USERS.DEV_TESTUSER15 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, CreateVacancyPage.ddCompany())
                .selectFor("Город", City.VINNYTSIA, CreateVacancyPage.ddCity())
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", Function.AUDIT, CreateVacancyPage.ddFunction())
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, CreateVacancyPage.ddSchedule())
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить и опубликовать", CreateVacancyPage.btnSaveAndPublishVacancy());

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }

    @Story("Create vacancy")
    @Test(description = "Create vacancy as supervisor and save")
    public void addVacancyAsSupervisorAndPublish() {
        String vacancyName = USERS.DEV_TESTUSER15 + "_VACANCY_Draft_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, CreateVacancyPage.ddCompany())
                .selectFor("Город", City.VINNYTSIA, CreateVacancyPage.ddCity())
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", Function.AUDIT, CreateVacancyPage.ddFunction())
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, CreateVacancyPage.ddSchedule())
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновики", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }
}
