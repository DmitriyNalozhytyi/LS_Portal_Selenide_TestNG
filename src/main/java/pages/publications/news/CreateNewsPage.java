package pages.publications.news;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.AddPublicationImage;
import components.DateTimePicker;
import components.PagePreLoader;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.ParentPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class to work the page to create a news
 */
public class CreateNewsPage extends ParentPage {
    private static final SelenideElement pageContainer = $("._b");

    private SelenideElement getTitleElement() {
        return pageContainer.find(".title");
    }

    private SelenideElement tbRU() {
        return getAllLanguageTab().get(1);
    }

    private SelenideElement tbUA() {
        return getAllLanguageTab().get(0);
    }

    private ElementsCollection getAllLanguageTab() {
        return getTabContainer().findAll(".mat-tab-label");
    }

    private SelenideElement getTabContainer() {
        return getGeneralInformationSection().find(".mat-tab-labels");
    }

    private SelenideElement getContentTypeContainer() {
        return pageContainer.find(".radio-field-wrapper");
    }

    private ElementsCollection getAllContentTypeButtons() {
        return getContentTypeContainer().findAll(".radio-field-block");
    }

    private static ElementsCollection getAllDropDowns() {
        return pageContainer.findAll(".mat-select-value");
    }

    private static ElementsCollection getAllDateFields() {
        return pageContainer.findAll(".main-input.vacancy-input.ng-pristine");
    }

    /**    FIELDS     */
    private SelenideElement btnContent(int i) {
        return getAllContentTypeButtons().get(i);
    }
    public static SelenideElement ddTargetAudience() {
        return getAllDropDowns().get(0);
    }

    public static SelenideElement ddHeading() {
        return getAllDropDowns().get(1);
    }

    public static SelenideElement ddSubheading() {
        return getAllDropDowns().get(2);
    }

    private SelenideElement inpTag() {
        return pageContainer.find(".tags-input");
    }

    private static SelenideElement getGeneralInformationSection() {
        return pageContainer.find(".mat-tab-group");
    }

    private static SelenideElement getImageBlock() {
        return pageContainer.find(".image-box");
    }

    public static ElementsCollection getAllHeaderPublicationInputs() {
        return getGeneralInformationSection().findAll(".main-input.vacancy-input");
    }

    public static SelenideElement inpTitleField() {
        return getAllHeaderPublicationInputs().get(0);
    }

    public static SelenideElement inpLeadingParagraph() {
        return getAllHeaderPublicationInputs().get(1);
    }

    public static SelenideElement inpDescriptionField() {
        return getGeneralInformationSection().find(".mce-tinymce.mce-container.mce-panel").find("iframe");
    }

    public static SelenideElement btnAddImage() {
        return getImageBlock().find(".file-upload");
    }
    public static SelenideElement btnSaveAndPublish() {
        return pageContainer.find(".vacancy-publish-button");
    }

    public static SelenideElement dtStartPublication() {
        return getAllDateFields().get(1);
    }

    public static SelenideElement dtStartInterview() {
        return getAllDateFields().get(2);
    }

    private boolean isTitleDisplay() {
        return getTitleElement().isDisplayed();
    }

    /**
     * Verify if page to create news opened
     */
    @Step("Verify if page to create news opened")
    public CreateNewsPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertTrue(isTitleDisplay(),"The news page");
        return this;
    }

    /**
     * Enter text in the input field
     * @param fieldName the name of field that should be displayed in allure report
     * @param element selector to find input field
     * @param text text that should be entered in the input field
     */
    @Step("Enter the text '{2}' into the field '{0}'")
    public CreateNewsPage setTextFor(String fieldName, SelenideElement element, String text) {
        new Actions().enterText(element, text, fieldName);
        return this;
    }

    /**
     *
     * @param name name of button
     * @param element Selenide element. The list of buttons there Button.
     */
    @Step("Click the button {0}")
    public void clickButton(SelenideElement element, String name) {
        new Actions().click(element, name);
    }

    /**
     * Switch tabs on Vacancy Management page
     * @param language tab name like "RU", "UA"
     */
    @Step("Switch to tab {0}")
    public CreateNewsPage switchTo(Language language) {
        switch (language) {
            case RU: clickButton(tbRU(), "RU"); break;
            case UA: clickButton(tbUA(), "UA"); break;
        }
        return this;
    }

    /**
     * Enter the text into Tiny MCE editor
     * @param field field name
     * @param text the text that should be inserted
     * @param element selector of the element. For Tiny MCE editor use Fields.
     */
    @Step("Enter the text {1} into {0}")
    public CreateNewsPage setTinyMCEText(String field, String text, SelenideElement element) {
        new Actions().enterTextInTinyMCE(element, text, field);
        return this;
    }

    /**
     * Add image to slider
     */
    @Step("Add image to slider")
    public CreateNewsPage addImageToSlider(){
        clickButton(btnAddImage(),"Добавить илюстрацию");
        new AddPublicationImage().upload("1.jpg");
        return this;
    }

    /**
     * Select a value from dropdown element
     */
    @Step("Select {2} for {0}")
    public CreateNewsPage selectValueFor(String name, SelenideElement element, int i){
        new Actions().dropdown(name, element, i);
        return this;
    }

    /**
     * Select Content Type
     * @param type content type <br>CONTENT_TYPE.PHOTO <br>CONTENT_TYPE.MOVIE <br>CONTENT_TYPE.INFOGRAPHICS
     */
    @Step("Select content type {0}")
    public CreateNewsPage selectContentType(int type){
        btnContent(type).click();
        return this;
    }

    /**
     * Enter list of tags
     * @param text tag name like "#tag1", "#tag2", "#tag3", "#tag4", "#tag5"
     */
    @Step("Enter tags {0}")
    public CreateNewsPage enterTag(String... text) {
        for (String s : text) {
            inpTag().sendKeys(s);
            inpTag().sendKeys(Keys.ENTER);
        }
        return this;
    }

    /**
     * Set data and time
     * @param fieldName field name
     * @param element element of
     * @param dateTime date and time in format YYYY.MM.dd HH:mm
     */
    @Step("Set date {2} for {0}")
    public CreateNewsPage setDateTimeFor(String fieldName, SelenideElement element, String dateTime) {
        clickButton(element, fieldName);
        new DateTimePicker().setDate(dateTime);
        return this;
    }
}
