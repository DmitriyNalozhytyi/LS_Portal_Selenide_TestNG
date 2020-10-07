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
import org.apache.log4j.Logger;

public class Actions {
    WebDriver webDriver;
    WebDriverWait wait;

    // выбираем: Logger (org.apache.log4j)
    // создаем объект, который будет писать нам лог
    Logger logger = Logger.getLogger(getClass());

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 15);
    }
    public void wait(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void refreshPage(){
        //webDriver.navigate().refresh();
        webDriver.navigate().to(webDriver.getCurrentUrl());
    }

    public void waitToBeClickable(WebElement element) {
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
            System.out.println("Text inserted");
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

        int random_number1 = a + (int) (Math.random() * b); // Генерация числа
        System.out.println("Random number is: " + random_number1);
        return random_number1;
    }
    public boolean existsElement(WebElement element) {
        //Проверка наличия элемента на странице
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
    public void searchNewsWithComment(String testcomment, WebElement element){
        //Проверка наличия комментария в новости
        int i;
        i = 1;
        boolean find = false;

        for (i = 1; i <= 15; i++){
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

    public void switchTo1stFrameOf2(WebElement element) {
        try {
            webDriver.switchTo().defaultContent();
            Thread.sleep(1000);
            webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 2);
            logger.info("Frame was changed");
            //   System.out.println("Frame was changed");
        } catch (Exception e) {
            logger.info("Can't switch to frame");
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.info("Can't work with element" + e);
        // Assert.fail - безусловная остановка теста
        Assert.fail("Can't work with element" + e);
    }

    public void switchToDefaultContentFromFrame(){
        try {
            webDriver.switchTo().defaultContent();
        }catch (Exception e){
            System.out.println("Can't switch to default content from iframe");
        }
    }

}
