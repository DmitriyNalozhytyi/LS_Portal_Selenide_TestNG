package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class LoremIpsum extends ParentPage {
    public LoremIpsum(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(css = "input#generate")
    public WebElement btnGenerateLipsun;

    public String lorem() throws InterruptedException {
        String a = "window.open('https://ru.lipsum.com/','_blank');";
        ((JavascriptExecutor)webDriver).executeScript(a);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        //Thread.sleep(1500);
        //webDriver.switchTo().activeElement();
        Thread.sleep(1000);
        btnGenerateLipsun.click();
        Thread.sleep(2000);
        String lipsum = webDriver.findElement(By.cssSelector("#lipsum")).getText();
        webDriver.close();
        webDriver.switchTo().window(tabs.get(0));
        return lipsum;
    }
}
