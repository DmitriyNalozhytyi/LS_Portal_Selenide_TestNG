package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhotogallaryPageAlbum extends ParentPage {
    @FindBy(css = ".headline__text")
    public WebElement fieldAlbumTitle;
    @FindBy(css = ".album__album-description")
    public WebElement fieldAlbumDescription;
    @FindBy(css = ".photogallery > div:nth-child(1)")
    public WebElement fieldImage;

    @FindBy(css = ".mat-button .mat-button-wrapper")
    public WebElement btnAddPhotoToAlbum;
    @FindBy(css = "input[type='file']")
    WebElement inputDownloadImage;
    @FindBy(css = ".dynamic-form-button-block [type='submit'] .ng-star-inserted")
    public WebElement btnSaveImage;


        /*
    @FindBy(css = "")
    public WebElement ;

     */

    public void checkAlbumTitle(){
        actions.waitToBeVisible(fieldAlbumTitle);
    }
    public void checkAlbumDescription(){
        actions.waitToBeVisible(fieldAlbumDescription);
    }
    public void checkPhotoInAlbum(){
        actions.waitToBeVisible(fieldImage);
    }

    public void downloadImageToAlbum() throws InterruptedException {
        Thread.sleep(500);
        actions.click(btnAddPhotoToAlbum);
        inputDownloadImage.sendKeys("C:/Users/vkor2/OneDrive - Lizard Soft/Test Item/Test Pictures/Normal size/" + actions.randomNumber(1, 30) + ".jpg"); //Выбираем рандомную фотку в указанном диапазоне
        actions.click(btnSaveImage);
    }
}
