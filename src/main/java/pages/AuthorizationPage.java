package pages;

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



    public void insertEmail(String email) {
        actions.insertText(InputEmail,email);
        //InputEmail.sendKeys("vadim.kornienko@lizard-soft.com");
    }

    public void insertPassword(String password){
        actions.insertText(InputPassword,password);
    }

    public void pressBtnNext() {
        actions.click(BtnNext);
    }

    public void pressBtnWorkAccount() {
        actions.click(BtnWorkAccount);
    }
}
