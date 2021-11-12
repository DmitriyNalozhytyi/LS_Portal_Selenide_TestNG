package polls.takingPolls;

import constants.Language;
import constants.Pages;
import constants.USER;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.polls.AllPollsPage;
import pages.polls.PollCreatePage;
import pages.polls.PollsManagementPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Polls")
@Feature("Taking a poll")
public class TakingPollTest extends ParentTest {

    private final String poll_1 = "POLL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String poll_2 = "POLL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String poll_3 = "POLL_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Check if selected answer is marked as passed")
    public void checkIfSelectedAnswerMarkedAsPassed() {

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER4);

        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage().isPageOpened().clickButton("Создать опрос",PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .switchToLanguage(Language.RU)
                    .setTextFor("Вопрос", PollCreatePage.inputPollTitle(), poll_1)
                    .setTextFor("Ответ 1", new PollCreatePage().inputAnswer(1), "Ответ 1")
                    .setTextFor("Ответ 2", new PollCreatePage().inputAnswer(2), "Ответ 2")
                    .setTextFor("Ответ 3", new PollCreatePage().inputAnswer(3), "Ответ 3")
                    .setTextFor("Ответ 4", new PollCreatePage().inputAnswer(4), "Ответ 4")
                    .setTextFor("Ответ 5", new PollCreatePage().inputAnswer(5), "Ответ 5")
                    .setTextFor("Ответ 6", new PollCreatePage().inputAnswer(6), "Ответ 6")
                    .setTextFor("Ответ 7", new PollCreatePage().inputAnswer(7), "Ответ 7")
                    .setTextFor("Ответ 8", new PollCreatePage().inputAnswer(8), "Ответ 8")
                .selectAnswer(PollCreatePage.ANSWER.ONE_ANSWER)
                .toggle("Отображать на главной", PollCreatePage.btnDisplayOnMainPage(), true)
                .toggle("Группа Метинвест", PollCreatePage.btnMetInvestGroup(), true)
                .clickButton("Сохранить и опубликовать",PollCreatePage.btnSaveAndPublishPoll());

        new PollsManagementPage()
                .isPageOpened()
                .checkIfPollCreated(Language.RU, poll_1);

        new MainPage()
                .goTo(Pages.MAIN)
                .scrollTo(MainPage.getPollsContainer())
                .searchPoll(poll_1)
                .selectAnswerByPosition(2)
                .sendAnswer()
                .checkIfAnswerSelected();
    }

    @Test(description = "Check if passed poll in the list of passed")
    public void checkIfPassedPollInTheListOfPassed() {

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER4);

        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage().isPageOpened().clickButton("Создать опрос",PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .switchToLanguage(Language.RU)
                    .setTextFor("Вопрос", PollCreatePage.inputPollTitle(), poll_2)
                    .setTextFor("Ответ 1", new PollCreatePage().inputAnswer(1), "Ответ 1")
                    .setTextFor("Ответ 2", new PollCreatePage().inputAnswer(2), "Ответ 2")
                    .setTextFor("Ответ 3", new PollCreatePage().inputAnswer(3), "Ответ 3")
                    .setTextFor("Ответ 4", new PollCreatePage().inputAnswer(4), "Ответ 4")
                    .setTextFor("Ответ 5", new PollCreatePage().inputAnswer(5), "Ответ 5")
                    .setTextFor("Ответ 6", new PollCreatePage().inputAnswer(6), "Ответ 6")
                    .setTextFor("Ответ 7", new PollCreatePage().inputAnswer(7), "Ответ 7")
                    .setTextFor("Ответ 8", new PollCreatePage().inputAnswer(8), "Ответ 8")
                .selectAnswer(PollCreatePage.ANSWER.ONE_ANSWER)
                .toggle("Отображать на главной", PollCreatePage.btnDisplayOnMainPage(), true)
                .toggle("Группа Метинвест", PollCreatePage.btnMetInvestGroup(), true)
                .clickButton("Сохранить и опубликовать",PollCreatePage.btnSaveAndPublishPoll());

        new PollsManagementPage()
                .isPageOpened()
                .checkIfPollCreated(Language.RU, poll_2);

        new MainPage()
                .goTo(Pages.MAIN)
                .scrollTo(MainPage.getPollsContainer())
                .searchPoll(poll_2)
                .selectAnswerByPosition(2)
                .sendAnswer()
                .checkIfAnswerSelected()
                .goTo(Pages.ALL_POLLS);

        new AllPollsPage()
                .isPageOpened()
                .switchTo(AllPollsPage.Tab.PASSED)
                .checkIfPollPresent(poll_2);
    }

    @Test(description = "Check if not passed poll in the list of not passed")
    public void checkIfNotPassedPollInTheListOfNotPassed() {

        new AuthorizationPage().loginAs(USER.DEV_TESTUSER4);

        new MainPage().goTo(Pages.POLLS);

        new PollsManagementPage().isPageOpened().clickButton("Создать опрос",PollsManagementPage.btnCreatePoll());

        new PollCreatePage()
                .isPageOpened()
                .switchToLanguage(Language.RU)
                .setTextFor("Вопрос", PollCreatePage.inputPollTitle(), poll_3)
                .setTextFor("Ответ 1", new PollCreatePage().inputAnswer(1), "Ответ 1")
                .setTextFor("Ответ 2", new PollCreatePage().inputAnswer(2), "Ответ 2")
                .setTextFor("Ответ 3", new PollCreatePage().inputAnswer(3), "Ответ 3")
                .setTextFor("Ответ 4", new PollCreatePage().inputAnswer(4), "Ответ 4")
                .setTextFor("Ответ 5", new PollCreatePage().inputAnswer(5), "Ответ 5")
                .setTextFor("Ответ 6", new PollCreatePage().inputAnswer(6), "Ответ 6")
                .setTextFor("Ответ 7", new PollCreatePage().inputAnswer(7), "Ответ 7")
                .setTextFor("Ответ 8", new PollCreatePage().inputAnswer(8), "Ответ 8")
                .selectAnswer(PollCreatePage.ANSWER.ONE_ANSWER)
                .toggle("Отображать на главной", PollCreatePage.btnDisplayOnMainPage(), true)
                .toggle("Группа Метинвест", PollCreatePage.btnMetInvestGroup(), true)
                .clickButton("Сохранить и опубликовать",PollCreatePage.btnSaveAndPublishPoll());

        new PollsManagementPage()
                .isPageOpened()
                .checkIfPollCreated(Language.RU, poll_3);

        new MainPage()
                .goTo(Pages.ALL_POLLS);

        new AllPollsPage()
                .isPageOpened()
                .switchTo(AllPollsPage.Tab.NOT_PASSED)
                .checkIfPollPresent(poll_3);

    }


    @AfterClass(description = "Clean up")
    public void cleanUp() {
        new AuthorizationPage().loginAs(USER.DEV_TESTUSER4);
        new MainPage().goTo(Pages.POLLS);
        new PollsManagementPage()
                .isPageOpened()
                .deletePoll(Language.RU, poll_1)
                .checkIfPollDeleted(Language.RU, poll_1)
                .deletePoll(Language.RU, poll_2)
                .checkIfPollDeleted(Language.RU, poll_2)
                .deletePoll(Language.RU, poll_3)
                .checkIfPollDeleted(Language.RU, poll_3);
    }
}
