package News;

import libs.Actions;
import org.junit.Test;
import pages.NewNewsPage;
import parentTest.ParentTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateNewNewsTest extends ParentTest {

    @Test
    public void createNewNews() throws InterruptedException {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //LocalDateTime currentTime = LocalDateTime.now();
        //actions.loremIpsum();
        //loremIpsum.lorem();
        authorizationPage.authorization();
        mainPage.GoToAllNews();
        //Thread.sleep(5000);
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
