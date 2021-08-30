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

import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertTrue;

public class CreateNewFeedback_Page_MainModerator extends ParentPage {

    @FindBy(css = "mat-option:nth-of-type(1) > .mat-option-text")
    private WebElement directionManagementCompany;

    @FindBy(css = ".mat-datepicker-toggle > .mat-icon-button")
    private WebElement dateIcon;

    @FindBy(css = "tr:nth-of-type(2) > td:nth-of-type(1) > .mat-calendar-body-cell-content")
    private WebElement dateInCalendar;

    @FindBy(css = "input[role='combobox']")
    private WebElement speakerPeoplePickekField;

    @FindBy(css = ".modern-option__title")
    private WebElement chooseSpeakerInPeoplePeackerField;

    @FindBy(css = ".dark-border.feedback-placeholder.main-input.ng-invalid.ng-pristine.ng-untouched")
    private WebElement countOfPeopleField;

    @FindBy(css = "mat-option:nth-of-type(5) > .mat-option-text")
    private WebElement infoConsLine;

    @FindBy(css = "[placeholder='Введите номер телефона']")
    private WebElement phoneField;

    @FindBy(css = "mat-option:nth-of-type(6) > .mat-option-text")
    private WebElement corpMassMedia;

    private final SelenideElement lastFeedbackInTopicProductFAQ = $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-item.single");

    @FindBy(className = "mat-select-arrow-wrapper")
    public WebElement DDlist;

    private final SelenideElement portalChannel = $("#mat-option-0");

    private final SelenideElement arrowChooseChannel = $(".create-feedback-select .mat-select-arrow");
    private final SelenideElement arrowChooseTopic = $(".textarea-field-label");


    @FindBy(id = "mat-option-3")
    public WebElement personalMeetings;


    private final SelenideElement prodaction = $("mat-option-29");
    private final SelenideElement appealField = $("#tinymce");
    private final SelenideElement sendBtn = $(".feedback-button");


    private SelenideElement communicationChannelField() {return $$(".mat-select-arrow-wrapper").get(0).waitUntil(Condition.appears,10000);}
    private SelenideElement topicField() {return $$(".mat-select-arrow-wrapper").get(2).waitUntil(Condition.appears,20000);}
    private SelenideElement production() {return $$(".mat-option-text").get(2).waitUntil(Condition.appears,20000);}
    private SelenideElement productionTopicInFAQList() {return $$(".accordion").get(2).waitUntil(Condition.appears,20000);}

    Logger logger = Logger.getLogger(getClass());
    String titleText2;

/*

 /*public Actions dropdown(String fieldName, String value, SelenideElement element) {
        try {
            element.click();
            $(".mat-select-content").waitUntil(Condition.appear,10000).findAll(".ng-star-inserted").find(Condition.matchText(value)).click();
            System.out.println("Element '" + value + "' selected for " + fieldName);
        } catch (Exception e) {
            Assert.fail("The element missing in the list" + e);
        }
        return this;
    }
*/

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
     public boolean isFeedbackInFAQList()  {
        return $("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p").getText().contains(ViewListOfFeedbacks_Page_MainModerator.answerText);
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








  /*  JUnit
    @Step
    public void clickOnSendBtn() {
        actions.waitToBeVisible(sendBtn);
        actions.click(sendBtn);

    }*/

    @Step
     public CreateNewFeedback_Page_MainModerator clickOnSendBtn() {
        actions.click(sendBtn, "Send");
        return this;
    }


    @Step
    public CreateNewFeedback_Page_MainModerator openTopicProductFAQ() throws InterruptedException {
  //  $$(".accordion").get(2).click();
        actions.click(productionTopicInFAQList());
    logger.info("accordions choosed");
    return this;



 /*       logger.info("choosed 2nd frame");
        return this;


        if (list.size() > 0) {
            List.get(List.size() - 11).click();
            logger.info("last close btn clicked");
        } else {
            logger.info("!!! number of same elements '0'!!! ");
        }*/
    }

    @Step
    public CreateNewFeedback_Page_MainModerator openLastFeebbackInTopicProductFAQ() throws InterruptedException {
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

    @Step
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
    }

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

    @Step
    public void chooseDate() {
        actions.click(dateIcon);
        actions.click(dateInCalendar);

    }

    @Step
    public void chooseSpeakerOrInitiatorInPeolePeakerField(String text) throws InterruptedException {

        actions.enterText(speakerPeoplePickekField, text);
        Thread.sleep(1000);
        actions.click(chooseSpeakerInPeoplePeackerField);
        Thread.sleep(2000);
    }

    @Step
    public void insertCountOfPeopleField(String text) {
        actions.enterText(countOfPeopleField,text);
    }

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
    public void choose_CommunicationChannel_InfoConsLine() throws InterruptedException {

        List<WebElement> DDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(DDList.size() + " - number of DD");

        if (DDList.size() > 0) {
            actions.waitUntilBecomeClickable(DDlist);
            DDList.get(DDList.size() - 3).click();
            actions.waitUntilBecomeClickable(infoConsLine);
            actions.click(infoConsLine);
            logger.info("personal meetings Channel choosed");
            Thread.sleep(2000);
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
           logger.info("!!! number of same elements '0'!!!");
        }

    }

    @Step
    public void insertPhoneNumber(String text) {
        actions.enterText(phoneField, text);
    }

    @Step
    public void choose_CommunicationChannel_CorpMassMedia() throws InterruptedException {
        List<WebElement> DDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(DDList.size() + " - number of DD");

        if (DDList.size() > 0) {
            actions.waitUntilBecomeClickable(DDlist);
            DDList.get(DDList.size() - 3).click();
            actions.waitUntilBecomeClickable(corpMassMedia);
            actions.click(corpMassMedia);
            logger.info("personal meetings Channel choosed");
            Thread.sleep(2000);
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
            logger.info("!!! number of same elements '0'!!!");
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




  /*  public boolean isFeedbackInFAQListOld() {

       if (
        //webDriver.findElement(By.cssSelector("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText().contains("Main")){
        titleText2.contains(webDriver.findElement(By.cssSelector("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText()))
        {
           System.out.println("TRUEEE");
           return true;

       }else {
           System.out.println("FALSEE");
           return false;
        }

    }
*/
   /* public boolean isFeedbackInFAQListOld() {
       // return assertTrue(webDriver.findElement(By.cssSelector(".title")).getText().contains("text"));
       // assertTrue(driver.findElement(By.cssSelector(".title")).getText().contains("text"));
    }*/
}