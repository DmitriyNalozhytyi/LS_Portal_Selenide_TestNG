package publications;

import org.junit.Test;
import parentTest.ParentTest;

public class ArticlesTest extends ParentTest {
    @Test
    public void createNewArticle() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllArticles();
        articlesPageAll.clickOnRBtnNewArticle();
        articlesPageAll.clickOnBtnCreate();
        createNewPublicationPage.writeHeadline("ATest " + actions.currentTime());
        createNewPublicationPage.writeTitle("ATest " + actions.currentTime());
        Thread.sleep(1000);
        createNewPublicationPage.addImageToSlider();
        createNewPublicationPage.writeDescription(loremIpsum.getLorem(1, 5));
        createNewPublicationPage.selectRandomTA();
        createNewPublicationPage.selectRandomContentType();
        createNewPublicationPage.selectRandomRubric();
        createNewPublicationPage.selectSubrubric();
        createNewPublicationPage.writeTag("#ATest");
        createNewPublicationPage.saveAndPublish();
        newsPage.checkTitle();
    }

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
    }
}
