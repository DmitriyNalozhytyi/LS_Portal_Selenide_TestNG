package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends ParentPage {


    public AuthorizationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage() {
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/");
    }


    @FindBy(id = "i0116")
    private WebElement InputEmail;

    @FindBy(id = "idSIButton9")
    private WebElement BtnNext;

    @FindBy(id = "aadTileTitle")
    private WebElement BtnWorkAccount;

    @FindBy(id = "i0118")
    private WebElement InputPassword;

    @Step
    public void insertEmail(String email) {
        actions.insertText(InputEmail,email);
        //InputEmail.sendKeys("vadim.kornienko@lizard-soft.com");
    }

    @Step
    public void insertPassword(String password) throws InterruptedException {
        Thread.sleep(500);
        actions.insertText(InputPassword,password);
    }

    @Step
    public void pressBtnNext() {
        actions.click(BtnNext);
    }

    @Step
    public void pressBtnWorkAccount() {
        actions.click(BtnWorkAccount);
    }

    @Step
    public void authorization(String Email, String Pass) throws InterruptedException {

        webDriver.navigate().to("https://metinvest-intranet-test.azurewebsites.net");
        actions.waitToBeVisible(InputEmail);
        actions.insertText(InputEmail,Email);
        actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);
        actions.waitToBeVisible(InputPassword);
        actions.insertText(InputPassword,Pass);
        actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);
        actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);
    }

    @Step
    public void ReAuthorization(String Email, String Pass) throws InterruptedException {
        actions.waitToBeVisible(InputEmail);
        actions.insertText(InputEmail,Email);
        actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);
        Thread.sleep(2000);
        actions.waitToBeVisible(InputPassword);
        actions.insertText(InputPassword,Pass);
        actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);
        /*actions.waitUntilBecomeClickable(BtnNext);
        actions.click(BtnNext);*/
    }
}
