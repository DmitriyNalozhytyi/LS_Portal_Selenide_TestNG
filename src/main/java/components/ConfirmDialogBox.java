package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmDialogBox {
    private final SelenideElement container = $(".confirm-dialog");

    private SelenideElement confirmButton() {
        if (container.find(".vacancy-publish-button").exists()) {
            return container.find(".vacancy-publish-button");
        } else if (container.find(".confirm-dialog__button_confirm").exists()) {
            return container.find(".confirm-dialog__button_confirm");
        } else {
            return null;
        }
    }

    public ConfirmDialogBox isDialogOpen() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed());
        return this;
    }

    public void confirm(boolean isConfirm) {
        if (isConfirm) {
            confirmButton().click();
            container.should(Condition.disappear,Duration.ofSeconds(25));
        }
    }


}
