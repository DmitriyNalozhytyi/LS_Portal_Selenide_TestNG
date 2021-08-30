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



@Epic("Feedback")
@Feature("MainFlow. Author - Main Moderator")
public class Feedback_MainFlow_Author_MainModerator_Refactor_Test extends ParentTest {

    //TestCase 1
    @Story("create Feedback by Main Moderator")
    @Test(description = "createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApproveByMM__PubFAQ")

    public void createFeedbackByMM_Portal_Management_MH__ApproveByAp_ApproveByMM__PubFAQ() throws InterruptedException {

        //    STEP 1 - create feedback and remember feedback number
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER12);
        new MainPage().goTo(SiteMenu.CREATE_NEW_FEEDBACK);;
        new CreateNewFeedback_Page_MainModerator()
                .choose_CommunicationChannel_Portal()
                .choose_TopicField()
                .enterTextInTo_AppealField("textInAppealField")
                .clickOnSendBtn();
        new ViewListOfFeedbacks_Page_MainModerator()
                .closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        //    STEP 2 - open last created feedback by Approver and approve
        new AuthorizationPage()
                .loginAs(USERS.DEV_TESTUSER10);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback() //need to refactor url
                .clickOnSendBtn()//validate empty field "Response text field"
                .enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower()
                .clickOnSendBtn()
                .closePopUp() //need to refactor
                .closeFeedbackCard(); //need to refactor
      //  STEP 3 - open last approved by Approver feedback and approve by Main Moderator and publish in FAQ
        new AuthorizationPage()
                .loginAs(USERS.DEV_TESTUSER12);
        new ViewListOfFeedbacks_Page_MainModerator()
                .openLastCreatedFeedback()//need to refactor url
                .clickOnSendBtn()
                .publishInFAQ();
        new MainPage()
                .goTo(SiteMenu.CREATE_NEW_FEEDBACK);
        new CreateNewFeedback_Page_MainModerator()
                .openTopicProductFAQ()
                .openLastFeebbackInTopicProductFAQ();
        //  STEP 4 - check last published feedback in FAQ
        new Actions()
               .checkExpectedResult("no feedback in FAQList",new CreateNewFeedback_Page_MainModerator().isFeedbackInFAQList());
    }

    //TEST-CASE 2



}
