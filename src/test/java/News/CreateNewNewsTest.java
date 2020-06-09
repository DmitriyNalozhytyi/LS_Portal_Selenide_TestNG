package News;

import org.junit.Test;
import parentTest.ParentTest;

public class CreateNewNewsTest extends ParentTest {

    @Test
    public void createNewNews() throws InterruptedException {
        authorizationPage.authorization();
        mainPage.GoToAllNews();
        Thread.sleep(5000);
        allNewsPage.enterTextInToFieldClerk();
        //allNewsPage.choosePublicationTypeNews();
        //allNewsPage.clickOnRBtnNewNews();
        allNewsPage.ClickOnBtnCreate();
        newNewsPage.chooseDate();
        newNewsPage.writeTitle("text");
        newNewsPage.addImageBtn();
//test
    }
}
