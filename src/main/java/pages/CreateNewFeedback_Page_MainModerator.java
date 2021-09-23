package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class CreateNewFeedback_Page_MainModerator extends ParentPage {

    private final SelenideElement openDDToChooseDirection = $(" ls-select-field .mat-select-arrow-wrapper");
    private final SelenideElement chooseDirectionManagementCompanyInDD = $("mat-option:nth-of-type(1) > .mat-option-text");
    private final SelenideElement dateIcon = $(".date_icon.ng-star-inserted");
//    private final SelenideElement dateInCalendar = $("tr:nth-of-type(3) > .ng-star-inserted.owl-dt-calendar-cell.owl-dt-day-4 > .owl-dt-calendar-cell-content > font > font");
    private final SelenideElement dateInCalendar = $("tr:nth-of-type(3) > .ng-star-inserted.owl-dt-calendar-cell.owl-dt-day-4 > .owl-dt-calendar-cell-content");


    private final SelenideElement speakerPeoplePickerField = $("input[role='combobox']");
    private final SelenideElement chooseSpeakerInPeoplePeackerField = $(".modern-option__title");
    private final SelenideElement countOfPeopleField = $(".dark-border.feedback-placeholder.main-input.ng-invalid.ng-pristine.ng-untouched");
    private final SelenideElement infoConsLine = $("mat-option:nth-of-type(5) > .mat-option-text");
    private final SelenideElement phoneField = $("[placeholder='Введите номер телефона']");
    private final SelenideElement chooseDateBtnInDataPeacker = $("[class] [tabindex='0']:nth-of-type(2) font:nth-of-type(1) font");
    private final SelenideElement dateTitle = $(".datepicker-field-label font:nth-of-type(1) font");
    private final SelenideElement corpMassMedia = $("mat-option:nth-of-type(6) > .mat-option-text");



    /*@FindBy(css = "mat-option:nth-of-type(6) > .mat-option-text")
    private WebElement corpMassMedia;*/

    private final SelenideElement lastFeedbackInTopicProductFAQ = $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-item.single");

    @FindBy(className = "mat-select-arrow-wrapper")
    public WebElement DDlist;

    private final SelenideElement portalChannel = $("#mat-option-0");
    private final SelenideElement arrowChooseChannel = $(".create-feedback-select .mat-select-arrow");
    private final SelenideElement arrowChooseTopic = $(".textarea-field-label");
    private final SelenideElement textInLastFeedbackInTopicProductFAQ = $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p");
    private final SelenideElement personalMeetings = $("#mat-option-3");
    private final SelenideElement prodaction = $("mat-option-29");
    private final SelenideElement appealField = $("#tinymce");
    private final SelenideElement sendBtn = $(".feedback-button");
    private SelenideElement communicationChannelField() {return $$(".mat-select-arrow-wrapper").get(0).waitUntil(Condition.appears,10000);}
    private SelenideElement topicField() {return $$(".mat-select-arrow-wrapper").get(2).waitUntil(Condition.appears,20000);}
    private SelenideElement production() {return $$(".mat-option-text").get(2).waitUntil(Condition.appears,20000);}
    private SelenideElement productionTopicInFAQList() {return $$(".accordion").get(2).waitUntil(Condition.appears,20000);}

    Logger logger = Logger.getLogger(getClass());
    String titleText2;



    @Step
    public CreateNewFeedback_Page_MainModerator choose_CommunicationChannel_Portal()  {
        actions
             //   .waitUntilAppear_15000(communicationChannelField())
                .waitUntilAppear_15000(arrowChooseChannel)
                .click(communicationChannelField(), "CommunicationChannel DDField ")
                .waitUntilAppear_15000(portalChannel)
                .click(portalChannel, "Portal");
        logger.info("portal Channel choosed");
        return this;
    }

    @Step
    public CreateNewFeedback_Page_MainModerator choose_CommunicationChannel_PersonalMeeting()  {
        actions
                //   .waitUntilAppear_15000(communicationChannelField())
                .waitUntilAppear_15000(arrowChooseChannel)
                .click(arrowChooseChannel, "CommunicationChannel DDField ")
                .waitUntilAppear_15000(personalMeetings)
                .click(personalMeetings, "personalMeetings");
        logger.info("personalMeetings Channel choosed");
        return this;
    }

    @Step
    public CreateNewFeedback_Page_MainModerator choose_CommunicationChannel_InfoConsLine(){
        actions
                //   .waitUntilAppear_15000(communicationChannelField())
                .waitUntilAppear_15000(arrowChooseChannel)
                .click(arrowChooseChannel, "CommunicationChannel DDField ")
                .waitUntilAppear_15000(infoConsLine)
                .click(infoConsLine, "infoConsLine");
        logger.info("infoConsLine Channel choosed");
        return this;
    }

    @Step
    public CreateNewFeedback_Page_MainModerator choose_CommunicationChannel_CorpMassMedia()  {

        actions
                .waitUntilAppear_15000(arrowChooseChannel)
                .click(arrowChooseChannel, "CommunicationChannel DDField ")
                .waitUntilAppear_15000(corpMassMedia)
                .click(corpMassMedia, "corpMassMedia");
        logger.info("corpMassMedia Channel choosed");
        return this;
    }




    @Step
     public boolean isFeedbackInFAQList()  {
        actions.waitUntilVisible_15000(textInLastFeedbackInTopicProductFAQ);//test
        return textInLastFeedbackInTopicProductFAQ.getText().contains(ViewListOfFeedbacks_Page_MainModerator.answerText);//test
      //  return $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p").getText().contains(ViewListOfFeedbacks_Page_MainModerator.answerText);
    }



    @Step
    public boolean isFeedbackInFAQListOld() throws InterruptedException {
        Thread.sleep(2000);
        if (
                webDriver.findElement(By.cssSelector("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText().contains("Main")){
                // $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText().contains("Main")){
        logger.info("TRUE");
            return true;

        }else {
            logger.info("FALSEE");
            return false;
        }

    }
/*
// JInit
    @Step
    public void choose_TopicField() throws InterruptedException {

        List<WebElement> TopicFieldDDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));


        Thread.sleep(2000);
        if (TopicFieldDDList.size() > 0) {
         //   actions.waitUntilBecomeClickable(DDlist);
            actions.waitUntilVisibilityOfAllelements(DDlist);

            TopicFieldDDList.get(TopicFieldDDList.size() - 1).click();
            logger.info("Topic_DD clicked");

          // цикл. костыль с ожиданием
            Thread.sleep(2000);

            if (prodaction.isDisplayed()) { Thread.sleep(1000);
            actions.waitUntilBecomeClickable(prodaction);
            actions.waitToBeVisible(prodaction);
            actions.waitUntilBecomeClickable(prodaction);
            actions.click(prodaction);
            logger.info("prodaction choosed");
            }else {
                Thread.sleep(1000);
                actions.waitUntilBecomeClickable(prodaction);
                actions.click(prodaction);
                logger.info("prodaction choosed2");
            }



        } else {
   //         logger.info("!!! number of same elements '0'!!! ");
            logger.info("!!! number of same elements '0'!!!");

        }
    }*/

    @Step
    public CreateNewFeedback_Page_MainModerator choose_TopicField() {
        sleep(10000);
        actions
                .waitUntilAppear_15000(topicField())
                .click(topicField(),"Topic DDField")
                .click(production(),"Production");
                 logger.info("Production selected in DDTopicField");
            return this;
    }



    @Step
    public CreateNewFeedback_Page_MainModerator enterTextInTo_AppealField(String text)  {

        Selenide.switchTo().frame(0);
        actions
                .enterText(appealField, text + actions.currentTime(), "Feedback text");
                 Selenide.switchTo().defaultContent();
        return this;
       }


    @Step
     public CreateNewFeedback_Page_MainModerator clickOnSendBtn() {
        actions.click(sendBtn, "Send");
        return this;
    }


    @Step
    public CreateNewFeedback_Page_MainModerator openTopicProductFAQ()  {
  //  $$(".accordion").get(2).click();
        actions.click(productionTopicInFAQList());
    logger.info("accordions choosed");
    return this;
    }


    @Step
    public CreateNewFeedback_Page_MainModerator chooseDate(){
        actions
                .waitUntilAppear_15000(dateIcon)
                .click(dateIcon);
        actions
                .waitUntilAppear_15000(dateInCalendar)
                .waitUntilVisible_15000(dateInCalendar)
                .click(dateInCalendar);
        /*actions
                .waitUntilAppear_15000(dateIcon)
                .click(dateIcon);*/

        /*actions
                .click(chooseDateBtnInDataPeacker);*/

       /* actions
                .click(dateIcon);*/

        return this;

        }


    @Step
    public CreateNewFeedback_Page_MainModerator openLastFeebbackInTopicProductFAQ() {
      //  Thread.sleep(10000);
        actions
                .waitUntilAppear_15000(lastFeedbackInTopicProductFAQ)
                .click(lastFeedbackInTopicProductFAQ, "text");
        logger.info("opened last feedback in topic PRODUCT in FAQ");
      //  Thread.sleep(6000);
        return this;
    }





    @Step
    public void choose_Rundom_TopicField() throws InterruptedException {

        List<WebElement> TopicFieldDDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));


        Thread.sleep(2000);
        if (TopicFieldDDList.size() > 0) {
            //   actions.waitUntilBecomeClickable(DDlist);
            actions.waitUntilVisibilityOfAllelements(DDlist);

            TopicFieldDDList.get(TopicFieldDDList.size() - 1).click();
           logger.info("Topic_DD clicked");

            actions.waitUntilBecomeClickable(prodaction);
            actions.waitToBeVisible(prodaction);
            actions.waitUntilBecomeClickable(prodaction);

           // WebElement selectRundonTopic = webDriver.findElement(By.id("mat-option-" + actions.randomNumber(26, 38)));
            //mat-option:nth-of-type(3)  .tooltip-wrapper
            WebElement selectRundonTopic = webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(1, 13) + ")  .tooltip-wrapper"));

            logger.info("locator="+selectRundonTopic);
            actions.click(selectRundonTopic);

         //   actions.randomNumber(1,13);

            /*actions.click(prodaction);
            System.out.println("prodaction choosed");*/

        } else {
            logger.info("!!! number of same elements '0'!!! ");
        }

    }

  /*  @Step
    public void choose_Direction_ManagementCompany_Field() throws InterruptedException {

        List<WebElement> DDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(DDList.size() + " - number of DD");

        if (DDList.size() > 0) {
            actions.waitUntilBecomeClickable(DDlist);
            DDList.get(DDList.size() - 2).click();
            actions.waitUntilBecomeClickable(directionManagementCompany);
            actions.click(directionManagementCompany);
           logger.info("directionManagement choosed");
            Thread.sleep(2000);
        } else {
            logger.info("!!! number of same elements '0'!!! ");
        }
    }*/

    @Step
    public CreateNewFeedback_Page_MainModerator choose_Direction_ManagementCompany_Field()  {

        actions
                .waitUntilAppear_15000(openDDToChooseDirection)
                .click(openDDToChooseDirection);
        actions
                .waitUntilAppear_15000(chooseDirectionManagementCompanyInDD)
                .click(chooseDirectionManagementCompanyInDD);
        return this;
    }
  //  ls-select-field .mat-select-arrow-wrapper

    @Step
    public void choose_CommunicationChannel_PersMeet() throws InterruptedException {

        List<WebElement> DDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(DDList.size() + " - number of DD");
        logger.info(DDList.size() + " - number of DD");

        if (DDList.size() > 0) {
            actions.waitUntilBecomeClickable(DDlist);
            DDList.get(DDList.size() - 3).click();
            actions.waitUntilBecomeClickable(personalMeetings);
            actions.click(personalMeetings);
            logger.info("personal meetings Channel choosed");
            Thread.sleep(2000);
        } else {
            logger.info("!!! number of same elements '0'!!! ");
        }
    }


   /* @Step
    public void insertCountOfPeopleField(String text) {
        actions.enterText(countOfPeopleField,text);
    }*/

    @Step
    public boolean isBtnDeleteIsPresent() {
       /* if (
                webDriver.findElement(By.cssSelector(".dynamic-form-button.feedback-button__gray.mat-button")).isDisplayed()){
           logger.info("TRUE");
            return true;

        }else {
            logger.info("FALSE");
            return false;
        }*/

       try {
           webDriver.findElement(By.cssSelector(".dynamic-form-button.feedback-button__gray.mat-button")).isDisplayed();
               logger.info("TRUE");
               return true;
       }catch (Exception e){
           logger.info("FALSE");
           return false;
       }
    }







    @Step
    public boolean isField_TopicField_Required() throws InterruptedException {
        actions.click(sendBtn);
        if (
                webDriver.findElement(By.className("feedback-button")).isDisplayed()){
            logger.info("FIELD TopicField IS  REQUIRED");
            return true;

        }else {
            logger.info("FIELD TopicField IS NOT REQUIRED");
            return false;
        }


    }

    @Step
    public void clearField_AppealField() {

        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
            logger.info(frames.size() + " - number of frames AppealField byMainModerator");

            if (frames.size() == 1) {
                actions.switchTo1stFrameOf1(appealField);
                webDriver.findElement(By.id("tinymce")).clear();
                webDriver.findElement(By.id("tinymce")).sendKeys("\b\b\b\b\b\b");

            }else {

                actions.switchTo1stFrameOf2(appealField);
                webDriver.findElement(By.id("tinymce")).clear();
                webDriver.findElement(By.id("tinymce")).sendKeys("\b\b\b\b\b\b");
            }
        }catch (Exception e){
            logger.info("no frames");
            actions.printErrorAndStopTest(e);
            //Assert.fail("Can`t click on element " + e);

        }
        //    actions.switchTo1stFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        webDriver.findElement(By.id("tinymce")).clear();
        actions.switchToDefaultContentFromFrame();

    }

    @Step
    public boolean isField_AppealField_Required() {
        actions.click(sendBtn);

        if (
                webDriver.findElement(By.className("feedback-button")).isDisplayed()){
            logger.info("FIELD AppealField IS  REQUIRED");
            return true;

        }else {
            logger.info("FIELD AppealField IS NOT REQUIRED");
            return false;
        }

    }

    @Step
    public boolean isField_Speaker_Required() {
        actions.click(sendBtn);

        if (
                webDriver.findElement(By.className("feedback-button")).isDisplayed()){
            logger.info("FIELD Speaker IS  REQUIRED");
            return true;

        }else {
            logger.info("FIELD Speaker IS NOT REQUIRED");
            return false;
        }
    }
    public CreateNewFeedback_Page_MainModerator chooseSpeakerOrInitiatorInPeolePeakerField(String text) {
        actions
                .waitUntilAppear_15000(speakerPeoplePickerField)
                .enterText(speakerPeoplePickerField,text,"choose speakerPeoplePickerField");
        actions
                .click(chooseSpeakerInPeoplePeackerField);
        return this;
    }
    public CreateNewFeedback_Page_MainModerator insertCountOfPeopleField(String text) {
        actions
                .waitUntilAppear_15000(countOfPeopleField)
                .enterText(countOfPeopleField,text,"(countOfPeopleField");
        return this;
    }



    @Step
    public CreateNewFeedback_Page_MainModerator insertPhoneNumber(String text) {
        actions
                .waitUntilAppear_15000(phoneField)
                .enterText(phoneField,text,"phoneField");
        return this;


        /*actions.enterText(phoneField, text);*/
    }






/*    public ViewListOfFeedbacks_Page_MainModerator chooseNewApprover(String text){
        actions
                .waitUntilAppear_15000(newApproverField)
                .enterText(newApproverField,text,"chooseNewApprover");
        chooseApproverInPeoplePeackerField.shouldHave(text("Верезумская Ирина Викторовна"));
        actions
                .click(chooseApproverInPeoplePeackerField);
        return this;
    }*/

}