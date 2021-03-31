package pages.recruiter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.MessageDialogBox;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class AddRecruiterPage {
    private final SelenideElement dialogTitle = $(".add-dialog__title");
    private final SelenideElement dialogContainer = $(".add-dialog");
    private final SelenideElement inptRecruiter = $$(".vacancy-input").find(Condition.attribute("role", "combobox"));

    private SelenideElement getOptionToSelect() {
        return $(byAttribute("role","listbox")).find(byAttribute("tabindex","0")).waitUntil(Condition.appear,10000);
    }

    private SelenideElement errorContainer() {
        return dialogContainer.find("mat-error").waitUntil(Condition.appear,10000);
    }

    public AddRecruiterPage isPageOpens() {
        Assert.assertEquals(dialogTitle.getText(), WindowTitle.ADD_RECRUITER, WindowTitle.ADD_RECRUITER + " cannot be found");
        return this;
    }

    @Step("Select {0}")
    public AddRecruiterPage selectRecruiter(String text) {
        new Actions()
                .enterText(inptRecruiter,text, "ФИО")
                .selectOption(getOptionToSelect());
        sleep(1000);
        return this;
    }

    @Step("Click the button {0}")
    public AddRecruiterPage clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

    public AddRecruiterPage checkPopUpMessage(String text) {
        Assert.assertEquals(new MessageDialogBox().getMessage(), text, text + " cannot be found");
        return this;
    }

    public void closePopUp() {
        new MessageDialogBox().close();
    }

    public void checkValidationMessage(String message) {
        Assert.assertEquals(errorContainer().getText(), message, message + "is not showing");
    }


}
