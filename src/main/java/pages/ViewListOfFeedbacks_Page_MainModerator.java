package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ViewListOfFeedbacks_Page_MainModerator extends ParentPage {

    @FindBy(css = ".right_box .menu-item:nth-of-type(4) a")
    private WebElement exitFromAccount;

    @FindBy(id = "otherTileText")
    private WebElement changeAccount;

   /* @FindBy(id = "tinyMceArea")
    private WebElement vvv;*/

  /*  @FindBy(css = "div[role='group']")
    private WebElement appealFieldForApprover;*/

    @FindBy(css = "[for='mat-checkbox-2-input'] .mat-checkbox-inner-container")
    private WebElement checkBoxNewFeedbackCard;

    @FindBy(className = "readonly")
    private WebElement reasonForReturnField;

    @FindBy(css = "[for='mat-checkbox-2-input'] .mat-checkbox-inner-container")
    private WebElement assignResponsibleChackBox;

    @FindBy(css = "input[role='combobox']")
    private WebElement newApproverField;


    // @FindBy(id = "mat-option-12")

    @FindBy(css = "mat-option#mat-option-0")
    private WebElement chooseApproverInPeoplePeackerField;



    @FindBy(css = "div[role='group']")
    private WebElement appealFieldBackedToMM;

    @FindBy(css = "[frameborder]")
    private WebElement frameInFeedbackCardStatusNew;

    @FindBy(css = ".dynamic-form-button.feedback-button__gray.mat-button > .mat-button-wrapper")
    private WebElement deleteFeedbackBtn;

    @FindBy(css = ".confirm-dialog__button.confirm-dialog__button_confirm.feedback-button.mat-button.mat-primary")
    private WebElement approveDeletingFeedbackBtnStatusOnApprovalOrApproved;

    @FindBy(css = ".confirm-dialog__button.confirm-dialog__button_confirm.mat-button.mat-primary")
    private WebElement approveDeletingFeedbackBtnStatusNew;

    public ViewListOfFeedbacks_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "feedback-button")
    private WebElement sendBtn;

    @FindBy(className = "popup-feedback__close")
    private WebElement closePopUpBtn;

   /* @FindBy(className = "feedback-button")
    private WebElement publishInFAQ;*/

    @FindBy(css = ".dynamic-form-button.feedback-button.mat-button")
    private WebElement publishInFAQ;

    /*@FindBy(className = "mat-button-wrapper")
    private WebElement accountBtn;*/

    @FindBy(css = ".mat-button-wrapper div")
    private WebElement accountBtn;


    @FindBy(className = "popup-feedback__button-close")
    private WebElement closePopUpAfterCreateFeedback;

    @FindBy(className = "popup-feedback_bold-text")
    private WebElement textFeedbackNumber;

    @FindBy(className = "new-feedback-button__text")
    private WebElement createNewFeedbackBtn;

    @FindBy(id = "tinymce")
    public WebElement appealField;

    private String titleText;
    public String titleText2;

    public String closePopUpFeedbackCreated_And_RememberFeedbackNumber() throws InterruptedException {
        actions.waitToBeVisible(textFeedbackNumber);
        //  Thread.sleep(2000);
        titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;
    }

    public void clickOnCreateBtn() {
        actions.waitUntilBecomeClickable(createNewFeedbackBtn);
        actions.click(createNewFeedbackBtn);
    }

    public void openLastCreatedFeedback() {
        webDriver.navigate().to("https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText);
        logger.info("https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText);
    }

    public void enterTextInTo_AppealField_FeedbackCard_status_New(String text) throws InterruptedException {

        actions.waitToBeVisible(frameInFeedbackCardStatusNew);
        //Thread.sleep(3000);

        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
           logger.info(frames.size() + " - number of frames");

            if (frames.size() > 0) {
                actions.switchTo2ndFrameOf2(appealField);
            } else {
                actions.switchTo1stFrameOf1(appealField);
            }
        } catch (Exception e) {
            logger.info("no frames");
            actions.printErrorAndStopTest(e);
            //  Assert.fail("Can`t click on element " + e);

        }
//        actions.switchTo2ndFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
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
       //проверочка - след строчка
        actions.waitToBeVisible(accountBtn);
        actions.click(accountBtn);
        actions.click(exitFromAccount);
        actions.click(changeAccount);
    }

    public void enterTextInTo_AppealField_FeedbackCard_status_New_Apprower(String text) {
        actions.switchTo2ndFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        logger.info(appealField+ "visible");
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

    public boolean checkFeedbackIsCreated() {
        // open last Created Feedback
        webDriver.navigate().to("https://metinvest-intranet-tests.azurewebsites.net/feedback/list/" + titleText);
     logger.info("https://metinvest-intranet-tests.azurewebsites.net/feedback/list/" + titleText);

        if (
                webDriver.findElement(By.className("feedback-button")).isDisplayed()) {

            logger.info("TRUE. button send is displayed. Feedback is Created");
            return true;

        } else {
           logger.info("FALSE. button send is NOT displayed. Feedback is NOT Created");
            return false;
        }

    }

    public void chooseCheckBoxToBackMM() {
       /* Select checkBox = new Select(webDriver.findElement(By.className("mat-checkbox-frame")));
        checkBox.selectByIndex(1);*/
        actions.click(checkBoxNewFeedbackCard);
    }

    public void inputReasonForReturn() {
        actions.insertText(reasonForReturnField, "UUUYUYUYUY");
    }

    public void assignNewResponsible() {
        actions.click(assignResponsibleChackBox);
    }

    public void chooseNewApprover(String text) throws InterruptedException {

        //  actions.insertTextInToPeopePickerFieldUsingEnter(newApproverField,text);
        actions.insertText(newApproverField, text);
        // actions.waitUntilBecomeClickable(chooseApproverInPeoplePeackerField);
        actions.click(chooseApproverInPeoplePeackerField);
        // actions.waitUntilBecomeClickable(sendBtn);
        Thread.sleep(2000);
       /* actions.insertText(newApproverField, text);
        webDriver.findElement(By.cssSelector("input[role='combobox']")).sendKeys(Keys.ENTER);*/

    }

    public void enterTextInTo_AppealField_FeedbackCard_status_New_BackFromAp_ByMM(String text) throws InterruptedException {

        Thread.sleep(3000);
      //  actions.waitUntilBecomeClickable(sendBtn);
       // actions.waitToBeVisible(appealField);
        actions.switchTo2ndFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        logger.info(appealField + "visible");
        actions.insertText(appealField, text);
        actions.switchToDefaultContentFromFrame();

      /*  Thread.sleep(2000);
    //    actions.waitToBeVisible(sendBtn);

        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
            System.out.println(frames.size() + " - number of frames");

            if (frames.size() > 0) {
                actions.switchTo2ndFrameOf2(appealFieldBackedToMM);
            } else {
                actions.switchTo1stFrameOf2(appealFieldBackedToMM);
            }
        } catch (Exception e) {
            System.out.println("no frames");
            actions.printErrorAndStopTest(e);
            //  Assert.fail("Can`t click on element " + e);
*/
        }

    public void deleteFeedbackStatusOnApprovalOrApproved() throws InterruptedException {
        actions.waitUntilBecomeClickable(deleteFeedbackBtn);
        actions.click(deleteFeedbackBtn);
        actions.waitUntilBecomeClickable(approveDeletingFeedbackBtnStatusOnApprovalOrApproved);
        actions.click(approveDeletingFeedbackBtnStatusOnApprovalOrApproved);
        Thread.sleep(2000);
    }

    public boolean isFeedbackDeleted() {
        try {
            webDriver.findElement(By.cssSelector(".dynamic-form-button.feedback-button__gray.mat-button")).isDisplayed();
            logger.info("FALSE");
            return false;
        }catch (Exception e){
            logger.info("TRUE");
            return true;
        }
    }

    public void deleteFeedbackStatusNew() throws InterruptedException {
        actions.waitUntilBecomeClickable(deleteFeedbackBtn);
        actions.click(deleteFeedbackBtn);
        actions.waitUntilBecomeClickable(approveDeletingFeedbackBtnStatusNew);
        actions.click(approveDeletingFeedbackBtnStatusNew);
        Thread.sleep(2000);
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


