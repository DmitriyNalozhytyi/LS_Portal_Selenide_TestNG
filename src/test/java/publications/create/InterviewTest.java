package publications.create;


import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import constants.*;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.publications.ViewPublicationPage;
import pages.publications.news.AllNewsPage;
import pages.publications.news.CreateNewsPage;
import parentTest.ParentTest;
import utils.CustomRandom;
import utils.DateTime;

public class InterviewTest extends ParentTest
{
    Lorem lorem = LoremIpsum.getInstance();

    private final String interviewNameRU             = "INTERVIEW_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String interviewNameUA             = "INTERVIEW_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String interviewLeadingParagraphRU = "Interview_Leading_Paragraph_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String interviewLeadingParagraphUA = "Interview_Leading_Paragraph_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create a new Interview")
    public void createNews() {


        new AuthorizationPage().loginAs(USER.DEV_TESTUSER4);
        new MainPage().goTo(Pages.NEWS);

        new AllNewsPage()
                .isPageOpened(Language.RU)
                .openCreatePublicationPage(PUBLICATION.INTERVIEW);

        new CreateNewsPage()
                .isPageOpens()
                .switchTo(Language.RU)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), interviewNameRU)
                .setTextFor("Лидирующий абзац", CreateNewsPage.inpLeadingParagraph(), interviewLeadingParagraphRU)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .switchTo(Language.UA)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), interviewNameUA)
                .setTextFor("Лидирующий абзац", CreateNewsPage.inpLeadingParagraph(), interviewLeadingParagraphUA)
                .setTinyMCEText("Описание Интервью", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .addImageToSlider()
                .selectContentType(CONTENT_TYPE.PHOTO)
                .setDateTimeFor("Дата и время публикации", CreateNewsPage.dtStartInterview(), DateTime.getDate("YYYY.MMMM.dd HH:mm", 0, "ru"))
                .selectValueFor("Целевая аудитория", CreateNewsPage.ddTargetAudience(), 1)
                .enterTag("#ATest1", "#ATest2")
                .clickButton(CreateNewsPage.btnSaveAndPublish(), "Сохранить и опубликовать");

        new ViewPublicationPage()
                .isPageOpened()
                .isPublicationPresent(Language.RU, interviewNameRU)
                .isPublicationPresent(Language.UA, interviewNameUA)
                .clickButton("Return to the All News Page",ViewPublicationPage.btnBack());

        new AllNewsPage()
                .isPageOpened(Language.RU);
    }
}
