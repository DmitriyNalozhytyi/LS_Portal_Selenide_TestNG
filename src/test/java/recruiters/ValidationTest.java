package recruiters;

import constants.Button;
import constants.ErorMeggage;
import constants.USERS;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.vacancy.recruiter.AddRecruiterPage;
import pages.vacancy.recruiter.RecruiterPage;
import parentTest.ParentTest;

@Epic("Recruiter")
public class ValidationTest extends ParentTest {

    @Story("Field validation")
    @Test(description = "Verify if empty field leads to the validation")
    public void checkValidation() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToRecruiterPage();

        new RecruiterPage()
                .isPageOpens()
                .clickButton(Button.ADD_RECRUITER);

        new AddRecruiterPage()
                .isPageOpens()
                .clickButton(Button.SAVE_RECRUITER)
                .checkValidationMessage(ErorMeggage.FIELD_IS_REQUIRED);


    }
}
