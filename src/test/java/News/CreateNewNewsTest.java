package News;

import org.junit.Test;
import parentTest.ParentTest;

public class CreateNewNewsTest extends ParentTest {

    @Test
    public void createNewNews() throws InterruptedException {
        //authorizationPage.authorization();
        mainPage.GoToAllNews();
        allNewsPage.enterTextInToFieldClerk();
        //allNewsPage.choosePublicationTypeNews();
        //allNewsPage.clickOnRBtnNewNews();
        allNewsPage.ClickOnBtnCreate();
        //newNewsPage.chooseDate();
        newNewsPage.writeTitle("Test " + actions.currentTime());
        Thread.sleep(1000);
        newNewsPage.addImageToSlider();
        newNewsPage.writeDescription(loremIpsum.lorem());
        newNewsPage.selectTA();
        newNewsPage.writeTag("#Test");
        newNewsPage.selectContentType();
        newNewsPage.saveAndPublish();
//test
    }
}