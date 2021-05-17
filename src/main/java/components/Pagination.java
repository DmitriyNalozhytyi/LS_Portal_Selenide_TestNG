package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Pagination {
    private final SelenideElement container = $(".mat-paginator-range-actions");

    public boolean isNexButtonActive() {
        return getNextButton().isEnabled();
    }

    private SelenideElement getNextButton() {
        return container.find(".mat-paginator-navigation-next");
    }

    public void next() {
        getNextButton().click();
        new PagePreLoader().waitToLoad();
    }
}
