package libs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
    WebDriver webDriver;
    WebDriverWait wait;


    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 15);
    }

    public void wait(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element){
        try {
            wait(element);
            element.click();
        }catch (Exception e){
            Assert.fail("Can`t click on element" + e);
        }
    }


    public void insertText(WebElement element, String text){
        try {
            wait(element);
            element.clear();
            element.sendKeys(text);
            System.out.println("find done");
        }catch (Exception e){
            Assert.fail("Can`t insert text in field " + e);
        }
    }
    /*public void chouseElemenFromListByIndex(){
        webDriver.findElements(By.cssSelector(selector));
    }

     */
}
