package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MessageDialogBox {
    private final SelenideElement container = $(".confirm-dialog");

    private SelenideElement messageElement() {
        return container.find(".confirm-dialog__title").waitUntil(Condition.appear,25000);
    }

    private SelenideElement closeElement() {
        return container.find(".confirm-dialog__close");
    }

    public String getMessage() {
        return messageElement().getText();
    }


    public void close() {
        closeElement().click();
        closeElement().waitUntil(Condition.disappears,60000);
    }
}
