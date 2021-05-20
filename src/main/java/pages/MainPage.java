package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class MainPage extends ParentPage {

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

    @Step("Open create new feedback page")
    public MainPage navigateToCreateNewFeedbackPage() {
      open("https://metinvest-intranet-test.azurewebsites.net/ru/feedback");
      return this;
    }

    @Step
    public void navigateVievListOfFeedbacksPage() {
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/feedback/list");
    }

    /**
     * Open recruiter page
     */
    @Step("Open recruiters page")
    public void goToRecruiterPage() {
        open("https://metinvest-intranet-test.azurewebsites.net/ru/admin/vacancy/recruiters");
    }

    /**
     * Open vacancy management page "admin/vacancy"
     */
    @Step("Open Vacancy management page")
    public void goToVacancyManagementPage() {
        open("https://metinvest-intranet-test.azurewebsites.net/ru/admin/vacancy");
    }

    /**
     * Open vacancy page "/vacancy"
     */
    @Step("Open vacancy page")
    public void goToVacancyPage() {
        open("https://metinvest-intranet-test.azurewebsites.net/ru/vacancy");
    }
}

