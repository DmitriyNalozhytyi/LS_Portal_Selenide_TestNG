package pages.publications;

import com.codeborne.selenide.SelenideElement;
import components.PagePreLoader;
import org.testng.Assert;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class Search {
    private final SelenideElement pageContainer = $(".search-wrapper");

    private SelenideElement getMainSearchResult() {
        return pageContainer.find(".search-result");
    }

    private SelenideElement getSearchResultTitle() {
        return getMainSearchResult().find(".search-result__title");
    }

    public Search isPublicationExists(String expectedText) {
        new PagePreLoader().waitToLoad();
        String actualText = getSearchResultTitle().getText();
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(actualText, expectedText, "The search result");
        return this;
    }

    public void openToView() {
        getSearchResultTitle().click();
        new MainPage().switchToTab(1);
    }
}
