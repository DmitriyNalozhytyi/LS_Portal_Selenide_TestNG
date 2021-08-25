package pages.publications;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class Search {
    private final SelenideElement pageContainer = $(".search-wrapper");

    private SelenideElement getMainSearchResult() {
        return pageContainer.find(".search-result");
    }

    private SelenideElement getSearchResultTitle() {
        return getMainSearchResult().find(".search-result__title");
    }

    public void isResultPresent(String expectedText) {
        String actualText = getSearchResultTitle().getText();
        Assert.assertEquals(actualText, expectedText, "The search result");
    }


}
