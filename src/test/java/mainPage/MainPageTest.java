package mainPage;

import org.junit.Test;
import parentTest.ParentTest;


public class MainPageTest extends ParentTest {

    @Test
    public void OpenAllNews(){
        authorizationPage.authorization();
        //Thread.sleep(5000);
        mainPage.GoToAllNews();
    }
}
