package components;

import com.codeborne.selenide.ElementsCollection;
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
    private ElementsCollection getAllPages() {
        return $("._paginatorBlock").findAll("li");
    }

    public void next() {
        getNextButton().click();
        new PagePreLoader().waitToLoad();
    }

    public int countOfPages() {
        return getAllPages().size()-1;
    }

    public void gotToPage(int i) {
        getAllPages().get(i).click();
    }
}
