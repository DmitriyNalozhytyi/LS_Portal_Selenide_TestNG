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

    @FindBy(css = "#amount")
    public WebElement inputNumParagraphs;

    public String getLorem(int a, int b) throws InterruptedException {
        //Получаем случайный текст с сайта lipsum.com
        String lipsumcom = "window.open('https://ru.lipsum.com/','_blank');";
        ((JavascriptExecutor)webDriver).executeScript(lipsumcom);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles()); //Открываем новую вкладку браузера
        webDriver.switchTo().window(tabs.get(1));
        Thread.sleep(1000);
        inputNumParagraphs.clear();
        inputNumParagraphs.sendKeys("" + actions.randomNumber(a, b)); //Выбираем количество генерируемых абзацев в диапазоне от a до b
        btnGenerateLipsun.click();
        Thread.sleep(2000);
        String lipsum = webDriver.findElement(By.cssSelector("#lipsum")).getText(); //Записываем полученный текст в переменную
        webDriver.close();
        webDriver.switchTo().window(tabs.get(0));
        return lipsum;
    }
}
