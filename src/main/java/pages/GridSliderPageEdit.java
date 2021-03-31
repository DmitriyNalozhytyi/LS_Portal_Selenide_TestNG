package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GridSliderPageEdit extends ParentPage {

    @FindBy(css = ".block-form-enrichment > button:nth-of-type(1)")
    WebElement btnAddNewBlockWithImage;

    @FindBy(css = ".block-form-enrichment > button:nth-of-type(2)")
    WebElement btnAddNewColorBlock;

    @FindBy(css = ".block-form-enrichment > button:nth-of-type(3)")
    WebElement btnSaveAndPublish;

    @FindBy(css = ".buttons > button:nth-of-type(2)")
    WebElement btnConfirmDelete;

    @FindBy(css = "[formcontrolname='headline']")
    WebElement inputHeadline;

    @FindBy(css = "[formcontrolname='description']")
    WebElement inputDescription;

    @FindBy(css = "[formcontrolname='link']")
    WebElement inputLink;

    @FindBy(css = ".mat-select-placeholder.ng-star-inserted")
    WebElement dropdownIcon;

    @FindBy(css = ".block-form-fields-color__input")
    WebElement inputColor;

    @FindBy(css = ".block-form-fields-save")
    WebElement btnSaveBlock;

    @FindBy(css = "app-file-image-uploader-singleton > input[type='file']")
    WebElement inputDownloadImage;

    public int deleteRandomBlock(){
        int randomBlock = actions.randomNumber(2, 5); //Генерируем рандомное число, оно будет означать номер блока, с которым мы работаем
        webDriver.findElement(By.cssSelector("ngx-gridster-item:nth-of-type(" + randomBlock + ")")).click(); //Кликаем по блоку, чтобы отобразить элементы управления(редактировать блок, удалить блок)
        webDriver.findElement(By.cssSelector("ngx-gridster-item:nth-of-type(" + randomBlock + ") button:nth-of-type(2)")).click(); //Удаляем блок, чтобы потом на его месте создать новый
        actions.click(btnConfirmDelete); //Подтверждаем удаление в всплывающем окне
        return randomBlock;
    }
    public void addNewColorBlock(){ //Добавляем новый однотонный блок
        actions.click(btnAddNewColorBlock);
        inputDescription.sendKeys();
        System.out.println(deleteRandomBlock());
    }

    public void addNewBlockWithImage(){ //Добавляем блок с изображением
        actions.click(btnAddNewBlockWithImage);
    }

    public void writeTitle(String text){
        actions.enterText(inputHeadline, text);
    }

    public void writeDescription(String text){
        actions.enterText(inputDescription, text);
    }

    public void writeLink(String text){
        actions.enterText(inputLink, text);
    }

    public void selectRandomIcon(){
        actions.click(dropdownIcon);
        actions.click(webDriver.findElement(By.cssSelector("mat-option:nth-of-type(" + actions.randomNumber(1, 3) + ") > .mat-option-text")));
    }

    public void selectColor(){
        actions.enterText(inputColor, "#ffffff" + Keys.ENTER);
    }

    public void clickSaveBlock(){
        actions.click(btnSaveBlock);
    }

    public void clickSaveGrid(){
        actions.click(btnSaveAndPublish);
    }

    public void addImage(){
        inputDownloadImage.sendKeys("C:/Users/vkor2/OneDrive - Lizard Soft/Test Item/Test Pictures/Normal size/" + actions.randomNumber(1, 30) + ".jpg"); //Выбираем рандомную фотку в указанном диапазоне
    }
}
