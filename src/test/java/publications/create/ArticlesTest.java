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

public class ArticlesTest extends ParentTest
{
    Lorem lorem = LoremIpsum.getInstance();

    private final String articleNameRU             = "Article_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String articleNameUA             = "Article_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String articleLeadingParagraphRU = "Article_Leading_Paragraph_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String articleLeadingParagraphUA = "Article_Leading_Paragraph_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create a new Article")
    public void createArticle() {


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.NEWS);

        new AllNewsPage()
                .isPageOpened(Language.RU)
                .openCreatePublicationPage(PUBLICATION.ARTICLES);

        new CreateNewsPage()
                .isPageOpens()
                .switchTo(Language.RU)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), articleNameRU)
                .setTextFor("Лидирующий абзац", CreateNewsPage.inpLeadingParagraph(), articleLeadingParagraphRU)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .switchTo(Language.UA)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), articleNameUA)
                .setTextFor("Лидирующий абзац", CreateNewsPage.inpLeadingParagraph(), articleLeadingParagraphUA)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .addImageToSlider()
                .selectContentType(CONTENT_TYPE.PHOTO)
                .selectValueFor("Целевая аудитория", CreateNewsPage.ddTargetAudience(), 0)
                .selectValueFor("Рубрика", CreateNewsPage.ddHeading(), 0)
                .selectValueFor("Подрубрика", CreateNewsPage.ddSubheading(), 0)
                .enterTag("#ATest1", "#ATest2")
                .clickButton(CreateNewsPage.btnSaveAndPublish(), "Сохранить и опубликовать");

        new ViewPublicationPage()
                .isPageOpened()
                .isPublicationPresent(Language.RU, articleNameRU)
                .isPublicationPresent(Language.UA, articleNameUA)
                .clickButton("Return to the All News Page",ViewPublicationPage.btnBack());

        new AllNewsPage()
                .isPageOpened(Language.RU);
    }
}
