package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlesPageAll extends ParentPage {
    @FindBy(css = "[for='mat-radio-3-input'] .mat-radio-label-content")
    public WebElement rbtnNewArticle;

    @FindBy(className = "create-btn")
    public WebElement btnCreate;

    public void clickOnRBtnNewArticle() {

        actions.click(rbtnNewArticle);
    }

    public void clickOnBtnCreate() {

        actions.click(btnCreate);
    }

    public void selectRandomArticle(){
        //div:nth-of-type(1) > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link
        webDriver.findElement(By.cssSelector("div:nth-of-type(" + actions.randomNumber(1, 6) + ") > div > mat-card:nth-of-type(" + actions.randomNumber(1, 3) + ") > app-item .item-block__header.news_top_cont_hed.ng-star-inserted > .default_link")).click();
        //webDriver.findElement(By.cssSelector("div:nth-of-type(" + actions.randomNumber(1, 15) + ") > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link")).click();
    }
}
