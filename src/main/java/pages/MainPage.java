package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends ParentPage {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
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

    @Step
    public void navigateToCreateNewFeedbackPage() {
        webDriver.navigate().to("https://metinvest-intranet-test.azurewebsites.net/ru/feedback");
    }

    @Step
    public void navigateVievListOfFeedbacksPage() {
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/feedback/list");
    }
}

