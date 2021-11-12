package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import config.Config;
import constants.USER;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage extends ParentPage {

    private final SelenideElement inputEmail                = $("#i0116");
    private final SelenideElement btnNext                   = $("#idSIButton9");
    private final SelenideElement btnWorkAccount            = $("#aadTileTitle");
    private final SelenideElement inputPassword             = $("#i0118");
    private final SelenideElement btnAccount                = $(".head_right").find(".mat-button-wrapper").find("div");
  //  private final SelenideElement btnExit                   = $(".right_box .menu-item:nth-of-type(4) a");
    private final SelenideElement btnExit                   = $(".left-icon-item_exit");
    private final SelenideElement changeAccount             = $("#otherTileText");

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
        logOut();
        actions.enterText(inputEmail, Email, "login")
               .click(btnNext, "Next")
               .enterText(inputPassword,Pass, "password")
               .click(btnNext,"Next");

        if (btnNext.exists()) {
               actions.click(btnNext, "Next");
        }
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

    @Step
    public void logOut() {
        if (btnAccount.exists()) {
            actions.click(btnAccount, "User account")
                    .click(btnExit, "Exit")
                    .click(changeAccount.should(Condition.appear, Duration.ofSeconds(10)), "Change account");
        }
    }

    /**
     * To login to the system
     * @param USER The value of user's login name and password that are used for authorisation in the system (e.g DEV_TESTUSER15)
     */
    @Step("Login as {0}")
    public void loginAs(USER USER) {
        switch (USER){
            case DEV_TESTUSER15: authorization(Config.HostsData.METINVEST.value[1], Config.HostsData.METINVEST.value[2]); break;
            case DEV_TESTUSER14: authorization(Config.HostsData.METINVEST.value[3], Config.HostsData.METINVEST.value[4]); break;
            case DEV_TESTUSER4:  authorization(Config.HostsData.METINVEST.value[5], Config.HostsData.METINVEST.value[6]); break;
            case DEV_TESTUSER12:  authorization(Config.HostsData.METINVEST.value[11], Config.HostsData.METINVEST.value[12]); break;
            case DEV_TESTUSER11:  authorization(Config.HostsData.METINVEST.value[9], Config.HostsData.METINVEST.value[10]); break;
            case DEV_TESTUSER10:  authorization(Config.HostsData.METINVEST.value[7], Config.HostsData.METINVEST.value[8]); break;
            case DEV_TESTUSER13:  authorization(Config.HostsData.METINVEST.value[13], Config.HostsData.METINVEST.value[14]); break;

        }
    }
}
