package libs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Actions {
    WebDriver webDriver;
    WebDriverWait wait;

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 15);
    }

    public void wait(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){
            Assert.fail("Can`t find field " + element + "! Exception: " + e);
        }
    }

    public void click(WebElement element) {
        try {
            wait(element);
            element.click();
        } catch (Exception e) {
            Assert.fail("Can`t click on element " + e);
        }
    }


    public void insertText(WebElement element, String text) {
        try {
            wait(element);
            element.clear();
            element.sendKeys(text);
            System.out.println("find done");
        } catch (Exception e) {
            Assert.fail("Can`t insert text in field " + e);
        }
    }

    public String currentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /*public int randomNumber30(){
        int a = 1; // Начальное значение диапазона - "от"
        int b = 30; // Конечное значение диапазона - "до"

        int random_number1 = a + (int) (Math.random() * b); // Генерация 1-го числа
        System.out.println("1-ое случайное число: " + random_number1);
        return random_number1;
    }

    public int randomNumber5(){
        int a = 1; // Начальное значение диапазона - "от"
        int b = 5; // Конечное значение диапазона - "до"

        int random_number1 = a + (int) (Math.random() * b); // Генерация 1-го числа
        System.out.println("1-ое случайное число: " + random_number1);
        return random_number1;
    }*/

    public int randomNumber(int a, int b){
        //int a = 1; // Начальное значение диапазона - "от"
        //int b = 5; // Конечное значение диапазона - "до"

        int random_number1 = a + (int) (Math.random() * b); // Генерация 1-го числа
        System.out.println("1-ое случайное число: " + random_number1);
        return random_number1;
    }


    public boolean existsElement(WebElement element) {
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("Element is not present ");
            return false;
        }
        return true;
    }

    public void selectRandomNews(){
        //div:nth-of-type(1) > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link
        webDriver.findElement(By.cssSelector("div:nth-of-type(" + randomNumber(1, 15) + ") > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link")).click();
    }

    public void selectNewsByCounter(String testcomment, WebElement element){
        int i = 1;
        boolean find = false;



        for (i = 2; i <= 15; i++){
            webDriver.findElement(By.cssSelector("div:nth-of-type(" + i + ") > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link")).click();
            if (webDriver.findElements(By.cssSelector("app-comment-item:nth-of-type(1) .ng-star-inserted.user-comment__add-emoji-wrapper > .mat-button.ng-star-inserted.user-comment__send-subcomment")).size() > 0){
                find = true;
                break;
            }
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span:nth-of-type(2) > .breadcrumb__link"))).click();
            //webDriver.findElement(By.cssSelector("span:nth-of-type(2) > .breadcrumb__link")).click();
            //webDriver.findElement(By.cssSelector("div:nth-of-type(" + i + ") > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link")).click();
            //webDriver.findElement(By.cssSelector("app-comment-item:nth-of-type(1) .ng-star-inserted.user-comment__add-emoji-wrapper > .mat-button.ng-star-inserted.user-comment__send-subcomment"));

        }

        //return counter;
    }




    /*public void chooseElementFromListByIndex(){
        webDriver.findElements(By.cssSelector(selector));
    }
     */
}
