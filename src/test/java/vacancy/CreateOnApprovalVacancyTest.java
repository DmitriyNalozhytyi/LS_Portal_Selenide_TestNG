package vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;
import utils.CustomRandom;
import utils.TestListeners;

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
                .checkForVacancy(vacancyName);

    }
}
