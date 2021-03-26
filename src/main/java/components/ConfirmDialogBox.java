package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmDialogBox {
    private final SelenideElement container = $(".confirm-dialog");

    private SelenideElement confirmButton() {
        return container.find(".vacancy-publish-button");
    }

    public ConfirmDialogBox isDialogOpen() {
        Assert.assertTrue(container.waitUntil(Condition.appears,60000).isDisplayed());
        return this;
    }

    public void confirm(boolean isConfirm) {
        if (isConfirm) {
            confirmButton().click();
        }
    }


}
