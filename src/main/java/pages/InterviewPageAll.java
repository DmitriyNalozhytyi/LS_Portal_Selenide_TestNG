package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InterviewPageAll extends ParentPage {
    @FindBy(css = "[for='mat-radio-5-input'] .mat-radio-label-content")
    public WebElement rbtnNewInterview;

    @FindBy(className = "create-btn")
    public WebElement btnCreate;


    public void selectPublicationTypeInterview() {
        rbtnNewInterview.click();
        btnCreate.click();
        actions.click(rbtnNewInterview);
        actions.click(btnCreate);
    }
}
