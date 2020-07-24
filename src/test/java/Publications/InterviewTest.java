package Publications;

import org.junit.Test;
import parentTest.ParentTest;

public class InterviewTest extends ParentTest {
    @Test
    public void createNewInterview() throws InterruptedException {
        mainPage.goToAllInterview();
        interviewPageAll.selectPublicationTypeInterview();
        createNewPublicationPage.writeHeadline("Test " + actions.currentTime());
        createNewPublicationPage.writeTitle("Test " + actions.currentTime());
        Thread.sleep(1000);
        createNewPublicationPage.addImageToSlider();
        createNewPublicationPage.writeDescription(loremIpsum.getLorem(1, 5));
        createNewPublicationPage.selectRandomTA();
        createNewPublicationPage.selectRandomContentType();
        createNewPublicationPage.writeTag("#Test");
        createNewPublicationPage.saveAndPublish();
    }

    @Test
    public void checkNews(){
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
        mainPage.goToAllNews();
        newsPageAll.selectRandomNews();
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
