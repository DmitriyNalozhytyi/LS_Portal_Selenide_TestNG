package authorization;

import org.junit.Test;
import parentTest.ParentTest;

public class Authorization extends ParentTest {

    @Test
    public void validLogin() throws InterruptedException {
        authorizationPage.openPage();
        authorizationPage.insertEmail("dev-testuser3@dev.lizard.net.ua");
        authorizationPage.pressBtnNext();
        authorizationPage.insertPassword("Pa$$w0rd");
        authorizationPage.pressBtnNext();
        authorizationPage.pressBtnNext();
        //authorizationPage.pressBtnWorkAccount();

    }
}
