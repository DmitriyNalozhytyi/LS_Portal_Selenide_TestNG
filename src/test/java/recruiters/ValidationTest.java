package recruiters;

import constants.Button;
import constants.ErrorMessage;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.recruiter.AddRecruiterPage;
import pages.recruiter.RecruiterPage;
import base.ParentTest;

@Epic("Recruiter")
public class ValidationTest extends ParentTest {

    @Story("Field validation")
    @Test(description = "Verify if empty field leads to the validation")
    public void checkValidation() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER15);

        new MainPage().goToRecruiterPage();

        new RecruiterPage()
                .isPageOpens()
                .clickButton("Добавить рекрутера", Button.ADD_RECRUITER);

        new AddRecruiterPage()
                .isPageOpens()
                .clickButton("Сохранить", Button.SAVE_RECRUITER)
                .checkValidationMessage(ErrorMessage.FIELD_IS_REQUIRED);


    }
}
