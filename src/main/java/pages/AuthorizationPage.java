package pages;

import com.codeborne.selenide.SelenideElement;
import config.Config;
import constants.USERS;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage extends ParentPage {

    private final SelenideElement inputEmail = $("#i0116");
    private final SelenideElement btnNext = $("#idSIButton9");
    private final SelenideElement btnWorkAccount = $("#aadTileTitle");
    private final SelenideElement inputPassword = $("#i0118");

    @Step
    public void insertEmail(String email) {
        actions.enterText(inputEmail,email);
        //InputEmail.sendKeys("vadim.kornienko@lizard-soft.com");
    }

    @Step
    public void insertPassword(String password) throws InterruptedException {
        Thread.sleep(500);
        actions.enterText(inputPassword,password);
    }

    @Step
    public void pressBtnNext() {
        actions.click(btnNext);
    }

    @Step
    public void pressBtnWorkAccount() {
        actions.click(btnWorkAccount);
    }

    @Step("{0} / {1}")
    public void authorization(String Email, String Pass) {
        actions.enterText(inputEmail, Email, "login")
               .click(btnNext, "Next")
               .enterText(inputPassword,Pass, "password")
               .click(btnNext,"Next")
               .click(btnNext, "Next");
    }

    @Step
    public void ReAuthorization(String Email, String Pass) throws InterruptedException {
        actions.waitToBeVisible(inputEmail);
        actions.enterText(inputEmail,Email);
        actions.waitUntilBecomeClickable(btnNext);
        actions.click(btnNext);
        Thread.sleep(2000);
        actions.waitToBeVisible(inputPassword);
        actions.enterText(inputPassword,Pass);
        actions.waitUntilBecomeClickable(btnNext);
        actions.click(btnNext);
        /*actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);*/
    }

    /**
     * To login to the system
     * @param users The value of user's login name and password that are used for authorisation in the system (e.g DEV_TESTUSER15)
     */
    @Step("Login as {0}")
    public void loginAs(USERS users) {
        switch (users){
            case DEV_TESTUSER15: authorization(Config.HostsData.METINVEST.value[1], Config.HostsData.METINVEST.value[2]); break;
            case DEV_TESTUSER14: authorization(Config.HostsData.METINVEST.value[3], Config.HostsData.METINVEST.value[4]); break;
        }
    }
}
