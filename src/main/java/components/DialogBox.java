package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DialogBox {
    private final SelenideElement container = $(".info-card");

    private SelenideElement closeButton() {
        return container.find(".closeImg.mat-button.info-card__button-close");
    }

    public String getTitle() {
        return container.find(".info-card__title").getText();
    }

    public void close() {
        closeButton().click();
        container.should(Condition.disappear, Duration.ofSeconds(10));
    }

    public void waitForClose() {
        container.should(Condition.disappear, Duration.ofSeconds(10));
    }
}
