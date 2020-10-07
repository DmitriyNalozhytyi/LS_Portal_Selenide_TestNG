package mainPage;

import org.junit.Test;
import parentTest.ParentTest;


public class MainPageTest extends ParentTest {

    //@Test
    public void openAllNews() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
    }
}
