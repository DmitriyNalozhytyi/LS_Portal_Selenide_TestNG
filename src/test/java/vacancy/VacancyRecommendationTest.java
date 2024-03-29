package vacancy;

import constants.*;
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
 * There is a short checklist: <br>
 *
 * <b>sendRecommendation()</b>         - verify that a recommendation can be sent <br>
 * <b>checkForStatusNew()</b>          - verify that just created recommendation changes to status NEW <br>
 * <b>checkForStatusViewed()</b>       - verify that the status of opened recommendation changes to VIEWED <br>
 * <b>checkForStatusOnApproval()</b>   - verify that the status of recommendation changes to ON APPROVAL when send a candidate on approval <br>
 * <b>checkForStatusAccepted()</b>     - verify that the status of recommendation changes to status ACCEPTED when accept this candidate <br>
 * <b>checkForStatusCanceled()</b>     - verify that the status of recommendation changes to status CANCELED when cancel this candidate <br>
 */

@Epic("Vacancy")
@Feature("Responses and Recommendations")
@Story("Recommendations")
public class VacancyRecommendationTest extends ParentTest {

   @Test(description = "Send recommendation for colleague")
    public void sendRecommendation() {
        String vacancyName = USER.DEV_TESTUSER14 + "_RECOMMEND_COLLEAGUE_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_1)
                .checkIfRecommendationAdded(Data.RECRUITER_1);
    }

    @Test(description = "Check the status NEW")
    public void checkForStatusNew() {
        String vacancyName = USER.DEV_TESTUSER14 + "_RECOMMEND_COLLEAGUE_STATUS_NEW_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_1)
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .checkForResponseStatus(Status.NEW);
    }

    @Test(description = "Check the status VIEWED")
    public void checkForStatusViewed() {
        String vacancyName = USER.DEV_TESTUSER14 + "_RECOMMEND_COLLEAGUE_STATUS_VIEWED_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_1)
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .closeRecommendationDetails()
                .checkForResponseStatus(Status.VIEWED);
    }

    @Test(description = "Check the status ON APPROVAL")
    public void checkForStatusOnApproval() {
        String vacancyName = USER.DEV_TESTUSER14 + "_RECOMMEND_COLLEAGUE_STATUS_ON_APPROVAL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_1)
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .responseActions(ResponseActions.ON_APPROVAL)
                .checkForResponseStatus(Status.ON_APPROVAL);
    }

    @Test(description = "Check the status ACCEPTED")
    public void checkForStatusAccepted() {
        String vacancyName = USER.DEV_TESTUSER14 + "_RECOMMEND_COLLEAGUE_STATUS_ACCEPTED_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USER.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_1)
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .responseActions(ResponseActions.ON_APPROVAL)
                .openRecommendationDetails()
                .responseActions(ResponseActions.ACCEPT)
                .checkForResponseStatus(Status.ACCEPTED);
    }

}
