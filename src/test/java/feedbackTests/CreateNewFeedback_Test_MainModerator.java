package feedbackTests;

import org.junit.Test;
import parentTest.ParentTest;
import parentTest.ParentTest_OLD;

public class CreateNewFeedback_Test_MainModerator extends ParentTest_OLD {
//
 /*   @Test
    public void createNewFeedback__Portal__Management_MH__Topic_Production() throws InterruptedException {

        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.checkFeedbackIsCreated();
    }*/

///////
    @Test
    public void createNewFeedback__Portal__Management_MH__Topic_Rundom() throws InterruptedException {

        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_Rundom_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        viewListOfFeedbacks_page_mainModerator.checkFeedbackIsCreated();
    }


/*

    @Test
    public void createNewFeedbackFromFeedbackList() throws InterruptedException {
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateVievListOfFeedbacksPage();
        viewListOfFeedbacks_page_mainModerator.clickOnCreateBtn();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
    }

    //need to add ER - feedback visible in FAQ
    @Test
    public void openFeedbackInFAQ () throws InterruptedException {
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.openTopicProductFAQ();
        createNewFeedback_Page_MainModerator.openLastFeebbackInTopicProductFAQ();
      //feedback visible in FAQ


    }
*/

}
