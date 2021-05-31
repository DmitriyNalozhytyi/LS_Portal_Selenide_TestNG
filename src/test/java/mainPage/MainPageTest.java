package mainPage;

import base.ParentTest_OLD;


public class MainPageTest extends ParentTest_OLD {

    //@Test
    public void openAllNews() throws InterruptedException {
        authorizationPage.authorization("dev-testuser3@dev.lizard.net.ua","Pa$$w0rd");
        mainPage.goToAllNews();
    }
}
