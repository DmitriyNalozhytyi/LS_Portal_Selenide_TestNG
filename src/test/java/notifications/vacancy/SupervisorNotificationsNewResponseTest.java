package notifications.vacancy;

import constants.Filter;
import constants.Pages;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.notifacations.NotificationPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Notifications")
public class SupervisorNotificationsNewResponseTest extends ParentTest {
    String vacancyName_1 = USERS.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_2 = USERS.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Feature("Recruiter")
    @Test(description = "Check the notification for a new response to his vacancy")
    public void checkNotificationNewResponseOnVacancy() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName_1);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_1, "Введите название вакансии")
                .openVacancyDetails(vacancyName_1);

        new VacancyDetailPage(vacancyName_1)
                .isPageOpens()
                .sendRespond();

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_1);
    }

    @Feature("Recruiter")
    @Test(description = "Check that recruiter can open response from notification")
    public void checkOpenResponseFromNotification() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName_2);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_2, "Введите название вакансии")
                .openVacancyDetails(vacancyName_2);

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens()
                .sendRespond();

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_2)
                .clickOnNotification(vacancyName_2);

        new VacancyDetailPage()
                .checkForOpenedResponse(USERS.DEV_TESTUSER13);
    }
/*

    @AfterClass
    public void cleanUp() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Open", VacancyManagementPage.tbVacancyOpened())
                .selectActionFor(vacancyName_1, VacancyAction.DELETE)
                .selectActionFor(vacancyName_2, VacancyAction.DELETE);
    }
*/

}
