package feedbackTests;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import libs.Actions;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.CreateNewFeedback_Page_MainModerator;
import pages.MainPage;
import pages.ViewListOfFeedbacks_Page_MainModerator;
import parentTest.ParentTest;

 /*
    https://metinvest-intranet-test.azurewebsites.net/ru/admin/responsibles

    MM - Main Moderator
    M - Moderator ( dev-testuser11@dev.lizard.net.ua   MMK Решетняк Алексей Николаевич?? )
    Ap - Approver ( dev-testuser10@dev.lizard.net.ua   MMK  Цыганок Юрий Борисович??)


    for NEGETIVE cases:

       moderator -  dev-testuser14@dev.lizard.net.ua   AZK  Верезумская Ирина Викторовна- moderator - for negative tests, when Portal_Management_Company not for AZK
       main moderator - dev-testuser14@dev.lizard.net.ua AZK

    TEST-CASES:

    BLOCK 1: FLOW. AUTHOR - MM, PORTAL, PUBLISH IN FAQ

   +TEST-CASE 1:  createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApproveByMM__PubFAQ
   +TEST-CASE 2: createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ
   +TEST-CASE 3:  createFeedbackByMM_Portal_Management_MH__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ
   +TEST-CASE 4:   createFeedbackByMM_Portal_Management_MH__BackToMMByAp__ApprovebyMM__PubFAQ

   +TEST-CASE 5:  createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ
   +TEST-CASE 6:  createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyMM__PubFAQ
   BUG TEST-CASE 7:  createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ
   +TEST-CASE 8:  createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyMM__PubFAQ

   +TEST-CASE 9:     createFeedbackByMM_Portal_Management_Company__ApprovebyM__PubFAQ
   +TEST-CASE 10:    createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyM__PubFAQ
   BUG TEST-CASE 11: createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyM__PubFAQ
   BUG TEST-CASE 12: createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByM__ApproveByNewAp_ApprovebyM__PubFAQ
   ??? TEST-CASE 13:   createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyM__PubFAQ

   BLOCK 2: FLOW. AUTHOR - MM, DIFFERENT OF PORTAL, CHECK BTN DELETE VISIBLE(for MM)/NOT VISIBLE (for M)

    TEST-CASE 14:  createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM
    TEST-CASE 15: createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM
    TEST-CASE 16: createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM

    TEST-CASE 17:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM
    TEST-CASE 18: createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM
    TEST-CASE 19: createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM

    TEST-CASE 20:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyM
    TEST-CASE 21: createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyM
    TEST-CASE 22: createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyM

     */

@Epic("Feedback")
@Feature("MainFlow. Author - Main Moderator")
public class Feedback_MainFlow_Author_MainModerator_Refactor_Test extends ParentTest {

    //TEST-CASE 1

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApproveByMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApproveByMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()//validate empty field "Response text field"
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closeFeedbackCard(); //need to refactor
      //  STEP 3 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()//need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 4 - check last published feedback in FAQ
        new Actions()
               .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 2

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_MH__ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
        //    STEP 2 - open last created feedback by Main Moderator and approve and publish in FAQ
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 3 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 3

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_MH__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
        //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .assignNewResponsible()
                .chooseNewApprover("Верезумская Ирина Викторовна")
                .clickOnSendBtn();
        //    STEP 5 - open and approve feedback by New Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER14);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closePopUp(); //need to refactor
        //    STEP 6 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 4

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__BackToMMByAp__ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_MH__BackToMMByAp__ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to Main Moderator  and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //  TEST-CASE 5:  createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve and publish in FAQ
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 3 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",!new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 6

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApproveByMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApproveByMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()//validate empty field "Response text field"
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closeFeedbackCard(); //need to refactor
        //  STEP 3 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()//need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 4 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 7

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .assignNewResponsible()
                .chooseNewApprover("Верезумская Ирина Викторовна")
                .clickOnSendBtn();
        //    STEP 5 - open and approve feedback by New Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER14);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closePopUp(); //need to refactor
        //    STEP 6 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 8

    @Story("create Feedback by Main Moderator")
    @Test(description = " createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyMM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyMM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to Main Moderator  and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //  TEST-CASE 9:  createFeedbackByMM_Portal_Management_Company__ApprovebyM__PubFAQ

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__ApprovebyM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__ApprovebyM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback and approve By Moderator MMK
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //.enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .enterTextInTo_AppealField_FeedbackCard_status_New_M()
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 3 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

   //TEST-CASE 10

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__ApproveByAp_ApprovebyM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()//validate empty field "Response text field"
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closeFeedbackCard(); //need to refactor
        //  STEP 3 - open last approved by Approver feedback and approve by  Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()//need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 4 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 11

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByMM__ApproveByNewAp_ApprovebyM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to Main Moderator and Assign To new Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .assignNewResponsible()
                .chooseNewApprover("Верезумская Ирина Викторовна")
                .clickOnSendBtn();
        //    STEP 5 - open and approve feedback by New Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER14);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closePopUp(); //need to refactor
        //    STEP 6 - open last approved by Approver feedback and approve by  Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 12

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByM__ApproveByNewAp_ApprovebyM__PubFAQ")
    public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__SendToNewApByM__ApproveByNewAp_ApprovebyM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 4 - open feedback backed from Approver to  Moderator and Assign To new Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .assignNewResponsible()
                .chooseNewApprover("Верезумская Ирина Викторовна")
                .clickOnSendBtn();
        //    STEP 5 - open and approve feedback by New Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER14);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closePopUp(); //need to refactor
        //    STEP 6 - open last approved by Approver feedback and approve by  Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 13

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyM__PubFAQ")
             public void createFeedbackByMM_Portal_Management_Company__BackToMMByAp__ApprovebyM__PubFAQ() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                //    STEP 3 - send feedback back to Main Moderator by Approver
                .chooseCheckBoxToBackMM()
                .inputReasonForReturn()
                .clickOnSendBtn()
                .closeFeedbackCard(); //need to refactor
        //    STEP 3 - open last returned feedback by Approver  and approve by  Moderator
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp()
                .publishInFAQ();
        new MainPage()
                .goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 7 - check last published feedback in FAQ
        new Actions()
                .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }


   /* BLOCK 2: FLOW. AUTHOR - MM, DIFFERENT OF PORTAL, CHECK BTN DELETE VISIBLE(for MM)/NOT VISIBLE (for M)

    TEST-CASE 14:  createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM
    TEST-CASE 15: createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM
    TEST-CASE 16: createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM

    TEST-CASE 17:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM
    TEST-CASE 18: createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM
    TEST-CASE 19: createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM

    TEST-CASE 20:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyM
    TEST-CASE 21: createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyM
    TEST-CASE 22: createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyM

    */

    //  TEST-CASE 14:  createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM")
    public void createFeedbackByMM_PersonalMeetings_Management_MH__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_PersonalMeeting()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertCountOfPeopleField("5")
            //    .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
         //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }


    //  TEST-CASE 15:  createFeedbackByMM_Portal_Management_Company__ApprovebyMM__PubFAQ

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM")
    public void createFeedbackByMM_InfoConsLine_Management_MH__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_InfoConsLine()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertPhoneNumber("067 777 77 77")
              //  .insertCountOfPeopleField("5")
              //  .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }

    //  TEST-CASE 16:   createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM

    @Story("create Feedback by Main Moderator")
    @Test(description = " createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM")
    public void  createFeedbackByMM_CorpMassMedia_Management_MH__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_CorpMassMedia()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                //  .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }


    //  TEST-CASE 17:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM")
    public void createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_PersonalMeeting()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertCountOfPeopleField("5")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }


    //  TEST-CASE 18:  createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM")
    public void createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_InfoConsLine()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertPhoneNumber("067 777 77 77")
                //  .insertCountOfPeopleField("5")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }

    //  TEST-CASE 19:   createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM

    @Story("create Feedback by Main Moderator")
    @Test(description = " createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM")
    public void  createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyMM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_CorpMassMedia()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber()
                //    STEP 2 - open last created feedback by Main Moderator and approve
                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }


    //  TEST-CASE 20:  createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyM

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyM")
    public void createFeedbackByMM_PersonalMeetings_Management_Company__ApprovebyM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_PersonalMeeting()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertCountOfPeopleField("5")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by  Moderator and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()

                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }


    //  TEST-CASE 21:  createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyM

    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyM")
    public void createFeedbackByMM_InfoConsLine_Management_Company__ApprovebyM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_InfoConsLine()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .insertPhoneNumber("067 777 77 77")
                //  .insertCountOfPeopleField("5")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by  Moderator and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()

                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }

    //  TEST-CASE 22:   createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyM

    @Story("create Feedback by Main Moderator")
    @Test(description = " createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyM")
    public void  createFeedbackByMM_CorpMassMedia_Management_Company__ApprovebyM() {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER12);
        new MainPage().goTo(Pages.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_CorpMassMedia()
                .chooseDate()
                .chooseSpeakerOrInitiatorInPeolePeakerField("Цыганок Юрий Борисович")
                .choose_Direction_ManagementCompany_Field()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();

        //    STEP 2 - open last created feedback by  Moderator and approve
        new AuthorizationPage()
                .loginAs(USER.DEV_TESTUSER11);
        new ViewListOfFeedbacks_Page_MainModerator()

                .openLastCreatedFeedback() //need to refactor url
                .enterTextInTo_AppealField_FeedbackCard_status_New_MM()//test
                .clickOnSendBtn()
                .closePopUp();
        new Actions()
                .checkExpectedResult("feedback isn't answered",new ViewListOfFeedbacks_Page_MainModerator().isFeedback_AlternativeChannels_Answered());
    }







}
