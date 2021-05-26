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
import base.ParentTest;
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
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), user+vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, CreateVacancyPage.ddCompany())
                .selectFor("Город", City.VINNYTSIA, CreateVacancyPage.ddCity())
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция",Function.AUDIT, CreateVacancyPage.ddFunction())
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, CreateVacancyPage.ddSchedule())
                .selectResponsibleForSW(user, Data.RECRUITER_2)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновики", VacancyManagementPage.tbVacancyDraft())
                .search(user+vacancyName)
                .checkForVacancy(user+vacancyName);
    }
}
