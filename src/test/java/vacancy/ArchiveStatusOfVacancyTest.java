package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyEdit;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * moveOpenedVacancyToArchive()         - verify that vacancy is moved to archive from opened vacancy when admin changes the status to SUSPENDED, CLOSED, CANCELED
 * moveVacancyFromOnApprovalToArchive() - verify that vacancy is moved to archive from on approval vacancy when admin changes the status to CANCELED
 */

@Epic("Vacancy")
public class ArchiveStatusOfVacancyTest extends ParentTest {

    @DataProvider(name = "listOfStatusesOfOpened")
    public Object[][] listOfStatusesOfOpened() {

        return new Object[][]{
                {VacancyStatus.SUSPENDED, USERS.DEV_TESTUSER14+"_SUSPENDED_VACANCY_FROM_OPENED_TO_ARCHIVE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
                {VacancyStatus.CLOSED, USERS.DEV_TESTUSER14+"_CLOSED_VACANCY_FROM_OPENED_TO_ARCHIVE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
                {VacancyStatus.CANCELED_ON_OPENED, USERS.DEV_TESTUSER14+"_CANCELED_VACANCY_FROM_OPENED_TO_ARCHIVE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
        };
    }

    @DataProvider(name = "listOfStatusesOnApproval")
    public Object[][] listOfStatusesOnApproval() {

        return new Object[][]{
                {VacancyStatus.CANCELED_ON_APPROVAL, USERS.DEV_TESTUSER14+"_CANCELED_VACANCY_FROM_ON_APPROVAL_TO_ARCHIVE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
        };
    }

    @Story("Move vacancy to archive")
    @Test(description = "Move opened vacancy to archive", dataProvider = "listOfStatusesOfOpened")
    public void moveOpenedVacancyToArchive(int vacancyStatus, String vacancyName) {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
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

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .search(vacancyName)
                .checkForVacancy(vacancyName);


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEdit()
                .isPageOpens()
                .changeStatus("Статус", "Открытая", VacancyStatus.OPEN)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", Tabs.VACANCY_OPENED)
                .search(vacancyName)
                .checkForVacancy(vacancyName)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEdit()
                .isPageOpens()
                .changeStatus("Статус", "Приостановлена", vacancyStatus)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }

    @Story("Move vacancy to archive")
    @Test(description = "Move vacancy from on approval to archive", dataProvider = "listOfStatusesOnApproval")
    public void moveVacancyFromOnApprovalToArchive(int vacancyStatus, String vacancyName) {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
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

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .search(vacancyName)
                .checkForVacancy(vacancyName);


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEdit()
                .isPageOpens()
                .changeStatus("Статус", "Отменена", vacancyStatus)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", Tabs.VACANCY_ARCHIVE)
                .search(vacancyName)
                .checkForVacancy(vacancyName);
    }

}
