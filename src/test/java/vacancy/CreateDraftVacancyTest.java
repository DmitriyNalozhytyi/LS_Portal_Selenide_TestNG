package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * addDraftVacancy() - Verify that recruiter and admin can create a draft vacancy
 */

@Epic("Vacancy")
public class CreateDraftVacancyTest extends ParentTest {

    @DataProvider(name = "listOfUsers")
    public Object[][] checkSingleFilter() {

        return new Object[][]{
                {USERS.DEV_TESTUSER15, "_VACANCY_DRAFT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
                {USERS.DEV_TESTUSER14, "_VACANCY_DRAFT_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5)},
        };
    }


    @Story("Create vacancy")
    @Test(description = "Create draft vacancy", dataProvider = "listOfUsers")
    public void addDraftVacancy(USERS user, String vacancyName) {
        new AuthorizationPage().loginAs(user);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", Button.CREATE_VACANCY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, user+vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", VacancyType.FOR_STAFF)
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, Fields.VACANCY_COMPANY)
                .selectFor("Город", City.VINNYTSIA, Fields.VACANCY_CITY)
                .setValueFor("Уровень позиции", "N-1", PositionLevel.N_1)
                .setValueFor("Тип занятости", "Частичная занятость", EmploymentType.PART_TIME)
                .selectFor("Функция",Function.AUDIT, Fields.VACANCY_FUNCTION)
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, Fields.VACANCY_SCHEDULE)
                .selectResponsibleForSW(user, Data.RECRUITER_2)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновики", Tabs.VACANCY_DRAFT)
                .search(user+vacancyName)
                .checkForVacancy(user+vacancyName);
    }
}
