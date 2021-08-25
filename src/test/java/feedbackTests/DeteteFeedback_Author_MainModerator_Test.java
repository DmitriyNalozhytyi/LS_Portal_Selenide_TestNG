package feedbackTests;

import constants.SiteMenu;
import org.junit.Test;
import parentTest.ParentTest_OLD;

public class DeteteFeedback_Author_MainModerator_Test extends ParentTest_OLD {

     /*
    https://metinvest-intranet-tests.azurewebsites.net/admin/responsibles

    MM - Main Moderator
    M - Moderator ( dev-testuser11@dev.lizard.net.ua   MMK  Восьмирко Дмитрий Семенович )
    Ap - Approver ( dev-testuser10@dev.lizard.net.ua   MMK  Сокирко Елена Викторовна)


    for NEGETIVE cases

       moderator -  dev-testuser14@dev.lizard.net.ua   AZK  Верезумская Ирина Викторовна- moderator - for negative tests, when Portal_Management_Company not for AZK
       main moderator - dev-testuser14@dev.lizard.net.ua AZK
    */

    /*

     !!!!TEST-CASES:


      BLOCK 1: FLOW. AUTHOR - MM, DELETE FEEDBACK(only by MM) AND CHECK THAT FEEDBACK DELETED

    TEST-CASE 1:  createFeedbackByMM_Portal_Management_MH__DeleteFeedbackByMM_StatusNew
    TEST-CASE 2:  createFeedbackByMM_Portal_Management_MH__ApprovebyAp__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproval
    TEST-CASE 3:  createFeedbackByMM_Portal_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproved

    TEST-CASE 4:  createFeedbackByMM_Management_Company_MH__DeleteFeedbackByMM_StatusNew
    TEST-CASE 5:  createFeedbackByMM_Management_Company_MH__ApprovebyAp__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproval
    TEST-CASE 6:  createFeedbackByMM_Management_Company_MH__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproved

    TEST-CASE 7:  createFeedbackByMM_PersonalMeetings_Management_MH__DeleteFeedbackByMM_StatusNew
    TEST-CASE 8:  createFeedbackByMM_InfoConsLine_Management_MH__DeleteFeedbackByMM_StatusNew
    TEST-CASE 9:  createFeedbackByMM_CorpMassMedia_Management_MH__DeleteFeedbackByMM_StatusNew

    TEST-CASE 10:  createFeedbackByMM_PersonalMeetings_Management_Company__DeleteFeedbackByMM_StatusNew
    TEST-CASE 11:  createFeedbackByMM_InfoConsLine_Management_Company__DeleteFeedbackByMM_StatusNew
    TEST-CASE 12:  createFeedbackByMM_CorpMassMedia_Management_Company__DeleteFeedbackByMM_StatusNew

    TEST-CASE 13:  createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved
    TEST-CASE 14:  createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved
    TEST-CASE 15:  createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved

    TEST-CASE 16:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved
    TEST-CASE 17:  createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved
    TEST-CASE 18:  createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved

     */

//TEST-CASE 1

    @Test
    public void createFeedbackByMM_Portal_Management_MH__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 2

    @Test
    public void createFeedbackByMM_Portal_Management_MH__ApprovebyAp__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproval() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
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
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closeFeedbackCard();

        //    STEP 4 - open last approved by Approver feedback and delete by Main Moderator and publish
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 3

    @Test
    public void createFeedbackByMM_Portal_Management_MH__ApprovebyMM__DeleteFeedbackByMM_StatusOnApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 - delete by MM
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

//TEST-CASE 4

    @Test
    public void createFeedbackByMM_Management_Company_MH__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 5

    @Test
    public void createFeedbackByMM_Management_Company_MH__ApprovebyAp__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproval() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
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
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        Thread.sleep(2000);
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();
        viewListOfFeedbacks_page_mainModerator.closeFeedbackCard();

        //    STEP 4 - open last approved by Approver feedback and delete by Main Moderator and publish
        viewListOfFeedbacks_page_mainModerator.exitFromAccount();
        authorizationPage.ReAuthorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 6

    @Test
    public void createFeedbackByMM_Management_Company_MH__ApprovebyMM_DeleteFeedbackByMM_StatusOnApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 - delete by MM
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 7

    @Test
    public void createFeedbackByMM_PersonalMeetings_Management_MH__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");

        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }


    //TEST-CASE 8

    @Test
    public void createFeedbackByMM_InfoConsLine_Management_MH__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_InfoConsLine();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertPhoneNumber("+(380)999-77-77");
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 9

    @Test
    public void createFeedbackByMM_CorpMassMedia_Management_MH__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_CorpMassMedia();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 10

    @Test
    public void createFeedbackByMM_PersonalMeetings_Management_Company__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }


    //TEST-CASE 11

    @Test
    public void createFeedbackByMM_InfoConsLine_Management_Company__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_InfoConsLine();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertPhoneNumber("+(380)999-77-77");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 12

    @Test
    public void createFeedbackByMM_CorpMassMedia_Management_Company__DeleteFeedbackByMM_StatusNew() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_CorpMassMedia();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and delete By MainModerator
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusNew();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 13

    @Test
    public void createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");

        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        //viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());

    }


    //TEST-CASE 14

    @Test
    public void createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_InfoConsLine();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertPhoneNumber("+(380)999-77-77");
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        //viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 15

    @Test
    public void createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_CorpMassMedia();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        //viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 16

    @Test
    public void createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        //viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }


    //TEST-CASE 17

    @Test
    public void createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_InfoConsLine();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertPhoneNumber("+(380)999-77-77");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }

    //TEST-CASE 18

    @Test
    public void createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM_DeleteFeedbackByMM_StatusApproved() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua", "Pa$$w0rd");
        mainPage.goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_CorpMassMedia();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.choose_Direction_ManagementCompany_Field();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback and approve
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.enterTextInTo_AppealField_FeedbackCard_status_New("Main");
        viewListOfFeedbacks_page_mainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUp();

        //    STEP 3 -  delete feedback by Main Moderator
        //viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.deleteFeedbackStatusOnApprovalOrApproved();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        checkExpectedResult("feedback is not deleted", viewListOfFeedbacks_page_mainModerator.isFeedbackDeleted());
    }











}
