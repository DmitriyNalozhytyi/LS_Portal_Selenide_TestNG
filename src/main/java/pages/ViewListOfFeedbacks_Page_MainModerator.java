package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.CustomRandom;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertTrue;


public class ViewListOfFeedbacks_Page_MainModerator extends ParentPage {

  /*  @FindBy(css = ".right_box .menu-item:nth-of-type(4) a")
    private WebElement exitFromAccount;*/

    private final SelenideElement exitFromAccount = $(".left-icon-item_exit");

    /*@FindBy(id = "otherTileText")
    private WebElement changeAccount;*/

    private final SelenideElement changeAccount = $("#otherTileText");

   /* @FindBy(id = "tinyMceArea")
    private WebElement vvv;*/

  /*  @FindBy(css = "div[role='group']")
    private WebElement appealFieldForApprover;*/

    @FindBy(css = "[for='mat-checkbox-2-input'] .mat-checkbox-inner-container")
    private WebElement checkBoxNewFeedbackCardOld;

    private final SelenideElement checkBoxNewFeedbackCard = $("[for='mat-checkbox-2-input'] .mat-checkbox-inner-container");


    @FindBy(className = "readonly")
    private WebElement reasonForReturnFieldOld;

    private final SelenideElement reasonForReturnField = $(".readonly");

    /*@FindBy(css = "[for='mat-checkbox-2-input'] .mat-checkbox-inner-container")
    private WebElement assignResponsibleCheckBox;*/

    private final SelenideElement assignResponsibleCheckBox = $("[for='mat-checkbox-2-input'] .mat-checkbox-inner-container");

   /* @FindBy(css = "input[role='combobox']")
    private WebElement newApproverField;*/
    private final SelenideElement newApproverField = $("input[role='combobox']");

    // @FindBy(id = "mat-option-12")

   /* @FindBy(css = "mat-option#mat-option-0")
    private WebElement chooseApproverInPeoplePeackerField;*/

  //  private final SelenideElement chooseApproverInPeoplePeackerField = $("mat-option#mat-option-0");
    private final SelenideElement chooseApproverInPeoplePeackerField = $(".modern-option__title");



    @FindBy(css = "div[role='group']")
    private WebElement appealFieldBackedToMM;

  /*  @FindBy(css = "[frameborder]")
    private WebElement frameInFeedbackCardStatusNew;*/

    private final SelenideElement frameInFeedbackCardStatusNew = $("[frameborder]");

    private final SelenideElement frameInFeedbackCardStatusNew2 = $("#_tinymce-i8vhbvlmoxp_ifr");


    @FindBy(css = ".dynamic-form-button.feedback-button__gray.mat-button > .mat-button-wrapper")
    private WebElement deleteFeedbackBtn;

    @FindBy(css = ".confirm-dialog__button.confirm-dialog__button_confirm.feedback-button.mat-button.mat-primary")
    private WebElement approveDeletingFeedbackBtnStatusOnApprovalOrApproved;

    @FindBy(css = ".confirm-dialog__button.confirm-dialog__button_confirm.mat-button.mat-primary")
    private WebElement approveDeletingFeedbackBtnStatusNew;

    /*@FindBy(className = "feedback-button")
    private WebElement sendBtn;*/

    private final SelenideElement sendBtn = $(".feedback-button");
    private final SelenideElement numberOfCreatedFeedback = $(".popup-feedback_bold-text");

    @FindBy(className = "popup-feedback__close")
    private WebElement closePopUpBtn;

   /* @FindBy(className = "feedback-button")
    private WebElement publishInFAQ;*/

    /*@FindBy(css = ".dynamic-form-button.feedback-button.mat-button")
    private WebElement publishInFAQ;*/

    private final SelenideElement publishInFAQ = $(".dynamic-form-button.feedback-button.mat-button");


    /*@FindBy(className = "mat-button-wrapper")
    private WebElement accountBtn;*/

   /* @FindBy(css = ".mat-button-wrapper div")
    private WebElement accountBtn;*/

    private final SelenideElement accountBtn = $(".mat-button-wrapper div");


   /* @FindBy(className = "popup-feedback__button-close")
    private WebElement closePopUpAfterCreateFeedback;*/

    private final SelenideElement closePopUpAfterCreateFeedback = $(".popup-feedback__button-close");


    @FindBy(className = "popup-feedback_bold-text")
    private WebElement textFeedbackNumber;

    @FindBy(className = "new-feedback-button__text")
    private WebElement createNewFeedbackBtn;

   /* @FindBy(id = "tinymce")
    public WebElement appealField;*/

    private final SelenideElement appealField = $("#tinymce");
    private final SelenideElement appealFieldForModerator = $("body#tinymce");
 //   List<SelenideElement> lastCloseBtn = $$(".popup-feedback__close");
    ElementsCollection  lastCloseBtn = $$(".popup-feedback__close");


    private final SelenideElement deleteFeedbackBtnAlternativeChannels = $(".dynamic-form-button.feedback-button__gray.mat-button");


    public static String titleText;
    public static String answerText;



 /*   public String closePopUpFeedbackCreated_And_RememberFeedbackNumber() throws InterruptedException {
        actions.waitToBeVisible(textFeedbackNumber);
        //  Thread.sleep(2000);
        titleText = webDriver.findElement(By.className("popup-feedback_bold-text")).getText();
        logger.info("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;
    }*/

   /* public String  closePopUpFeedbackCreated_And_RememberFeedbackNumber()  {
    //public  ViewListOfFeedbacks_Page_MainModerator closePopUpFeedbackCreated_And_RememberFeedbackNumber()  {
        titleText= $(".popup-feedback_bold-text").getText();
        logger.info("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback);
        return this.titleText;
       // return this;
    }*/

   @Step("close FeedbackCreated PopUp And Remember FeedbackNumber")
    public ViewListOfFeedbacks_Page_MainModerator  closePopUpFeedbackCreated_And_RememberFeedbackNumber()  {

       $(".popup-feedback_bold-text").waitUntil(Condition.appear,15000);
       titleText= $(".popup-feedback_bold-text").getText();
        logger.info("text recoded" + titleText);
        actions.click(closePopUpAfterCreateFeedback,"closePopUpAfterCreateFeedback");
         this.titleText = titleText;
         return this;
    }

    public void clickOnCreateBtn() {
        actions.waitUntilBecomeClickable(createNewFeedbackBtn);
        actions.click(createNewFeedbackBtn);
    }

    /*JUnit
    public void openLastCreatedFeedback() {
        webDriver.navigate().to("https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText);
        logger.info("https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText);
    }*/
    public ViewListOfFeedbacks_Page_MainModerator openLastCreatedFeedback()  {
        open("https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText);
        logger.info("site" + "https://metinvest-intranet-test.azurewebsites.net/ru/feedback/list/" + titleText +" are opened");
        return this;
    }



    @Step
    public ViewListOfFeedbacks_Page_MainModerator enterTextInTo_AppealField_FeedbackCard_status_New (String text) {

        Selenide.switchTo().frame(0);
        actions
                .enterText(appealField, text + actions.currentTime(), "Feedback text");
        Selenide.switchTo().defaultContent();

      //  ElementsCollection  countOfFrames = $$(".popup-feedback__close");

        return this;
    }

    @Step
    public ViewListOfFeedbacks_Page_MainModerator enterTextInTo_AppealField_FeedbackCard_status_New_MM () {

        Selenide.switchTo().frame(0);
        logger.info("switchedToFrame[0]");
        actions
                .waitUntilAppear_15000(appealField);//test
               // .enterText(appealField, text + actions.currentTime(), "Feedback text");
        String answerText = CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE, 15);//test
        actions.enterText(appealField,answerText,"AppealField");//test
        Selenide.switchTo().defaultContent();
        this.answerText = answerText;//test

        return this;
    }

    @Step
    public ViewListOfFeedbacks_Page_MainModerator enterTextInTo_AppealField_FeedbackCard_status_New_M (){

        /*actions.enterTextInTinyMCE($(".mce-tinymce.mce-container.mce-panel").find("iframe"),"ddsds","sdf");*/
        ElementsCollection  frames = $$(By.tagName("iframe"));
        logger.info(frames.size() + " - number of frames");
        Selenide.switchTo().frame(1);
        logger.info("switchedToFrame[1]");
        actions
                .waitUntilAppear_15000(appealField);//test
        String answerText = CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE, 15);//test
        actions.enterText(appealField,answerText,"AppealField");//test
        Selenide.switchTo().defaultContent();
        this.answerText = answerText;//test

        return this;
    }


    public ViewListOfFeedbacks_Page_MainModerator clickOnSendBtn() {
        actions
                .waitUntilAppear_15000(sendBtn)//test
                .click(sendBtn, "Send");
        return this;
    }

    public ViewListOfFeedbacks_Page_MainModerator closePopUp()  {
        sleep(5000);
        actions.clickOnLastElementOfList(lastCloseBtn, "Close last popUp");
        return this;


       /* public void clickOnLastElementCloseBtn()  {

            List<SelenideElement> CommunicationChannelDDList = $$(".popup-feedback__close");
            logger.info(CommunicationChannelDDList.size() + " - number of closeBtn");

            try {
                CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 1).click();
                logger.info("last close btn clicked");
            }catch (Exception e){
                logger.info("can not find " + CommunicationChannelDDList);
            }*/



    }

    public ViewListOfFeedbacks_Page_MainModerator publishInFAQ() {
        actions
                .waitUntilAppear_15000(publishInFAQ)//test
                .click(publishInFAQ, "publishInFAQ");
        logger.info(publishInFAQ+ "clicked");
        return this;
    }



    public ViewListOfFeedbacks_Page_MainModerator exitFromAccount() {
        actions.click(accountBtn,"accountBtn");
        actions.click(exitFromAccount,"exitFromAccount");
        actions.click(changeAccount,"changeAccount");
        return this;
    }

    /*public void enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower_Old(String text) {
        actions.switchTo2ndFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        logger.info(appealField+ "visible");
        actions.enterText(appealField, text);
        actions.switchToDefaultContentFromFrame();

    }*/

    public ViewListOfFeedbacks_Page_MainModerator enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower_Old(String text) {

        Selenide.switchTo().frame(1);
        actions.enterText(appealField, text + actions.currentTime(), "AppealField");
        Selenide.switchTo().defaultContent();
        return this;

    }

    public ViewListOfFeedbacks_Page_MainModerator enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower(){
        Selenide.switchTo().frame(1);
        actions.waitUntilAppear_15000(appealField);
     //   appealField.waitUntil(Condition.appear,15000);
        String answerText = CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE, 15);
        actions.enterText(appealField,answerText,"AppealField");
        Selenide.switchTo().defaultContent();
        this.answerText = answerText;
        return this;
    }





  /*     public String enterTextInTo_ResponceTextField_FeedbackCard_status_New_Apprower_Old() {
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

    public ViewListOfFeedbacks_Page_MainModerator closeFeedbackCard() {

        sleep(2000);
        actions.clickOnLastElementCloseBtn();
        return this;
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

    public void chooseCheckBoxToBackMMOld() {
       /* Select checkBox = new Select(webDriver.findElement(By.className("mat-checkbox-frame")));
        checkBox.selectByIndex(1);*/
        actions.click(checkBoxNewFeedbackCardOld);
    }

    @Step
    public ViewListOfFeedbacks_Page_MainModerator chooseCheckBoxToBackMM() {
        actions
                .waitUntilAppear_15000(checkBoxNewFeedbackCard)
                .click(checkBoxNewFeedbackCard);
        return this;
    }

    public void inputReasonForReturnOld() {
        actions.enterText(reasonForReturnFieldOld, "UUUYUYUYUY");
    }

    public ViewListOfFeedbacks_Page_MainModerator inputReasonForReturn() {

        actions
                .waitUntilAppear_15000(reasonForReturnField)
                .enterText(reasonForReturnField, actions.currentTime() + "Reason fo return", "Reason fo return");
        return this;
    }

    public void assignNewResponsible2() {
        actions.click(assignResponsibleCheckBox);
    }

    public ViewListOfFeedbacks_Page_MainModerator assignNewResponsible() {
        actions
                .waitUntilAppear_15000(assignResponsibleCheckBox)
                .click(assignResponsibleCheckBox,"assignResponsibleCheckBox");
        return this;
    }

    public void chooseNewApprover2(String text) throws InterruptedException {

        //  actions.insertTextInToPeopePickerFieldUsingEnter(newApproverField,text);
        actions.enterText(newApproverField, text);
        // actions.waitUntilBecomeClickable(chooseApproverInPeoplePeackerField);
        actions.click(chooseApproverInPeoplePeackerField);
        // actions.waitUntilBecomeClickable(sendBtn);
        Thread.sleep(2000);
       /* actions.insertText(newApproverField, text);
        webDriver.findElement(By.cssSelector("input[role='combobox']")).sendKeys(Keys.ENTER);*/

    }

    public ViewListOfFeedbacks_Page_MainModerator chooseNewApprover(String text){
       /* actions
                .waitUntilAppear_15000(newApproverField)
                .enterText(newApproverField,text,"chooseNewApprover");
      //  Thread.sleep(5000);
        actions
                .waitUntilVisible_15000(chooseApproverInPeoplePeackerField)
                .waitUntilAppear_15000(chooseApproverInPeoplePeackerField)
                .click(chooseApproverInPeoplePeackerField);*/

        actions
                .waitUntilAppear_15000(newApproverField)
                .enterText(newApproverField,text,"chooseNewApprover");
        chooseApproverInPeoplePeackerField.shouldHave(text("Верезумская Ирина Викторовна"));
        actions
                .click(chooseApproverInPeoplePeackerField);
        return this;
    }




    public void enterTextInTo_AppealField_FeedbackCard_status_New_BackFromAp_ByMM(String text) throws InterruptedException {

        Thread.sleep(3000);
      //  actions.waitUntilBecomeClickable(sendBtn);
       // actions.waitToBeVisible(appealField);
        actions.switchTo2ndFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        logger.info(appealField + "visible");
        actions.enterText(appealField, text);
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

    public boolean isFeedback_AlternativeChannels_Answered() {
        actions.waitUntilVisible_15000(deleteFeedbackBtnAlternativeChannels);
        return deleteFeedbackBtnAlternativeChannels.isDisplayed();
    }

/*    @Step
    public boolean isFeedbackInFAQList()  {
        actions.waitUntilVisible_15000(textInLastFeedbackInTopicProductFAQ);//test
        return textInLastFeedbackInTopicProductFAQ.getText().contains(ViewListOfFeedbacks_Page_MainModerator.answerText);
        }
        */




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


