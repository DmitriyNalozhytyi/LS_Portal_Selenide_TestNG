package Publications;

import org.junit.Test;
import parentTest.ParentTest;

public class NewsTest extends ParentTest {

    @Test
    public void createNewNews() throws InterruptedException {
        //authorizationPage.authorization();
        mainPage.goToAllNews();
        //allNewsPage.enterTextInToFieldClerk();
        //allNewsPage.choosePublicationTypeNews();
        allNewsPage.clickOnRBtnNewNews();
        allNewsPage.ClickOnBtnCreate();
        //newNewsPage.chooseDate();
        createNewPublicationPage.writeTitle("Test " + actions.currentTime());
        Thread.sleep(1000);
        createNewPublicationPage.addImageToSlider();
        createNewPublicationPage.writeDescription(loremIpsum.getLorem(1, 5));
        createNewPublicationPage.selectTA();
        createNewPublicationPage.writeTag("#Test");
        createNewPublicationPage.selectContentType();
        createNewPublicationPage.saveAndPublish();
    }

    @Test
    public void checkNews(){
        mainPage.goToAllNews();
        allNewsPage.selectRandomNews();
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
        mainPage.goToAllNews();
        allNewsPage.selectRandomNews();
        newsPage.writeComment(loremIpsum.getLorem(1, 2));
        newsPage.btnSendComment.click();
    }

    @Test
    public void writeCommentReply() throws InterruptedException {
        mainPage.goToAllNews();
        //allNewsPage.selectRandomNews();
        //actions.selectNewsByCounter();
        newsPage.writeCommentReply(loremIpsum.getLorem(1, 2));
    }
}