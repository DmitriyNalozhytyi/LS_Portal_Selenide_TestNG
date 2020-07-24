package Publications;

import org.junit.Test;
import parentTest.ParentTest;

public class ArticlesTest extends ParentTest {
    @Test
    public void createNewArticle() throws InterruptedException {
        mainPage.goToAllArticles();
        allArticlesPage.clickOnRBtnNewArticle();
        allArticlesPage.ClickOnBtnCreate();
        createNewPublicationPage.writeHeadline("Test " + actions.currentTime());
        createNewPublicationPage.writeTitle("Test " + actions.currentTime());
        Thread.sleep(1000);
        createNewPublicationPage.addImageToSlider();
        createNewPublicationPage.writeDescription(loremIpsum.getLorem(1, 5));
        createNewPublicationPage.selectTA();
        createNewPublicationPage.selectRubric();
        createNewPublicationPage.selectSubrubric();
        createNewPublicationPage.writeTag("#Test");
        createNewPublicationPage.selectContentType();
        createNewPublicationPage.saveAndPublish();
    }
}
