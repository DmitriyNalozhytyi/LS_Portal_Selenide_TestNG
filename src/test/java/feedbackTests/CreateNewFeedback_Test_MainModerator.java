package feedbackTests;

import org.junit.Test;
import parentTest.ParentTest;

public class CreateNewFeedback_Test_MainModerator extends ParentTest {

    @Test
    public void createNewFeedback_Portal_Management_MH() throws InterruptedException {

        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        createNewFeedback_Page_MainModerator.choose_CommunicationChannelField();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated();
      //  viewListOfFeedbacks_page_mainModerator.III();

    //    viewListOfFeedbacks_page_mainModerator.test();

       // viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();


    }



}
