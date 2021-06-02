package publications;

import org.junit.Test;
import parentTest.ParentTest_OLD;

@Deprecated
public class NewsTest extends ParentTest_OLD {

    @Test
    public void createNewNews() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
        newsPageAll.clickOnRBtnNewNews();
        newsPageAll.clickOnBtnCreate();
        //newNewsPage.chooseDate();
        createNewPublicationPage.writeTitle("ATest " + actions.currentTime());
        Thread.sleep(1000);
        createNewPublicationPage.addImageToSlider();
        createNewPublicationPage.writeDescription(loremIpsum.getLorem(1, 5));
        createNewPublicationPage.selectRandomTA();
        createNewPublicationPage.selectRandomContentType();
        createNewPublicationPage.writeTag("#ATest");
        createNewPublicationPage.saveAndPublish();
        newsPage.checkTitle();
    }

    @Test
    public void checkNews() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
        newsPageAll.selectRandomNews();
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