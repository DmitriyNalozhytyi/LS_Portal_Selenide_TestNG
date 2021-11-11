package notifications.vacancy;

import constants.Pages;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.notifacations.NotificationPage;
import pages.vacancy.CreateVacancyPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Notifications")
public class SupervisorNotificationsNewVacancyTest extends ParentTest {
    String vacancyName_1 = USERS.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_2 = USERS.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Feature("Supervisor")
    @Test(description = "Check the notification for the vacancy in status awaiting publication")
    public void checkNotificationAwaitingPublication() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName_1)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(),1)
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
                .search(vacancyName_1)
                .checkForVacancy(vacancyName_1);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_1);
    }

    @Feature("Supervisor")
    @Test(description = "Check that supervisor is able to open tha vacancy from the notification")
    public void checkOpenVacancyFromNotification() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName_2)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(),1)
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
                .search(vacancyName_2)
                .checkForVacancy(vacancyName_2);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_2)
                .clickOnNotification(vacancyName_2);

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens();
    }
/*
    @AfterClass
    public void cleanUp() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .selectActionFor(vacancyName_1, VacancyAction.DELETE)
                .selectActionFor(vacancyName_2, VacancyAction.DELETE);
    }*/

}
