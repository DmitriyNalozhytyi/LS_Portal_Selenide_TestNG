package feedbackTests;

import org.junit.Test;
import parentTest.ParentTest;

import static junit.framework.TestCase.assertTrue;

public class FlowFeedback extends ParentTest {

    /*
    MM - Main Moderator
    M - Moderator
    Ap - Approver
    */

    /*
     TEST-CASES:

    TEST-CASE 1:  createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ
    TEST-CASE 2:  createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApprovebyMM__PubFAQ
    TEST-CASE 3:  createFeedbackByMM_Portal_Management_MH__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ
    TEST-CASE 4:  createFeedbackByMM_Portal_Management_MH__BackToMMByAp__ApprovebyMM__PubFAQ

   - TEST-CASE 5:  createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ
    TEST-CASE 6:  createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyMM__PubFAQ
   - TEST-CASE 7:  createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ
   - TEST-CASE 8:  createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyMM__PubFAQ

    TEST-CASE 9:  createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM



     */

    //TEST-CASE 1

    @Test  //ok (need add time in AppealField to Check real feedbak in FAQ)
    public void createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
       // viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("test - Main Moderator - approve - status New" + actions.currentTime());
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        //    STEP 3 - puplish in FAQ
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        //createNewPublicationPage.checkTextFromAppealFieldExistInFAQ();

        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
       // assertTrue(webdriver.findElement(By.cssSelector(".title")).getText().contains("text"));
    }


    //TEST-CASE 2

    @Test //ok (need add time in AppealField to Check real feedbak in FAQ)
    public void createFeedbackByMM_Portal_Management_MH___ApproveByAp_ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
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
      //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

       // viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closeFeedbackCard();

        //    STEP 4 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

        //   STEP 5 - open and check last feedback in FAQ

        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
    }

    //TEST-CASE 3

    @Test // (need add time in AppealField to Check real feedbak in FAQ)!!!!!!!!!!!!!!!!!!!
    public void createFeedbackByMM_Portal_Management_MH__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - send feedback back to Main Moderator by Approver

        viewListOfFeedbacks_page_mainModerator.chooseCheckBoxToBackMM();
        viewListOfFeedbacks_page_mainModerator.inputReasonForReturn();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        Thread.sleep(2000);
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.assignNewResponsible();
        viewListOfFeedbacks_page_mainModerator.chooseNewApprover("Верезумская Ирина Викторовна");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();


        //    STEP 5 - open and approve feedback by New Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser14@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

       //    STEP 6 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

        //   STEP 7 - open and check last feedback in FAQ

        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());

    }


    //TEST-CASE 4

    @Test // (need add time in AppealField to Check real feedbak in FAQ)!!!!!!!!!!!!!!!!!!!
    public void createFeedbackByMM_Portal_Management_MH__BackToMMByAp__ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - send feedback back to Main Moderator by Approver

        viewListOfFeedbacks_page_mainModerator.chooseCheckBoxToBackMM();
        viewListOfFeedbacks_page_mainModerator.inputReasonForReturn();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        Thread.sleep(2000);
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        //    STEP 4 - open feedback backed from Approver to Main Moderator and Approve
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_BackFromAp_ByMM("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 - puplish in FAQ
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        //createNewPublicationPage.checkTextFromAppealFieldExistInFAQ();

        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
        // assertTrue(webdriver.findElement(By.cssSelector(".title")).getText().contains("text"));

    }

    //TEST-CASE 5

    @Test  //ok (need add time in AppealField to Check real feedbak in FAQ)
    public void createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();

        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();

        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        // viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("test - Main Moderator - approve - status New" + actions.currentTime());
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        //    STEP 3 - puplish in FAQ
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        //createNewPublicationPage.checkTextFromAppealFieldExistInFAQ();

        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
        // assertTrue(webdriver.findElement(By.cssSelector(".title")).getText().contains("text"));
    }

    //TEST-CASE 6

    @Test //ok (need add time in AppealField to Check real feedbak in FAQ)
    public void createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
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
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        // viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closeFeedbackCard();

        //    STEP 4 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

        //   STEP 5 - open and check last feedback in FAQ

        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
    }

    //TEST-CASE 7

    @Test // (need add time in AppealField to Check real feedbak in FAQ)!!!!!!!!!!!!!!!!!!!
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - send feedback back to Main Moderator by Approver

        viewListOfFeedbacks_page_mainModerator.chooseCheckBoxToBackMM();
        viewListOfFeedbacks_page_mainModerator.inputReasonForReturn();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        Thread.sleep(2000);
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.assignNewResponsible();
        viewListOfFeedbacks_page_mainModerator.chooseNewApprover("Сокирко Елена Викторовна");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();


        //    STEP 5 - open and approve feedback by New Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 6 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

        //   STEP 7 - open and check last feedback in FAQ

        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());

    }

    //TEST-CASE 8


    @Test // (need add time in AppealField to Check real feedbak in FAQ)!!!!!!!!!!!!!!!!!!!
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - send feedback back to Main Moderator by Approver

        viewListOfFeedbacks_page_mainModerator.chooseCheckBoxToBackMM();
        viewListOfFeedbacks_page_mainModerator.inputReasonForReturn();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        Thread.sleep(2000);
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        //    STEP 4 - open feedback backed from Approver to Main Moderator and Approve
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_BackFromAp_ByMM("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 - puplish in FAQ
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        //createNewPublicationPage.checkTextFromAppealFieldExistInFAQ();

        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());
        // assertTrue(webdriver.findElement(By.cssSelector(".title")).getText().contains("text"));

    }

    //TEST-CASE 9

    @Test
    public void createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");

        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        // viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("test - Main Moderator - approve - status New" + actions.currentTime());
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        checkExpectedResult("feedback is not approve Fail test", createNewFeedback_Page_MainModerator.isBtnDeleteIsPresent());
        // assertTrue(webdriver.findElement(By.cssSelector(".title")).getText().contains("text"));
    }



/*
    //NEGATIVE MODERATOR dev-testuser14 (AZS) не должен видеть обращение по направлению Руководству другого предприятия (ММК), даже если он ответственный

    @Test // (need add time in AppealField to Check real feedbak in FAQ)!!!!!!!!!!!!!!!!!!!
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ() throws InterruptedException {

        //  https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser10@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();

        //    STEP 3 - send feedback back to Main Moderator by Approver

        viewListOfFeedbacks_page_mainModerator.chooseCheckBoxToBackMM();
        viewListOfFeedbacks_page_mainModerator.inputReasonForReturn();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        Thread.sleep(2000);
        //  viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("test - Approver - approve - status New" + actions.currentTime());

        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.assignNewResponsible();
        viewListOfFeedbacks_page_mainModerator.chooseNewApprover("Верезумская Ирина Викторовна");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();


        //    STEP 5 - open and approve feedback by New Approver
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser14@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 6 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.publishInFAQ();

        //   STEP 7 - open and check last feedback in FAQ

        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isFeedbackInFAQList());

    }*/




}