package components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ShareDialogBox {
    private final SelenideElement container = $(".shared-dialog");

    private SelenideElement pageTitle() {
        return container.find(".shared-dialog__title");
    }

    private SelenideElement getSenderField() {
        return $(".main-input.vacancy-input.ng-pristine.ng-invalid");
    }

    /**
     * Verify that Share Vacancy dialog box appeared
     */
    @Step("Verify that Share Vacancy dialog box appeared")
    public ShareDialogBox isDialogOpened(String title) {
        Assert.assertEquals(pageTitle().getText(), title, "The title");
        return this;
    }

    /**
     * Select user for share a vacancy
     * @param name the name of sender
     * @param field the field name
     */
    @Step("select {0}")
    public ShareDialogBox selectUser(String name, String field) {
        new Actions().picketUser(getSenderField(), name, field);
        return this;
    }

    /**
     *
     * @param name name of button
     * @param element Selenide element. The list of buttons there Button.
     */
    @Step("Click the button {0}")
    public ShareDialogBox clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

    /**
     * check for sharing
     * @param shareMessage the message that should be in the message box
     */
    @Step("Check if confirmation dialog opens")
    public void checkIfShared(String shareMessage) {
        Assert.assertEquals(new MessageDialogBox().getMessage(),shareMessage, "The message");
        new MessageDialogBox().close();
    }

    /**
     * Open tab
     * @param element - tab element. Selenide element. The list of tabs can be found in Tabs.
     * @param field - name of tab
     */
    @Step("open tab{0}")
    public ShareDialogBox openTab(String field, SelenideElement element) {
        sleep(500);
        new Actions().click(element, field);
        sleep(500);
        return this;
    }

    /**
     * Enter the text into input
     * @param field field name
     * @param element Selenide element to find this field. The list of fields can be found in Fields.
     * @param value value to enter
     */
    @Step("Enter text {1} into the field {0}")
    public ShareDialogBox setTextFor(String field, String value, SelenideElement element) {
        new Actions().enterText(element, value, field);
        return this;
    }
}
