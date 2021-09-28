package pages.publications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import libs.Actions;
import org.testng.Assert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmPublicationDialogBox {
    private final SelenideElement container = $(".mat-card");

    private SelenideElement btnDelete() {
        return panelButtons().get(1);
    }

    private ElementsCollection panelButtons() {
        return container.find(".buttons").findAll(".mat-raised-button");
    }

    public ConfirmPublicationDialogBox isDialogOpen() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed());
        return this;
    }

    public ConfirmPublicationDialogBox clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
        return this;
    }

    public void confirm(boolean confirm) {
        if (confirm) {
            clickButton("Удалить", btnDelete());
        }
    }
}
