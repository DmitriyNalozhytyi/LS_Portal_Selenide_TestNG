package notifications.vacancy;

import components.DialogBox;
import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
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
public class RecommendViewedTest extends ParentTest {
    String vacancyName_1 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_2 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Feature("Author")
    @Test(description = "Author of recommendation gets the notification that the his recommendation has been viewed")
    public void checkRecommendationViewed() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_1);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_1, "Введите название вакансии")
                .openVacancyDetails(vacancyName_1);

        new VacancyDetailPage(vacancyName_1)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_1);

        new VacancyDetailPage(vacancyName_1)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .closeRecommendationDetails();

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_1);
    }

    @Feature("Author")
    @Test(description = "Author of recommendation opens vacancy from notification")
    public void checkOpenResponseFromNotification() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_2);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_2, "Введите название вакансии")
                .openVacancyDetails(vacancyName_2);

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_2);

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .closeRecommendationDetails();

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkIfNotificationIsPresent(vacancyName_2)
                .clickOnNotification(vacancyName_2);

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens();
    }

    @AfterClass
    public void cleanUp() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER15);

        new DialogBox().close();

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .selectActionFor(vacancyName_1, VacancyAction.DELETE)
                .selectActionFor(vacancyName_2, VacancyAction.DELETE);
    }
}
