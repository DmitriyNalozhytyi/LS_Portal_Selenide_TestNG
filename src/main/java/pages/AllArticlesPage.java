package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllArticlesPage extends ParentPage {
    public AllArticlesPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(css = "[for='mat-radio-3-input'] .mat-radio-label-content")
    public WebElement rbtnNewArticle;

    @FindBy(className = "create-btn")
    public WebElement btnCreate;

    public void clickOnRBtnNewArticle() {

        actions.click(rbtnNewArticle);
    }

    public void ClickOnBtnCreate() {

        actions.click(btnCreate);
    }
}
