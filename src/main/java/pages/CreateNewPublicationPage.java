package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CreateNewPublicationPage extends ParentPage {
    public CreateNewPublicationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "date_icon")
    private WebElement btnDateIcon;

    @FindBy(className = "owl-dt-calendar-cell-content")
    private WebElement btnChooseDay;

    @FindBy(className = "owl-dt-control-button-content")
    private WebElement btnConfirmDay;

    @FindBy(css = "[formcontrolname='leadingParagrag']")
    private WebElement inputTitle;

    @FindBy(className = "form_cont_img")
    private WebElement btnEditSlider;

    @FindBy(css = "input[type='file']")
    private WebElement inputAddImage;
    
    @FindBy(css = "[frameborder]")
    private WebElement inputDescription;

    @FindBy(css = "input#mat-chip-list-input-1")
    private WebElement inputTag;

    @FindBy(css = ".button-save-close .mat-button-wrapper")
    private WebElement btnSave;

    @FindBy(css = ".mat-select-placeholder.ng-star-inserted")
    private WebElement inputTA;

    @FindBy(css = "mat-radio-button:nth-of-type(1)  .mat-radio-label-content")
    private WebElement rbtnContentType;

    @FindBy(css = ".img-buttons > button:nth-of-type(2)")
    private WebElement btnSaveImage;

    @FindBy(css = "[formcontrolname='headline']")
    public WebElement inputHeadline;

    @FindBy(css = "[formcontrolname='rubric']")
    public WebElement inputRubric;

    @FindBy(css = "[formcontrolname='subRubric']")
    public WebElement inputSubrubric;


    public void chooseDate() {
        btnDateIcon.click();
        btnChooseDay.click();
        btnConfirmDay.click();
    }

    public void writeHeadline(String text) {
        inputHeadline.sendKeys(text);
    }

    public void writeTitle(String text) {
        inputTitle.sendKeys(text);
    }

    public void addImageToSlider() throws InterruptedException {
        btnEditSlider.click();
        inputAddImage.sendKeys("D:/OneDrive - Lizard Soft/Test Pictures/Normal size/" + actions.randomNumber(1, 30) + ".jpg"); //Выбираем рандомную фотку в указанном диапазоне
        Thread.sleep(1000);
        btnSaveImage.click();
    }

    public void writeDescription(String test_description) throws InterruptedException {
        webDriver.switchTo().frame(inputDescription);
        webDriver.switchTo().activeElement().sendKeys(test_description);
        webDriver.switchTo().defaultContent();
    }

    public void selectTA(){
        inputTA.click();
        webDriver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
        webDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void selectContentType(){
        rbtnContentType.click();
    }

    public void selectRubric() throws InterruptedException {
        inputRubric.click();
        webDriver.findElement(By.cssSelector("mat-option:nth-of-type(1) > span > div")).click();
        Thread.sleep(500);
    }

    public void selectSubrubric() throws InterruptedException {
        inputSubrubric.click();
        Thread.sleep(500);
        webDriver.findElement(By.cssSelector("mat-option:nth-of-type(2) > .mat-option-text")).click();
    }

    public void writeTag(String test) {
        inputTag.sendKeys(test);
        webDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void saveAndPublish() {
        btnSave.click();
    }
}
