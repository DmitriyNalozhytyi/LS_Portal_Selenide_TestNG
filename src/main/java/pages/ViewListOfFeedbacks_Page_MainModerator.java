package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewListOfFeedbacks_Page_MainModerator extends ParentPage {
    public ViewListOfFeedbacks_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }


    @FindBy(className = "popup-feedback__button-close")
    private WebElement closePopUpAfterCreateFeedback;

    @FindBy (className = "popup-feedback_bold-text")
    private WebElement textFeedbackNumber;


/*
    public void closePopUpFeedbackCreated() throws InterruptedException {
        actions.waitUntilBecomeVisible(textFeedbackNumber);
      //  Thread.sleep(2000);
        String titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        System.out.println("text recoded" + titleText);

        actions.click(closePopUpAfterCreateFeedback);
    }
*/


   private String titleText;


    public String closePopUpFeedbackCreated() throws InterruptedException {
        actions.waitToBeVisible(textFeedbackNumber);
        //  Thread.sleep(2000);
        titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        System.out.println("text recoded" + titleText);

        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;

    }









}
