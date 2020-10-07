package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateNewFeedback_Page_MainModerator extends ParentPage {

    public CreateNewFeedback_Page_MainModerator(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "accordion-item single")
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


    public void choose_CommunicationChannelField() {

        List<WebElement> CommunicationChannelDDList = webDriver.findElements(By.className("mat-select-arrow-wrapper"));
        actions.waitUntilVisibilityOfAllelements(DDlist);

        logger.info(CommunicationChannelDDList.size() + " - number of DD");
        System.out.println(CommunicationChannelDDList.size() + " - number of DD");

        if (CommunicationChannelDDList.size() > 0) {
            actions.waitToBeClickable(DDlist);
            CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 3).click();
            actions.waitToBeClickable(portalChannel);
            actions.click(portalChannel);
            System.out.println("portalChannel choosed");
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
            actions.waitToBeClickable(prodaction);
            actions.click(prodaction);
            System.out.println("prodaction choosed");
        } else {
   //         logger.info("!!! number of same elements '0'!!! ");
            System.out.println("!!! number of same elements '0'!!!");
        }
    }

    public void enterTextInTo_AppealField(String text) {
        actions.switchTo1stFrameOf2(appealField);
        actions.waitToBeVisible(appealField);
        actions.insertText(appealField, text);
        actions.switchToDefaultContentFromFrame();
    }

    public void clickOnSendBtn() {
        actions.waitToBeClickable(sendBtn);
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
        actions.click(lastFeedbackInTopicProductFAQ);
    }
}