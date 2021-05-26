package vacancy.sharing;

import components.ShareDialogBox;
import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.VacancyDetailPage;
import pages.vacancy.VacancyManagementPage;
import base.ParentTest;
import utils.CustomRandom;

/**
 * recommendationSharingFromVacancyManagementPage() -
 */

@Epic("Vacancy")
public class RecommendationSharingTest extends ParentTest {

    @Story("Share application with recruiters")
    @Test(description = "Verify that vacancy sharing with recruiter from the Vacancy Management page")
    public void responseSharingFromVacancyManagementPage() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_SHARING_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .recommendColleague(Data.RECRUITER_2)
                .clickButton("Отклики", VacancyDetailPage.btnVacancyResponses())
                .openTab("Рекомендации", VacancyDetailPage.tabVacancyRecommendations())
                .openRecommendationDetails()
                .clickButton("Поделиться", VacancyDetailPage.btnResponseShare());

        new ShareDialogBox()
                .isDialogOpened(ShareDialogBox.SHARE_RECOMMEND_PAGE_TITLE)
                .selectUser(Data.RECRUITER_2, "ФИО получателя")
                .clickButton("Отправить", ShareDialogBox.btnVacancySend())
                .checkIfShared(SuccessMessages.RESPONSE_SENT);
    }

}
