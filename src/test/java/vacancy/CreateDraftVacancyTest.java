package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;

import static utils.DateTime.getDate;

@Epic("Vacancy")
public class CreateDraftVacancyTest extends ParentTest {


    @Story("Create vacancy")
    @Test(description = "Create draft vacancy as recruiter")
    public void addDraftVacancy() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagement();

        new VacancyPage()
                .isPageOpens()
                .clickButton("Создать вакансию", Button.CREATE_VACANCY);

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", Input.VACANCY_NAME, USERS.DEV_TESTUSER14+"_VACANCY_DRAFT_" + getDate("dd/mm/YYYY", 5))
                .setValueFor("Тип вакансии", "Для сотрудников", VacancyType.FOR_STAFF)
                .selectFor("Предприятие", Companies.METINVEST_KHOLDING, Fields.VACANCY_COMPANY)
                .selectFor("Город", City.VINNYTSIA, Fields.VACANCY_CITY)
                .setValueFor("Уровень позиции", "N-1", PositionLevel.N_1)
                .setValueFor("Тип занятости", "Частичная занятость", EmploymentType.PART_TIME)
                .selectFor("Функция",Function.AUDIT, Fields.VACANCY_FUNCTION)
                .selectFor("График работы",Schedule.SHIFT_WORK_8_HOUR, Fields.VACANCY_SCHEDULE)
                .clickButton("Сохранить", Button.SAVE_VACANCY);

        /*new VacancyPage()
                .isPageOpens()
                .switchToTab()
                .checkForVacancy();*/

    }
}
