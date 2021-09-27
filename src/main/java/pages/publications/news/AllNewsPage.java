package pages.publications.news;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import constants.Language;
import constants.PUBLICATION;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.ParentPage;
import pages.publications.CreatePublicationPanel;
import pages.publications.Search;
import pages.publications.ViewPublicationPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class to work with News Page
 */
public class AllNewsPage extends ParentPage {

    private SelenideElement pageContainer() {
        if ($("app-all-news").isDisplayed()) {
            return $("app-all-news");
        } else if ($("app-articles").isDisplayed()) {
            return $("app-articles");
        } else if ($("app-interview").isDisplayed()) {
            return $("app-interview");
        } else return null;
    }

    /**
     * Return the block where news are displayed
     */
    private SelenideElement getNewsContainer() {
        return pageContainer().should(Condition.appear, Duration.ofSeconds(60)).find(".news.reuse-wrapper.mat-card").should(Condition.appear, Duration.ofSeconds(60));
    }

    /**
     * Return the title of news block
     */
    private SelenideElement getPageTitle() {
        return getNewsContainer().find(".title").should(Condition.appear, Duration.ofSeconds(60));
    }

    /**
     * Check if the Page All News opened
     * @param language interface language
     */
    @Step("Check if News Page opens")
    public AllNewsPage isPageOpened(Language language) {
        new MainPage().switchAppToLang(language);
        Assert.assertTrue(getPageTitle().should(Condition.appear, Duration.ofSeconds(60)).isDisplayed(), "The page title");
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
        new CreatePublicationPanel(pageContainer()).openPageToCreate(publication);
    }

    /**
     *
     * @param language
     * @param newsName
     */
    @Step("Delete {1}")
    public void deleteNews(Language language, String newsName) {
        new MainPage().switchAppToLang(language).search(newsName);

        new Search().isResultPresent(newsName).openToView();

        new ViewPublicationPage()
                .isPublicationPresent(language,newsName)
                .clickButton("Delete News", ViewPublicationPage.btnDeletePublication());

        new ConfirmDialogBox().isDialogOpen().confirm(true);
    }
}
