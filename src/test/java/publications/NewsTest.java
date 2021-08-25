package publications;


import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import constants.*;
import org.testng.annotations.Test;
import pages.*;
import pages.publications.news.AllNewsPage;
import pages.publications.news.CreateNewsPage;
import parentTest.ParentTest;
import utils.CustomRandom;

public class NewsTest extends ParentTest
{
    Lorem lorem = LoremIpsum.getInstance();

    private final String newsNameRU            = "NEWS_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String newsNameUA            = "NEWS_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create a new article")
    public void createNewArticle() {


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.NEWS);

        new AllNewsPage()
                .isPageOpened(Language.RU)
                .openCreatePublicationPage(PUBLICATION.NEWS);

        new CreateNewsPage()
                .isPageOpens()
                .switchTo(Language.RU)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), newsNameRU)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .switchTo(Language.UA)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), newsNameUA)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .addImageToSlider()
                .selectContentType(CONTENT_TYPE.PHOTO)
                .selectTargetAudience()
                .enterTag("#ATest1", "#ATest2")
                .clickButton(CreateNewsPage.btnSaveAndPublish(), "Сохранить и опубликовать");

        new AllNewsPage()
                .isPageOpened(Language.RU)
                .checkForNews(Language.RU, newsNameRU);
    }

/*
    @Test
    public void checkArticle() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllArticles();
        articlesPageAll.selectRandomArticle();
        newsPage.checkDate();
        newsPage.checkTA();
        newsPage.checkDeleteButton();
        newsPage.checkEditButton();
        newsPage.checkViews();
        newsPage.checkTitle();
        newsPage.checkImage();
        newsPage.checkDescription();
        newsPage.checkTag();
        newsPage.checkNextNewsButton();
        newsPage.checkPreviousNewsButton();
        newsPage.checkLikes();
        newsPage.checkComments();
    }

    @Test
    public void writeComment() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
        newsPageAll.selectRandomNews();
        newsPage.writeComment(loremIpsum.getLorem(1, 2));
        newsPage.btnSendComment.click();
        newsPage.checkTitle();
    }

    @Test
    public void writeCommentReply() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
        //allNewsPage.selectRandomNews();
        //actions.selectNewsByCounter();
        newsPage.writeCommentReply(loremIpsum.getLorem(1, 2));
        newsPage.checkTitle();
    }*/
}
