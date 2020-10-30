package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ViewListOfFeedbacks_Page_MainModerator extends ParentPage {

    @FindBy(css = ".log_out a")
    private WebElement exitFromAccount;

    @FindBy(id = "otherTileText")
    private WebElement changeAccount;

    @FindBy(id = "tinyMceArea")
    private WebElement vvv;

    @FindBy(css = "div[role='group']")
    private WebElement appealFieldForApprover;

    public ViewListOfFeedbacks_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(className = "feedback-button")
    private WebElement sendBtn;

    @FindBy(className = "popup-feedback__close")
    private WebElement closePopUpBtn;

    @FindBy(className = "feedback-button")
    private WebElement publishInFAQ;

    /*@FindBy(className = "mat-button-wrapper")
    private WebElement accountBtn;*/

    @FindBy(css = ".mat-button-wrapper div")
    private WebElement accountBtn;


    @FindBy(className = "popup-feedback__button-close")
    private WebElement closePopUpAfterCreateFeedback;

    @FindBy (className = "popup-feedback_bold-text")
    private WebElement textFeedbackNumber;

    @FindBy (className = "new-feedback-button__text")
    private WebElement createNewFeedbackBtn;

    @FindBy(id = "tinymce")
    public WebElement appealField;

  private String titleText;
    public String titleText2;

    public String closePopUpFeedbackCreated_And_RememberFeedbackNumber() throws InterruptedException {
        actions.waitUntilBecomeVisible(textFeedbackNumber);
        //  Thread.sleep(2000);
        titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        System.out.println("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;
    }

    public void clickOnCreateBtn() {
       actions.waitUntilBecomeClickable(createNewFeedbackBtn);
        actions.click(createNewFeedbackBtn);
    }

    public void openLastCreatedFeedback() {
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/feedback/list/"+titleText);
        System.out.println("https://metinvest-intranet-tests.azurewebsites.net/feedback/list/"+titleText);
    }

    public void enterTextInTo_AppealField_FeedbackCard_status_New(String text) {

        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
            System.out.println(frames.size() + " - number of frames");

           if (frames.size() > 0) {
               actions.switchTo2ndFrameOf2(appealField);
           }else {
               actions.switchTo1stFrameOf1(appealField);
           }
        }catch (Exception e){
            System.out.println("no frames");
            actions.printErrorAndStopTest(e);
          //  Assert.fail("Can`t click on element " + e);

        }
//        actions.switchTo2ndFrameOf2(appealField);
        actions.waitUntilBecomeVisible(appealField);
        actions.insertText(appealField, text);
        actions.switchToDefaultContentFromFrame();
    }

    public void clickOnSendBtn() {
        actions.click(sendBtn);
    }

    public void closePopUp() throws InterruptedException {
      Thread.sleep(2000);
        actions.clickOnLastElementCloseBtn();
    }

    public void publishInFAQ() {
        actions.click(publishInFAQ);
    }

    public void exitFromAccount() throws InterruptedException {
        actions.click(accountBtn);
        actions.click(exitFromAccount);
        actions.click(changeAccount);
    }

    public void enterTextInTo_AppealField_FeedbackCard_status_New_Apprower(String text) {
        actions.switchTo2ndFrameOf2(appealField);
        actions.waitUntilBecomeVisible(appealField);
        System.out.println("visible");
        actions.insertText(appealField, text);
        actions.switchToDefaultContentFromFrame();

    }

  /*     public String enterTextInTo_AppealField_FeedbackCard_status_New_Apprower() {
               actions.switchTo2ndFrameOf2(appealField);
               actions.waitUntilBecomeVisible(appealField);
               System.out.println("visible");
               String titleText2 = "*****" + actions.currentTime();
               actions.insertText(appealField,titleText2);
               actions.switchToDefaultContentFromFrame();
               System.out.println("write a value to a variable titleText2 = " + titleText2 );

        return this.titleText2;

    }*/



   /* public String closePopUpFeedbackCreated_And_RememberFeedbackNumber() throws InterruptedException {
        actions.waitUntilBecomeVisible(textFeedbackNumber);
        //  Thread.sleep(2000);
        titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        System.out.println("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;
    }*/

    public void closeFeedbackCard() throws InterruptedException {
        actions.clickOnLastElementCloseBtn();
    }

//    find last created feedback in list of reedbacks

   /* public void openLastCreatedFeedback1() throws InterruptedException {
// нижняя строка отрабатівает. остальные пока нет
//webDriver.findElement(By.xpath("//*[contains(text(), ' 1422 ')]")).click();
        webDriver.findElement(By.xpath("//*[contains(text(), "+titleText+")]")).click();

        System.out.println("IIIIIIIII" + titleText);
//webDriver.findElement(By.xpath("//*[contains(text(), '{0}')]".format(titleText))).click();
        Thread.sleep(5000);
    //    webDriver.findElement(By.xpath("//*[text()= '{0}')]".format(titleText))).click();
//        webDriver.findElement(By.xpath("//*[contains(text()='"+titleText.trim()+"')]")).click();
//        webDriver.findElement(By.xpath("//*[contains(text(),titleText)]")).click();
 //       webDriver.findElement(By.xpath(String.format("//*[contains(text()='{0}')]", titleText))).click();
 //       webDriver.findElement(By.xpath(String.format("//*[contains(text()=''%s']",titleText))).click();
//        webDriver.findElement(By.xpath(String.format(titleText))).click();
        Thread.sleep(20000);
        webDriver.findElement(By.xpath("//*[contains(text(),'"+titleText.trim()+"')]")).click();
    }*/
}

