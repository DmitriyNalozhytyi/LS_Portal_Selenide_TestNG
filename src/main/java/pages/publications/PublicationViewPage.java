package pages.publications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

/**
 * ViewPublicationPage - class to work with View Publication Page
 */
public class PublicationViewPage {
    private static final SelenideElement pageContainer = $(".detail-wrapper");

    /**
     * Information Publication Container
     * @return Selenide element of container of publication information
     */
    private static SelenideElement getInfoContainer() {
        return pageContainer.find(".detail-info");
    }

    /**
     * Delete publication button
     * @return Selenide element of delete publication button
     */
    public static SelenideElement btnDeletePublication() {
        return panelOfPublicationActions().get(1);
    }

    /**
     * The panel of publication actions: DELETE, EDIT
     * @return Element Collection of publication actions
     */
    private static ElementsCollection panelOfPublicationActions() {
        return getInfoContainer().find(".detail-wrapper_hed-right-edite").findAll("mat-icon");
    }

    /**
     * Publication Title
     * @return Selenide element of publication title
     */
    private SelenideElement publicationTitle() {
        return getInfoContainer().should(Condition.appear, Duration.ofSeconds(30)).find(".detail-wrapper__headline");
    }

    /**
     * The Button Back to the page of all publications
     * @return Selenide element of button to return to page of all publications
     */
    public static SelenideElement btnBack() {
        return getInfoContainer().find(".detail-wrapper__back-icon");
    }

    /**
     * Verify if View Publication Page opened
     */
    @Step("Verify if View Publication Page opened")
    public PublicationViewPage isPageOpened() {
        Assert.assertTrue(pageContainer.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed());
        return this;
    }

    /**
     * Verify if publication was created
     * @param language - publication language
     * @param expectedPublicationName - name of publication
     */
    @Step("Verify that publication {1} exists for language {0}")
    public PublicationViewPage isPublicationPresent(Language language, String expectedPublicationName) {
        new MainPage().switchAppToLang(language);
        String actualPublicationName = publicationTitle().getText();
        Assert.assertEquals(actualPublicationName,expectedPublicationName, "The publication title is");
        return this;
    }

    /**
     * Click the button
     * @param name - name of button
     * @param element - path to element in DOM
     */
    @Step("Click the button {name}")
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
    }

    @Step("Delete publication")
    public void deleteNews() {
        clickButton("Delete News", btnDeletePublication());
        new ConfirmPublicationDialogBox().isDialogOpen().confirm(true);
    }

}
