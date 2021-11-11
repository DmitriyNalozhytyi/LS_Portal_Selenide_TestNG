package vacancy;

import constants.ResponseActions;
import constants.Pages;
import constants.Status;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

/**
 * There is a short checklist:
 *
 * sendApplication()            - verify that response on a vacancy can be sent
 * checkForStatusNew()          - verify that just created response changes to status NEW
 * checkForStatusViewed()       - verify that the status of opened response changes to VIEWED
 * checkForStatusOnApproval()   - verify that the status of response changes to ON APPROVAL when send a candidate on approval
 * checkForStatusAccepted()     - verify that the status of response changes to status ACCEPTED when accept this candidate
 */
@Epic("Vacancy")
@Feature("Responses and Recommendations")
@Story("Responses")
public class VacancyResponseTest extends ParentTest {

   @Test(description = "Send application")
    public void sendApplication() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_RESPONSE_SEND_APPLICATION_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .sendRespond();
    }

    @Test(description = "Check the status NEW")
    public void checkForStatusNew() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_RESPONSE_STATUS_NEW_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .sendRespond()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .checkForResponseStatus(Status.NEW);
    }

    @Test(description = "Check the status VIEWED")
    public void checkForStatusViewed() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_RESPONSE_STATUS_VIEWED_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .sendRespond()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openResponseDetails()
                .closeResponseDetails()
                .checkForResponseStatus(Status.VIEWED);
    }

    @Test(description = "Check the status DECLINED")
    public void checkForStatusOnApproval() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_RESPONSE_STATUS_ON_APPROVAL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .sendRespond()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openResponseDetails()
                .responseActions(ResponseActions.ON_APPROVAL)
                .checkForResponseStatus(Status.ON_APPROVAL);
    }

    @Test(description = "Check the status ACCEPTED")
    public void checkForStatusAccepted() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_RESPONSE_STATUS_ACCEPTED_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .sendRespond()
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openResponseDetails()
                .responseActions(ResponseActions.ON_APPROVAL)
                .openResponseDetails()
                .responseActions(ResponseActions.ACCEPT)
                .checkForResponseStatus(Status.ACCEPTED);
    }
}
