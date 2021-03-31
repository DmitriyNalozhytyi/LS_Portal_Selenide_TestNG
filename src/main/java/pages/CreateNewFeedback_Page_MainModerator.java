package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CreateNewFeedback_Page_MainModerator extends ParentPage {

   // @FindBy(id = "mat-option-11")
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

    @FindBy (css = ".dark-border.feedback-placeholder.main-input.ng-invalid.ng-pristine.ng-untouched")
    private WebElement countOfPeopleField;

    @FindBy(css = "mat-option:nth-of-type(5) > .mat-option-text")
    private WebElement infoConsLine;

    @FindBy(css = "[placeholder='Введите номер телефона']")
    private WebElement phoneField;

    @FindBy(css = "mat-option:nth-of-type(6) > .mat-option-text")
    private WebElement corpMassMedia;

    /*public CreateNewFeedback_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }
*/
    @FindBy(css = "div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-item.single")
    private WebElement lastFeedbackInTopicProductFAQ;

    @FindBy(className = "mat-select-arrow-wrapper")
    public WebElement DDlist;

    @FindBy(id = "mat-option-0")
    public WebElement portalChannel;

    @FindBy(id = "mat-option-3")
    public WebElement personalMeetings;

    @FindBy(id = "mat-option-28")
    public WebElement prodaction;


    @FindBy(id = "tinymce")
    public WebElement appealField;

    @FindBy(className = "feedback-button")
    public WebElement sendBtn;

    Logger logger = Logger.getLogger(getClass());


     String titleText2;

    @Step
    public void choose_CommunicationChannel_Portal() throws InterruptedException {

        List<WebElement> DDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(DDList.size() + " - number of DD");

        if (DDList.size() > 0) {
            Thread.sleep(3000);
            actions.waitUntilBecomeClickable(DDlist);
            DDList.get(DDList.size() - 3).click();
            actions.waitUntilBecomeClickable(portalChannel);
            actions.click(portalChannel);
            logger.info("portalChannel choosed");
            Thread.sleep(2000);
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
            logger.info("!!! number of same elements '0'");
        }
    }

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
    }

    @Step
    public void enterTextInTo_AppealField(String text) {
     /*   actions.switchTo1stFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
*/
        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
           logger.info(frames.size() + " - number of frames AppealField byMainModerator");

            if (frames.size() == 1) {
                actions.switchTo1stFrameOf1(appealField);
            }else {

                actions.switchTo1stFrameOf2(appealField);
            }
        }catch (Exception e){
            logger.info("no frames");
           actions.printErrorAndStopTest(e);
            //Assert.fail("Can`t click on element " + e);

        }
    //    actions.switchTo1stFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        actions.enterText(appealField, text);
        actions.switchToDefaultContentFromFrame();
    }

    @Step
    public void clickOnSendBtn() {
        actions.waitToBeVisible(sendBtn);
        actions.click(sendBtn);

    }

    @Step
    public void openTopicProductFAQ() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> List = webDriver.findElements(By.className("accordion"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(List.size() + " - number of elements");

        if (List.size() > 0) {
            List.get(List.size() - 11).click();
            logger.info("last close btn clicked");
        } else {
            logger.info("!!! number of same elements '0'!!! ");
        }
    }

    @Step
    public void openLastFeebbackInTopicProductFAQ() throws InterruptedException {
        Thread.sleep(3000);
        actions.waitUntilBecomeClickable(lastFeedbackInTopicProductFAQ);
        actions.click(lastFeedbackInTopicProductFAQ);
    }

    @Step
    public boolean isFeedbackInFAQList() throws InterruptedException {
        Thread.sleep(2000);
        if (
            webDriver.findElement(By.cssSelector("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText().contains("Main")){
            logger.info("TRUE");
            return true;

        }else {
            logger.info("FALSEE");
            return false;
        }

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




   /* public boolean isFeedbackInFAQList() {

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

    }*/

   /* public boolean isFeedbackInFAQList() {
       // return assertTrue(webDriver.findElement(By.cssSelector(".title")).getText().contains("text"));
       // assertTrue(driver.findElement(By.cssSelector(".title")).getText().contains("text"));
    }*/
}