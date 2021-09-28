package pages;

import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.tooltip.TooltipDialogBox;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends ParentPage {

    private final SelenideElement selectLanguageElement = $(".language-select");

    private final SelenideElement inpSearch() {
        return $(".header-search.large");
    }

    @FindBy(className = "_link-dashboard")
    public WebElement btnAllNews;

    @FindBy(className = "a > img")
    public WebElement btnMainLogo;

    @FindBy(css = "[href='\\/articles'] ._link-dashboard")
    public WebElement btnAllArticles;

    @FindBy(css = "[href='\\/interview-top-manager'] ._link-dashboard")
    public WebElement btnAllInterview;

    @FindBy(css = ".fa-pencil-alt.fas")
    public WebElement btnGridSliderEdit;

    @FindBy(css = "[href='\\/photogallery'] ._link-dashboard")
    public WebElement btnAllAlbums;

    public static SelenideElement portalLanguageRU() {
        return $(".language-list").findAll(".language-option").get(1);
    }

    public static SelenideElement portalLanguageUA() {
        return $(".language-list").findAll(".language-option").get(0);
    }



    public void goToAllNews(){

        actions.click(btnAllNews);
    }

    public void goToAllArticles(){

        actions.click(btnAllArticles);
    }

    public void goToAllInterview(){
        actions.click(btnAllInterview);
    }

    public void goToMainPage(){
        actions.click(btnMainLogo);
    }

    public void goToGridSliderEdit(){
        actions.click(btnGridSliderEdit);
    }

    public void goToAllAlbums(){
        actions.click(btnAllAlbums);
    }

    @Step("Open page {0}")
    public MainPage goTo(String page) {
        new TooltipDialogBox().close();
        open(page);
        return this;
    }

    @Step
    public void navigateVievListOfFeedbacksPage() {
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/feedback/list");
    }


    /**
     * Switch the language of app
     * @param language language. List of language can be found there Language
     */
    @Step("Switch the language of Portal to {0}")
    public MainPage switchAppToLang(Language language) {
        switch (language) {
            case RU: actions.click(selectLanguageElement, "Язык портала").click(portalLanguageRU(), "Русский"); break;
            case UA: actions.click(selectLanguageElement, "Язык портала").click(portalLanguageUA(), "Украинский"); break;
        }

        sleep(5000);
        return this;
    }

    /**
     * Search on the portal
     * @param text text that should be found
     */
    public void search(String text) {
        new Actions().enterText(inpSearch(), text, "Поиск");
        inpSearch().sendKeys(Keys.ENTER);
    }

    public void switchToTab(int i) {
        switchTo().window(i);
    }
}

