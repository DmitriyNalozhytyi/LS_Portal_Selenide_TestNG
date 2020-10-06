package feedbackTests;

import org.junit.Test;
import parentTest.ParentTest;

public class FlowFeedback extends ParentTest {


    @Test
    public void createFeedback_Approve_PublishFAQ_Management_MH_byMainModerator() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannelField();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("test - Main Moderator - approve - status New" + actions.currentTime());
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

    }

    @Test
    public void createFeedback_ApproveByApprover_ApproveByMainModerator() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannelField();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - approve feedback by Approver
       // Thread.sleep(15000);
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closeFeedbackCard();


    }

    @Test
    public void TEST() throws InterruptedException {
        authorizationPage.authorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();


    }
}