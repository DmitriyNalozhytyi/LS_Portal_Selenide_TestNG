package mainPage;

import org.junit.Test;
import parentTest.ParentTest;


public class MainPageTest extends ParentTest {

    //@Test
    public void openAllNews() throws InterruptedException {
        mainPage.goToAllNews();
    }
}
