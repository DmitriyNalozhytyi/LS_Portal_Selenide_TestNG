package recruiters;

import constants.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.recruiter.AddRecruiterPage;
import pages.vacancy.recruiter.RecruiterPage;
import parentTest.ParentTest;

@Epic("Recruiter")
public class AddRecruiterTest extends ParentTest {

    @Story("Add Recruiter")
    @Test(description = "Check adding of recruiter")
    public void addRecruiter() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToRecruiterPage();

        new RecruiterPage()
                .isPageOpens()
                .clickButton("Добавить рекрутера",Button.ADD_RECRUITER);

        new AddRecruiterPage()
                .isPageOpens()
                .selectRecruiter(Data.RECRUITER_1)
                .clickButton("Сохранить", Button.SAVE_RECRUITER)
                .checkPopUpMessage(SuccessMessages.RECRUITER_SAVED)
                .closePopUp();

        new RecruiterPage()
                .checkForRecruiter(Data.RECRUITER_1);
    }

    @Story("Add Recruiter")
    @Test(description = "Check that one recruiter cannot be added twice", dependsOnMethods = "addRecruiter")
    public void addExistingRecruiter() {

        new RecruiterPage()
                .isPageOpens()
                .clickButton("Добавить рекрутера",Button.ADD_RECRUITER);

        new AddRecruiterPage()
                .isPageOpens()
                .selectRecruiter(Data.RECRUITER_1)
                .clickButton("Сохранить", Button.SAVE_RECRUITER)
                .checkValidationMessage(ErrorMessage.RECRUITER_ALREADY_EXISTS);
    }
}
