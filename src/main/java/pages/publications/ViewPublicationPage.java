package pages.publications;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * ViewPublicationPage - class to work with View Publication Page
 */
public class ViewPublicationPage {
    private static final SelenideElement pageContainer = $(".detail-wrapper");

    /**
     * Information Publication Container
     * @return Selenide element of container of publication information
     */
    private static SelenideElement getInfoContainer() {
        return pageContainer.find(".detail-info");
    }

    /**
     * Publication Title
     * @return Selenide element of publication title
     */
    private SelenideElement publicationTitle() {
        return getInfoContainer().waitUntil(Condition.appears, 30000).find(".detail-wrapper__headline");
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
     * @return
     */
    @Step("Verify if View Publication Page opened")
    public ViewPublicationPage isPageOpened() {
        Assert.assertTrue(pageContainer.waitUntil(Condition.appears, 30000).isDisplayed());
        return this;
    }

    /**
     * Verify if publication was created
     * @param language - publication language
     * @param expectedPublicationName - name of publication
     */
    @Step("Verify that publication {1} was created for language {0}")
    public ViewPublicationPage isPublicationPresent(Language language, String expectedPublicationName) {
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
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
    }


}
