package feedbackTests;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import parentTest.ParentTest;
import parentTest.ParentTest_OLD;

public class CheckFields extends ParentTest_OLD {

//CheckFields_Feedback_Author_MainModerator_Test

    @Test
    public void checkFieldValidation_CreateNewFeedback_Portal_ByMM() throws InterruptedException {
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        //check field  TopicField
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isField_TopicField_Required());
        //check field  AppealField
        createNewFeedback_Page_MainModerator.clearField_AppealField();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isField_AppealField_Required());
    }

    @Test
    public void checkFieldValidation_CreateNewFeedback_PersonalMeetings_ByMM() throws InterruptedException {
        authorizationPage.authorization("dev-testuser12@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.navigateToCreateNewFeedbackPage();
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        //check field Speaker
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        checkExpectedResult("no feedback in FAQList", createNewFeedback_Page_MainModerator.isField_Speaker_Required());



        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_PersMeet();
        createNewFeedback_Page_MainModerator.chooseDate();
        createNewFeedback_Page_MainModerator.chooseSpeakerOrInitiatorInPeolePeakerField("Сокирко Елена Викторовна");
        createNewFeedback_Page_MainModerator.insertCountOfPeopleField("5");

        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();


    }







/*
        createNewFeedback_Page_MainModerator.choose_CommunicationChannel_Portal();
        createNewFeedback_Page_MainModerator.choose_TopicField();
        createNewFeedback_Page_MainModerator.enterTextInTo_AppealField("test" + actions.currentTime());
        createNewFeedback_Page_MainModerator.clickOnSendBtn();
        viewListOfFeedbacks_page_mainModerator.closePopUpFeedbackCreated_And_RememberFeedbackNumber();
        // viewListOfFeedbacks_page_mainModerator.openLastCreatedFeedback();
        viewListOfFeedbacks_page_mainModerator.checkFeedbackIsCreated();*/

}
