package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CreateNewFeedback_Page_MainModerator extends ParentPage {

    public CreateNewFeedback_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(css = "div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-item.single")
    private WebElement lastFeedbackInTopicProductFAQ;

    @FindBy(className = "mat-select-arrow-wrapper")
    public WebElement DDlist;

    @FindBy(id = "mat-option-0")
    public WebElement portalChannel;

    @FindBy(id = "mat-option-28")
    public WebElement prodaction;

    @FindBy(id = "tinymce")
    public WebElement appealField;

    @FindBy(className = "feedback-button")
    public WebElement sendBtn;

    Logger logger = Logger.getLogger(getClass());


     String titleText2;


    public void choose_CommunicationChannelField() throws InterruptedException {

        List<WebElement> CommunicationChannelDDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(CommunicationChannelDDList.size() + " - number of DD");
        System.out.println(CommunicationChannelDDList.size() + " - number of DD");

        if (CommunicationChannelDDList.size() > 0) {
            actions.waitUntilBecomeClickable(DDlist);
            CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 3).click();
            actions.waitUntilBecomeClickable(portalChannel);
            actions.click(portalChannel);
            System.out.println("portalChannel choosed");
            Thread.sleep(2000);
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
            System.out.println("!!! number of same elements '0'!!!");
        }
    }

    public void choose_TopicField() throws InterruptedException {

        List<WebElement> TopicFieldDDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));


        Thread.sleep(2000);
        if (TopicFieldDDList.size() > 0) {
         //   actions.waitUntilBecomeClickable(DDlist);
            actions.waitUntilVisibilityOfAllelements(DDlist);

            TopicFieldDDList.get(TopicFieldDDList.size() - 1).click();
            System.out.println("Topic_DD clicked");
            actions.waitUntilBecomeClickable(prodaction);
            actions.waitUntilBecomeVisible(prodaction);
            actions.waitUntilBecomeClickable(prodaction);
            actions.click(prodaction);
            System.out.println("prodaction choosed");
        } else {
   //         logger.info("!!! number of same elements '0'!!! ");
            System.out.println("!!! number of same elements '0'!!!");

        }
    }

    public void enterTextInTo_AppealField(String text) {

        try {
            List<WebElement> frames = webDriver.findElements(By.tagName("iframe"));
            System.out.println(frames.size() + " - number of frames AppealField byMainModerator");

            if (frames.size() == 1) {
                actions.switchTo1stFrameOf1(appealField);
            }else {

                actions.switchTo1stFrameOf2(appealField);
            }
        }catch (Exception e){
            System.out.println("no frames");
           actions.printErrorAndStopTest(e);
            //Assert.fail("Can`t click on element " + e);

        }
    //    actions.switchTo1stFrameOf2(appealField);
        actions.waitUntilBecomeVisible(appealField);
        actions.insertText(appealField, text);
        actions.switchToDefaultContentFromFrame();
    }

    public void clickOnSendBtn() {
        actions.waitUntilBecomeClickable(sendBtn);
        actions.click(sendBtn);

    }


    public void openTopicProductFAQ() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> List = webDriver.findElements(By.className("accordion"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(List.size() + " - number of elements");
        System.out.println(List.size() + " - number of element");

        if (List.size() > 0) {
            List.get(List.size() - 11).click();
            System.out.println("last close btn clicked");
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
            System.out.println("!!! number of same elements '0'!!!");
        }
    }

    public void openLastFeebbackInTopicProductFAQ() throws InterruptedException {
        Thread.sleep(3000);
        actions.waitUntilBecomeClickable(lastFeedbackInTopicProductFAQ);
        actions.click(lastFeedbackInTopicProductFAQ);
    }

    public boolean isFeedbackInFAQList() {

        if (
            webDriver.findElement(By.cssSelector("div:nth-of-type(3) > app-faq-topic .ng-star-inserted > div:nth-of-type(1) > .accordion-item-container > .accordion-content > .accordion-content-wrapper > .feedback-answer > p")).getText().contains("Main")){


            System.out.println("TRUEEE");
            return true;

        }else {
            System.out.println("FALSEE");
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