package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhotogallaryPageAll extends ParentPage {
    public PhotogallaryPageAll(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(css = ".body-firstAlbum-block > div > div:nth-of-type(1)")
    public WebElement btnCreateNewAlbum;
    @FindBy(css = ".textarea-album-name__wrapper #words")
    public WebElement inputTitle;
    @FindBy(css = ".textarea-album-short__wrapper #words")
    public WebElement inputShortDescription;
    @FindBy(css = ".textarea-album-description__wrapper #words")
    public WebElement inputDescription;
    @FindBy(css = ".has-validation .mat-select-value")
    public WebElement dropdownTypeOfEvent;
    @FindBy(css = ".photogallery_datapicker .mat-select-value")
    public WebElement dropdownTA;
    @FindBy(css = "[for='mat-checkbox-2-input'] .mat-checkbox-inner-container")
    public WebElement checkboxDisableLikes;
    @FindBy(css = "[for='mat-checkbox-3-input'] .mat-checkbox-inner-container")
    public WebElement checkboxDisableComments;
    @FindBy(css = "[for='mat-checkbox-4-input'] .mat-checkbox-inner-container")
    public WebElement checkboxDisablePhotoDownloading;
    @FindBy(css = "[for='mat-checkbox-5-input'] .mat-checkbox-inner-container")
    public WebElement checkboxDisablePhotoSharing;
    @FindBy(css = ".dynamic-form-button-block [type='submit']")
    public WebElement btnSave;
    @FindBy(css = ".dynamic-form-button-block [type='button'] .ng-star-inserted")
    public WebElement btnCancel;
    /*
    @FindBy(css = "")
    public WebElement ;
     */
    public void createNewAlbum(){
        actions.click(btnCreateNewAlbum);
    }

    public void writeTitle(String text){
        actions.insertText(inputTitle, text);
    }

    public void writeShortDescription(String text){
        actions.insertText(inputShortDescription, text);
    }

    public void writeDescription(String text){
        actions.insertText(inputDescription, text);
    }

    public void chooseTypeOfEvent(){
        actions.click(dropdownTypeOfEvent);
        actions.click(webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(1, 10) + ") > .mat-option-text")));
    }

    public void chooseTA() throws InterruptedException {
        Thread.sleep(500);
        actions.click(dropdownTA);
        actions.click(webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(1, 28) + ") > .mat-option-text")));
    }

    public void disableLikes(){
        actions.click(checkboxDisableLikes);
    }

    public void disableComments(){
        actions.click(checkboxDisableComments);
    }

    public void disablePhotoDownloading(){
        actions.click(checkboxDisablePhotoDownloading);
    }

    public void disablePhotoSharing(){
        actions.click(checkboxDisablePhotoSharing);
    }

    public void saveAlbum(){
        actions.click(btnSave);
    }
}
