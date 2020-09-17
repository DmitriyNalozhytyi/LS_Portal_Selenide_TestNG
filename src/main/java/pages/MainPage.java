package pages;

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
}