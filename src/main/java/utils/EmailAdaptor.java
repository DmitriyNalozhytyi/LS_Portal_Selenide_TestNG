package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class EmailAdaptor {

    private static final String emailURL                        = "https://mail.tm/en";

    private static final SelenideElement emailContainer         = $("#address");
    private static final SelenideElement loginContainer         = $(byAttribute("role","dialog"));

    private static final SelenideElement btnRefresh             = $(withText("Refresh"));
    private static final SelenideElement currentEmail           = $(withText("Verify Email Address"));
    private static final SelenideElement btnBack                = $$("a").find(Condition.matchText("Back"));
    private static final SelenideElement accountMenu            = $("#accounts-menu");
    private static final SelenideElement accountList            = $("#accounts-list");
    private static final SelenideElement fldEmail               = loginContainer.$("#address");
    private static final SelenideElement fldPassword            = loginContainer.$("#password");
    private static final SelenideElement btnLogin               = loginContainer.$(withText("Login"));

    public EmailAdaptor() {
        WebDriverRunner.getWebDriver().manage().window().setSize(new Dimension(1800,1080));
    }

    private SelenideElement geMenuItem(String menuItem) {
        return accountList.find("div").findAll("div").find(Condition.exactText(menuItem));
    }

    @Step("Select {0}")
    private void select(String menuItem) {
        geMenuItem(menuItem).click();
    }

    @Step("Open account menu")
    private void openAccountMenu() {
        accountMenu.should(Condition.exist).click();
    }

    @Step("Get email address")
    public String getEmailAddress() {
        open(emailURL);
        sleep(5000);
        return emailContainer.getValue();
    }

    @Step("Select email")
    public EmailAdaptor selectEmail() {
        currentEmail.click();
        return this;
    }

    @Step("Click the button Back")
    public EmailAdaptor back() {
        btnBack.click();
        return this;
    }

    @Step("Refresh Inbox")
    public EmailAdaptor refreshToGetEmail(String emailTitle) {
        while (!$(withText(emailTitle)).exists()) {
            btnRefresh.click();
            sleep(5000);
        }
        return this;
    }

    @Step("Login as user")
    public EmailAdaptor loginAsUser(String userName, String password) {
        openAccountMenu();
        select("Login");
        fldEmail.val(userName);
        fldPassword.val(password);
        btnLogin.click();
        return this;
    }

    @Step("Open temp email site")
    public EmailAdaptor openSite() {
        open(emailURL);
        return this;
    }
}

