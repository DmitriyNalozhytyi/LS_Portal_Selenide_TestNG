package recruiters;

import constants.Data;
import constants.SuccessMessages;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.recruiter.RecruiterPage;
import parentTest.ParentTest;

@Epic("Recruiter")
public class DeleteRecruiterTest extends ParentTest {

    @Story("Delete recruiter")
    @Test(description = "Verify deleting of recruiter")
    public void deleteRecruiter() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToRecruiterPage();

        new RecruiterPage()
                .delete(Data.RECRUITER_1)
                .checkPopUpMessage(SuccessMessages.RECRUITER_DELETED)
                .closePopUp()
                .checkIfRecruiterDeleted(Data.RECRUITER_1);
    }
}
