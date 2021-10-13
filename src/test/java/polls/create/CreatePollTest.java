package polls.create;

import constants.Language;
import constants.Pages;
import constants.USERS;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.polls.PollCreatePage;
import pages.polls.PollsManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Polls")
@Feature("Create poll")
public class CreatePollTest extends ParentTest {

    private final String pollTitleRU            = "POLL_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String pollTitleUA            = "POLL_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create poll with one answer")
    public void createOneAnswerPoll() {

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage().isPageOpened().clickButton("Создать опрос",PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .switchToLanguage(Language.RU)
                    .setTextFor("Вопрос", PollCreatePage.inputPollTitle(), pollTitleRU)
                    .setTextFor("Ответ 1", new PollCreatePage().inputAnswer(1), "Ответ 1")
                    .setTextFor("Ответ 2", new PollCreatePage().inputAnswer(2), "Ответ 2")
                    .setTextFor("Ответ 3", new PollCreatePage().inputAnswer(3), "Ответ 3")
                    .setTextFor("Ответ 4", new PollCreatePage().inputAnswer(4), "Ответ 4")
                    .setTextFor("Ответ 5", new PollCreatePage().inputAnswer(5), "Ответ 5")
                    .setTextFor("Ответ 6", new PollCreatePage().inputAnswer(6), "Ответ 6")
                    .setTextFor("Ответ 7", new PollCreatePage().inputAnswer(7), "Ответ 7")
                    .setTextFor("Ответ 8", new PollCreatePage().inputAnswer(8), "Ответ 8")
                .switchToLanguage(Language.UA)
                    .setTextFor("Вопрос", PollCreatePage.inputPollTitle(), pollTitleUA)
                    .setTextFor("Відповідь 1", new PollCreatePage().inputAnswer(1), "Відповідь 1")
                    .setTextFor("Відповідь 2", new PollCreatePage().inputAnswer(2), "Відповідь 2")
                    .setTextFor("Відповідь 3", new PollCreatePage().inputAnswer(3), "Відповідь 3")
                    .setTextFor("Відповідь 4", new PollCreatePage().inputAnswer(4), "Відповідь 4")
                    .setTextFor("Відповідь 5", new PollCreatePage().inputAnswer(5), "Відповідь 5")
                    .setTextFor("Відповідь 6", new PollCreatePage().inputAnswer(6), "Відповідь 6")
                    .setTextFor("Відповідь 7", new PollCreatePage().inputAnswer(7), "Відповідь 7")
                    .setTextFor("Відповідь 8", new PollCreatePage().inputAnswer(8), "Відповідь 8")
                .selectAnswer(PollCreatePage.ANSWER.ONE_ANSWER)
                .toggle("Отображать на главной", PollCreatePage.btnDisplayOnMainPage(), true)
                .toggle("Группа Метинвест", PollCreatePage.btnMetInvestGroup(), true)
                .clickButton("Сохранить и опубликовать",PollCreatePage.btnSaveAndPublishPoll());

        new PollsManagementPage()
                .isPageOpened()
                .checkIfPollCreated(Language.RU, pollTitleRU)
                .checkIfPollCreated(Language.UA, pollTitleUA);
    }


    @AfterClass(description = "Clean up")
    public void cleanUp() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.POLLS);
        new PollsManagementPage()
                .isPageOpened()
                .deletePoll(Language.RU, pollTitleRU)
                .checkIfPollDeleted(Language.RU, pollTitleRU)
                .checkIfPollDeleted(Language.UA, pollTitleUA);
    }
}
