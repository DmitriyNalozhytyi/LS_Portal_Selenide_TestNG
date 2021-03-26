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
        actions.insertText(inputEmail,email);
        //InputEmail.sendKeys("vadim.kornienko@lizard-soft.com");
    }

    @Step
    public void insertPassword(String password) throws InterruptedException {
        Thread.sleep(500);
        actions.insertText(inputPassword,password);
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
        actions.insertText(inputEmail,Email)
               .click(btnNext)
               .insertText(inputPassword,Pass)
               .click(btnNext)
               .click(btnNext);
    }

    @Step
    public void ReAuthorization(String Email, String Pass) throws InterruptedException {
        actions.waitToBeVisible(inputEmail);
        actions.insertText(inputEmail,Email);
        actions.waitUntilBecomeClickable(btnNext);
        actions.click(btnNext);
        Thread.sleep(2000);
        actions.waitToBeVisible(inputPassword);
        actions.insertText(inputPassword,Pass);
        actions.waitUntilBecomeClickable(btnNext);
        actions.click(btnNext);
        /*actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);*/
    }

    @Step("Login as {0}")
    public void loginAs(USERS users) {
        switch (users){
            case DEV_TESTUSER15: authorization(Config.HostsData.METINVEST.value[1], Config.HostsData.METINVEST.value[2]);
        }
    }
}
