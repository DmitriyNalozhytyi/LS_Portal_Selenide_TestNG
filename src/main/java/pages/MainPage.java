package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.tooltip.TooltipDialogBox;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends ParentPage {

    private int answerPosition;
    
    private final SelenideElement selectLanguageElement = $(".language-select");

    public static SelenideElement getPollsContainer() {
        return $(".polls-widget__wrapper");
    }

    private SelenideElement inpSearch() {
        return $(".header-search.large");
    }
    private final ElementsCollection listOfAnswers = getPollsContainer().findAll(".body-progress-info");

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
    @Step("Search {0}")
    public void search(String text) {
        new Actions().enterText(inpSearch(), text, "Поиск");
        inpSearch().sendKeys(Keys.ENTER);
    }

    /**
     * Scroll to the block on Main Page
     * @param container Selenide element of block on page
     */
    @Step("Scroll to {0}")
    public MainPage scrollTo(SelenideElement container) {
        container.should(Condition.appear, Duration.ofSeconds(60)).scrollIntoView(true);
        return this;
    }

    /**
     * Search the poll in Polls block
     * @param name the name of poll
     */
    @Step("Search the {0}")
    public MainPage searchPoll(String name) {
        SelenideElement pollTitle = getPollsContainer().find(".body-title");
        SelenideElement btnNextPoll = getPollsContainer().find(".polls-widget__arrows").find("mat-icon").should(Condition.appear, Duration.ofMinutes(1));

        // Go to the first poll
        while(getPollsContainer().find(".polls-widget__arrows").find(".mirrorY").isDisplayed()) {
            getPollsContainer().find(".polls-widget__arrows").find(".mirrorY").click();
        }

        // Go to the poll which need processing
        while(!pollTitle.getText().equals(name)) {
            btnNextPoll.click();
            btnNextPoll = getPollsContainer().find(".polls-widget__arrows").findAll("mat-icon").get(1);
        }
        return this;
    }

    /**
     * Select an answer by position
     * @param i the position of answer in the list
     */
    @Step("Select answer {0}")
    public MainPage selectAnswerByPosition(int i) {
        this.answerPosition = i-1;
        listOfAnswers.get(answerPosition).click();
        return this;
    }

    /**
     * Send answers
     */
    @Step("Send answer")
    public MainPage sendAnswer() {
        SelenideElement btnSendAnswer = $(".mat-but.send-comment");
        new Actions().click(btnSendAnswer,"Отправить");
        return this;
    }

    /**
     * Check if selected answer is marked as passed
     */
    @Step("Check if selected answer is marked as passed")
    public MainPage checkIfAnswerSelected() {
        SelenideElement markedAnswer = listOfAnswers.get(answerPosition).find("span");
        Assert.assertTrue(markedAnswer.should(Condition.appear, Duration.ofMinutes(5)).isDisplayed());
        return this;
    }
}

