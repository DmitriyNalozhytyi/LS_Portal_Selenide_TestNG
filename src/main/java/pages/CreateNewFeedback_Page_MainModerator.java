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

  //      Thread.sleep(2000);
        if (TopicFieldDDList.size() > 0) {
            actions.waitToBeClickable(DDlist);
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


}