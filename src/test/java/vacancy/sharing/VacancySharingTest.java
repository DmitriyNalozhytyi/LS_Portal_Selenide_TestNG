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
import pages.vacancy.VacancyPage;
import base.ParentTest;
import utils.CustomRandom;

/**
 * vacancySharingFromVacancyManagementPage() -
 * vacancySharingFromVacancyPage() -
 * vacancySharingByEmailFromVacancyManagementPage() -
 * vacancySharingByEmailFromVacancyPage() -
 */

@Epic("Vacancy")
public class VacancySharingTest extends ParentTest {

    @Story("Share Vacancy with recruiters")
    @Test(description = "Verify that vacancy sharing with recruiter from the Vacancy Management page")
    public void vacancySharingFromVacancyManagementPage() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_SHARING_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Поделиться", VacancyDetailPage.btnVacancyShare_VMP());

        new ShareDialogBox()
                .isDialogOpened(ShareDialogBox.SHARE_VACANCY_PAGE_TITLE)
                .selectUser(Data.RECRUITER_1, "ФИО получателя")
                .clickButton("Отправить", ShareDialogBox.btnVacancySend())
                .checkIfShared(SuccessMessages.VACANCY_SENT);
    }

    @Story("Share Vacancy with recruiters")
    @Test(description = "Verify vacancy sharing with recruiter from the Vacancy page")
    public void vacancySharingFromVacancyPage() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_SHARING_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goToVacancyPage();

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Поделиться", VacancyDetailPage.btnVacancyShare());

        new ShareDialogBox()
                .isDialogOpened(ShareDialogBox.SHARE_VACANCY_PAGE_TITLE)
                .selectUser(Data.RECRUITER_1, "ФИО получателя")
                .clickButton("Отправить", ShareDialogBox.btnVacancySend())
                .checkIfShared(SuccessMessages.VACANCY_SENT);
    }

    @Story("Share Vacancy by email")
    @Test(description = "Verify that vacancy sharing from the Vacancy Management page")
    public void vacancySharingByEmailFromVacancyManagementPage() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_SHARING_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName)
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Поделиться", VacancyDetailPage.btnVacancyShare_VMP());

        new ShareDialogBox()
                .isDialogOpened(ShareDialogBox.SHARE_VACANCY_PAGE_TITLE)
                .openTab("Поделиться по почте", ShareDialogBox.tabVacancyShareByEmail())
                .setTextFor("Электронная почта получателя", "test@asd.com", ShareDialogBox.inpVacancyShareEmail())
                .clickButton("Отправить", ShareDialogBox.btnVacancySend())
                .checkIfShared(SuccessMessages.VACANCY_SENT);
    }

    @Story("Share Vacancy by email")
    @Test(description = "Verify vacancy sharing from the Vacancy page")
    public void vacancySharingByEmailFromVacancyPage() {
        String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_SHARING_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goToVacancyManagementPage();

        new VacancyManagementPage()
                .isPageOpens()
                .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goToVacancyPage();

        new VacancyPage()
                .isPageOpens()
                .filterBy(Filter.NAME,vacancyName, "Введите название вакансии")
                .openVacancyDetails(vacancyName);

        new VacancyDetailPage(vacancyName)
                .isPageOpens()
                .clickButton("Поделиться", VacancyDetailPage.btnVacancyShare());

        new ShareDialogBox()
                .isDialogOpened(ShareDialogBox.SHARE_VACANCY_PAGE_TITLE)
                .openTab("Поделиться по почте", ShareDialogBox.tabVacancyShareByEmail())
                .setTextFor("Электронная почта получателя", "test@asd.com", ShareDialogBox.inpVacancyShareEmail())
                .clickButton("Отправить", ShareDialogBox.btnVacancySend())
                .checkIfShared(SuccessMessages.VACANCY_SENT);
    }
}
