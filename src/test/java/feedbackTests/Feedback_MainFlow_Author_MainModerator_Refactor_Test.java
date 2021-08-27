package feedbackTests;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.CreateNewFeedback_Page_MainModerator;
import pages.MainPage;
import pages.ViewListOfFeedbacks_Page_MainModerator;
import parentTest.ParentTest;



@Epic("Feedback")
@Feature("MainFlow. Author - Main Moderator")
public class Feedback_MainFlow_Author_MainModerator_Refactor_Test extends ParentTest {

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ()  {


        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        new AuthorizationPage()
                .loginAs(USERS.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()
                .clickOnSendBtn()
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower("Text: Approve by Aprover")
                .clickOnSendBtn()
                //
                .closePopUp()
                .closeFeedbackCard();
        new AuthorizationPage()
                .loginAs(USERS.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ();

//
















    }






    /*@Epic("Vacancy")
    @Feature("Actions for vacancies in status OPENED")
    public class RecruiterOpenVacancyActionsTest extends ParentTest {

        @Story("Copy vacancy")
        @Test(description = "Verify that recruiter can create a copy of a vacancy")
        public void recruiterCanCopyVacancy() {
            String vacancyName = USERS.DEV_TESTUSER14 + "_VACANCY_OPEN_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE, 5);
            String vacancyNameCopied = vacancyName + "_COPIED";

            new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName);

            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .selectActionFor(vacancyName, VacancyAction.COPY);

            new CreateVacancyPage()
                    .isCreateVacancyPage()
                    .checkForVacancyName(vacancyName)
                    .setTextFor("Название вакансии", Input.VACANCY_NAME, vacancyNameCopied)
                    .clickButton("На утверждение", Button.ON_APPROVAL_VACANCY);

            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                    .search(vacancyNameCopied)
                    .checkForVacancy(vacancyNameCopied);

            new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .switchTo("На утверждении", Tabs.VACANCY_ON_APPROVAL)
                    .selectActionFor(vacancyNameCopied, VacancyAction.EDIT);

            new VacancyEditPage()
                    .isPageOpens()
                    .changeStatus("Статус", "Открытая", VacancyStatus.OPEN)
                    .clickButton("Сохранить", Button.SAVE_VACANCY);

            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .switchTo("Открытые", Tabs.VACANCY_OPENED)
                    .search(vacancyNameCopied)
                    .checkForVacancy(vacancyNameCopied);

            new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

            new MainPage().goTo(SiteMenu.VACANCY_MANAGEMENT);

            new VacancyManagementPage()
                    .isPageOpens()
                    .switchTo("Открытые", Tabs.VACANCY_OPENED)
                    .search(vacancyNameCopied)
                    .checkForVacancy(vacancyNameCopied);
        }

    }*/
}
