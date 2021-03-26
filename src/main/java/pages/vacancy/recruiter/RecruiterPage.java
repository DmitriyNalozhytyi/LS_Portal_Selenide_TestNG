package pages.vacancy.recruiter;

import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.MessageDialogBox;
import components.Table;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RecruiterPage {

    private final SelenideElement pageTitle = $(".vacancies-header__title");
    private final SelenideElement pageHeaderContainer = $(".vacancies-header");

    private final SelenideElement btnAddRecruiter = $(".vacancies-header__button");

    public SelenideElement getBtnAddRecruiter() {
        return btnAddRecruiter;
    }

    public RecruiterPage isPageOpens() {
        Assert.assertEquals(pageTitle.getText(),  WindowTitle.LIST_OF_RECRUITERS, WindowTitle.LIST_OF_RECRUITERS + "cannot be found" );
        return this;
    }

    @Step("Click the button {0}")
    public void clickButton(SelenideElement name) {
        new Actions().click(name);
    }

    @Step ("Check if recruiter present in the table")
    public void checkForRecruiter(String name) {
        sleep(5000);
        Assert.assertEquals( new Table().getCellValue(1,2), name, name + "cannot be found");
    }

    @Step("Delete recruiter {0}")
    public RecruiterPage delete(String name) {
        new Table().delete(name);
        new ConfirmDialogBox()
                .isDialogOpen()
                .confirm(true);
        return this;
    }

    @Step("Close popup")
    public RecruiterPage closePopUp() {
        new MessageDialogBox().close();
        return this;
    }

    public RecruiterPage checkPopUpMessage(String text) {
        Assert.assertEquals( new MessageDialogBox().getMessage(), text, text + " cannot be found");
        return this;
    }

    public void checkIfRecruiterDeleted(String name) {
        Assert.assertNotEquals(new Table().getCellValue(1,2), name, "");
    }
}