package polls.fieldVerification;

import constants.ErrorMessage;
import constants.Pages;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.polls.PollCreatePage;
import pages.polls.PollsManagementPage;
import parentTest.ParentTest;

@Epic("Polls")
@Feature("Field validation")
public class FieldValidationTest extends ParentTest {

    @Test(description = "Check when no type of answer select it leads to the validation")
    public void checkValidationAnswerType() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage()
                .isPageOpened()
                .clickButton("Создать опрос", PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .clickButton("Сохранить", PollCreatePage.btnSavePoll())
                .checkValidationMessageFor(PollCreatePage.FieldValidation.ANSWER_TYPE, ErrorMessage.FIELD_IS_REQUIRED);
    }

    @Test(description = "Check when the target audience is not selected it leads to the validation")
    public void checkValidationTargetAudience() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage()
                .isPageOpened()
                .clickButton("Создать опрос", PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .clickButton("Сохранить", PollCreatePage.btnSavePoll())
                .checkValidationMessageFor(PollCreatePage.FieldValidation.TARGET_AUDIENCE, ErrorMessage.FIELD_IS_REQUIRED);
    }
}
