package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyEdit;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;
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

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .clickButton("Создать вакансию", Button.CREATE_VACANCY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", VacancyType.FOR_STAFF)
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, Fields.VACANCY_COMPANY)
                .selectFor("Город", City.VINNYTSIA, Fields.VACANCY_CITY)
                .setValueFor("Уровень позиции", "N-1", PositionLevel.N_1)
                .setValueFor("Тип занятости", "Частичная занятость", EmploymentType.PART_TIME)
                .selectFor("Функция",Function.AUDIT, Fields.VACANCY_FUNCTION)
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, Fields.VACANCY_SCHEDULE)
                .clickButton("На утверждение", Button.ON_APPROVAL_VACANCY);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .search(vacancyName)
                .checkForVacancy(vacancyName);


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEdit()
                .isPageOpens()
                .changeStatus("Статус", "Открытая", VacancyStatus.OPEN)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .switchTo("Открытые", Tabs.VACANCY_OPENED)
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .switchTo("Открытые", Tabs.VACANCY_OPENED)
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }

    @Story("Create vacancy")
    @Test(description = "Create vacancy as admin and approve as admin")
    public void addVacancyAsAdminAndPublish() {
        String vacancyName = USERS.DEV_TESTUSER15 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .clickButton("Создать вакансию", Button.CREATE_VACANCY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", VacancyType.FOR_STAFF)
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, Fields.VACANCY_COMPANY)
                .selectFor("Город", City.VINNYTSIA, Fields.VACANCY_CITY)
                .setValueFor("Уровень позиции", "N-1", PositionLevel.N_1)
                .setValueFor("Тип занятости", "Частичная занятость", EmploymentType.PART_TIME)
                .selectFor("Функция", Function.AUDIT, Fields.VACANCY_FUNCTION)
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, Fields.VACANCY_SCHEDULE)
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить и опубликовать", Button.SAVE_AND_PUBLISH_VACANCY);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .switchTo("Открытые", Tabs.VACANCY_OPENED)
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }
}
