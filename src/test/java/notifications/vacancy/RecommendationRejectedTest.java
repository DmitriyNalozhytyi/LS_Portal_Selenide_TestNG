package notifications.vacancy;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.notifacations.NotificationPage;
import pages.notification.REJECTION_REASON;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import pages.vacancy.VacancyPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Notifications")
public class RecommendationRejectedTest extends ParentTest {
    String vacancyName_1 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_2 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_3 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_4 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_5 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    String vacancyName_6 = USER.DEV_TESTUSER14 + "_NOTIFICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Feature("Author")
    @Test(description = "Author of recommendation gets the notification that the recommended person does not fit this vacancy")
    public void checkNotificationDoesNotFit() {
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
                .declineResponse(REJECTION_REASON.MISMATCHED_QUALIFICATION, "");

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_1, REJECTION_REASON.MISMATCHED_QUALIFICATION, "");
    }

    @Feature("Author")
    @Test(description = "Author opens vacancy from notification about rejected recommendation by mismatched qualification")
    public void checkOpenVacancyRejectedMismatched() {
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
                .declineResponse(REJECTION_REASON.MISMATCHED_QUALIFICATION, "");

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_1, REJECTION_REASON.MISMATCHED_QUALIFICATION, "")
                .openRejectedVacancy(vacancyName_6, REJECTION_REASON.MISMATCHED_QUALIFICATION, "");

        new VacancyDetailPage(vacancyName_2)
                .isPageOpens();
    }

    @Feature("Author")
    @Test(description = "Author of recommendation gets the notification that the decision was made in favor of another candidate")
    public void checkNotificationAnotherCandidate() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_3, "Введите название вакансии")
                .openVacancyDetails(vacancyName_3);

        new VacancyDetailPage(vacancyName_3)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_3);

        new VacancyDetailPage(vacancyName_3)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .declineResponse(REJECTION_REASON.ANOTHER_CANDIDATE, "");

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_3, REJECTION_REASON.ANOTHER_CANDIDATE, "");
    }

    @Feature("Author")
    @Test(description = "Author opens vacancy from notification about rejected response because decision made in favor of another candidate")
    public void checkOpenVacancyRejectedCandidate() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_4);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_4, "Введите название вакансии")
                .openVacancyDetails(vacancyName_4);

        new VacancyDetailPage(vacancyName_4)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_4);

        new VacancyDetailPage(vacancyName_4)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .declineResponse(REJECTION_REASON.ANOTHER_CANDIDATE, "");

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_4, REJECTION_REASON.ANOTHER_CANDIDATE, "")
                .openRejectedVacancy(vacancyName_6, REJECTION_REASON.ANOTHER_CANDIDATE, "");

        new VacancyDetailPage(vacancyName_4)
                .isPageOpens();
    }

    @Feature("Author")
    @Test(description = "Response rejected due to other reason")
    public void checkNotificationRejectedAnotherReason() {
        String otherReason = CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,10);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_5, "Введите название вакансии")
                .openVacancyDetails(vacancyName_5);

        new VacancyDetailPage(vacancyName_5)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_5);

        new VacancyDetailPage(vacancyName_5)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .declineResponse(REJECTION_REASON.OTHER_REASON, otherReason);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_5, REJECTION_REASON.OTHER_REASON, otherReason);
    }

    @Feature("Author")
    @Test(description = "Author opens vacancy from notification about rejected by other reason")
    public void checkOpenVacancyRejectedOtherReason() {
        String otherReason = CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,10);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName_6);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.VACANCY);

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName_6, "Введите название вакансии")
                .openVacancyDetails(vacancyName_6);

        new VacancyDetailPage(vacancyName_6)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_3);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .openVacancyDetails(vacancyName_6);

        new VacancyDetailPage(vacancyName_6)
                .isPageOpens()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .declineResponse(REJECTION_REASON.OTHER_REASON, otherReason);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER13);

        new MainPage().goTo(Pages.NOTIFICATIONS);

        new NotificationPage()
                .isPageOpened()
                .checkNotificationForDeclinedResponse(vacancyName_6, REJECTION_REASON.OTHER_REASON, otherReason)
                .openRejectedVacancy(vacancyName_6, REJECTION_REASON.OTHER_REASON, otherReason);

        new VacancyDetailPage(vacancyName_6)
                .isPageOpens();
    }


    @AfterClass
    public void cleanUp() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER15);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .selectActionFor(vacancyName_1, VacancyAction.DELETE)
                .selectActionFor(vacancyName_2, VacancyAction.DELETE)
                .selectActionFor(vacancyName_3, VacancyAction.DELETE)
                .selectActionFor(vacancyName_4, VacancyAction.DELETE)
                .selectActionFor(vacancyName_5, VacancyAction.DELETE)
                .selectActionFor(vacancyName_6, VacancyAction.DELETE);
    }


}
