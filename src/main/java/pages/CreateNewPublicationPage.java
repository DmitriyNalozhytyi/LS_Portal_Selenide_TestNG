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

    @FindBy(css = "app-file-image-uploader-singleton > input[type='file']")
    WebElement inputDownloadImage;
    
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
        inputDownloadImage.sendKeys("C:/Users/vkor2/OneDrive - Lizard Soft/Test Item/Test Pictures/Normal size/" + actions.randomNumber(1, 30) + ".jpg"); //Выбираем рандомную фотку в указанном диапазоне
        Thread.sleep(1000);
        btnSaveImage.click();
    }

    public void writeDescription(String test_description) {
        webDriver.switchTo().frame(inputDescription);
        webDriver.switchTo().activeElement().sendKeys(test_description);
        webDriver.switchTo().defaultContent();
    }

    public void selectRandomTA(){
        WebElement selectTA;
        inputTA.click();
        selectTA = webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(2, 29) + ") > .mat-option-text"));
        actions.click(selectTA);
        //webDriver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
        //webDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public void selectRandomContentType(){
        webDriver.findElement(By.cssSelector("mat-radio-button:nth-of-type(" + actions.randomNumber(1, 3) + ")  .mat-radio-label-content")).click();
        //rbtnContentType.click();
    }

    public void selectRandomRubric() throws InterruptedException {
        inputRubric.click();
        Thread.sleep(500);
        webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(1, 6) + ") > span > div")).click();
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
