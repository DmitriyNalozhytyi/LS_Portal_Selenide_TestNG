package pages.publications.news;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import constants.PUBLICATION;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.ParentPage;
import pages.publications.CreatePublicationPanel;
import pages.publications.Search;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class to work with News Page
 */
public class AllNewsPage extends ParentPage {
    private static final String ALL_NEWS_PAGE_RU           = "Новости";
    private static final String ALL_NEWS_PAGE_UA           = "Новини";

    private final SelenideElement pageContainer = $("app-all-news");

    /**
     * Return the block where news are displayed
     */
    private SelenideElement getNewsContainer() {
        return pageContainer.waitUntil(Condition.appears, 30000).find(".news.reuse-wrapper.mat-card").waitUntil(Condition.appear,30000);
    }

    /**
     * Return the title of news block
     */
    private SelenideElement getPageTitle() {
        return getNewsContainer().find(".title").waitUntil(Condition.appear,30000);
    }

    /**
     * Check if the Page All News opened
     * @param language interface language
     */
    @Step("Check if News Page opens")
    public AllNewsPage isPageOpened(Language language) {
        String expectedTitle = "";
        switch (language) {
            case RU: expectedTitle = ALL_NEWS_PAGE_RU; break;
            case UA: expectedTitle = ALL_NEWS_PAGE_UA; break;
            default: expectedTitle = "";
        }
        new MainPage().switchAppToLang(language);
        Assert.assertEquals(getPageTitle().getText(),  expectedTitle, "The page title" );
        return this;
    }

    /**
     * Check if news has been created
     * @param language news language
     * @param name name of news
     */
    @Step("Verify if news {1} was created on language {0}")
    public AllNewsPage checkForNews(Language language, String name) {
        new MainPage()
                .switchAppToLang(language)
                .search(name);

        new Search()
                .isResultPresent(name);
        return this;
    }

    /**
     * Open page to create a publication
     * @param publication type of publication: NEWS, ARTICLE, ANNOUNCEMENT, INTERVIEW
     */
    @Step("Open create {0} page")
    public void openCreatePublicationPage(PUBLICATION publication) {
        new CreatePublicationPanel(pageContainer).openPageToCreate(publication);
    }

}