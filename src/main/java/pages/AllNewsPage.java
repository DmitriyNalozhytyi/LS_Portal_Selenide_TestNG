package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AllNewsPage extends ParentPage {
    public AllNewsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "mat-radio-label-content")
    private WebElement rbtnNewNews;

    @FindBy(className = "create-btn")
    private WebElement btnCreate;

    public void clickOnRBtnNewNews() {
        actions.click(rbtnNewNews);
    }

    public void ClickOnBtnCreate() {
        actions.click(btnCreate);
    }

    public void choosePublicationTypeNews(){
        //webDriver.findElements(By.cssSelector("mat-radio-label-content")).;
        List<WebElement> rbtns = webDriver.findElements(By.cssSelector("#mat-radio-label-content"));
        rbtns.get(rbtns.size()-3).click();

    }
    public void enterTextInToFieldClerk() {
        //  actionsWithOurElements.deleteClerk();
        try {
            List<WebElement> closeButtons = webDriver.findElements(By.className("mat-radio-label-content"));
            System.out.println(closeButtons.size() + " - number of same elements");
            if (closeButtons.size() > 0) {
                closeButtons.get(closeButtons.size() - 4).click();
                System.out.println("CloseButton was clicked");
            } else {
                System.out.println("!!! number of same elements '0'!!! ");
            }
        } catch (Exception e) {
            System.out.println("Can't enter text in to fieldExecutor" + e);
            // printErrorAndStopTest(e);
        }

    }
}
