package libs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class Actions {
    WebDriver webDriver;
    WebDriverWait wait;

    // выбираем: Logger (org.apache.log4j)
    // создаем объект, который будет писать нам лог
    Logger logger = Logger.getLogger(getClass());

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 60);
    }

    public Actions() {

    }
    public void wait(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void refreshPage(){
        //webDriver.navigate().refresh();
        webDriver.navigate().to(webDriver.getCurrentUrl());
    }

    public void waitUntilBecomeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){
            Assert.fail("Can`t find field " + element + "! Exception: " + e);
        }
    }


  /*  public void checkExpectedResult(String message, boolean actualResult){
        Assert.assertEquals(message, true, actualResult);
    }*/

    public void click(WebElement element) {
        try {
            //wait(element);
            element.click();
        } catch (Exception e) {
            Assert.fail("Can`t click on element " + e);
        }
    }



    @Deprecated
    public void enterText(WebElement element, String text) {
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

    public void switchTo1stFrameOf2(WebElement element) throws InterruptedException {
      /*  try {
            Thread.sleep(2000);
            webDriver.switchTo().defaultContent();
            System.out.println("switch to default content");
            Thread.sleep(5000);
            webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 2);
            System.out.println("switch to 1st frame of 2");
            logger.info("Frame was changed");
               System.out.println("Frame was changed");
        } catch (Exception e) {
            logger.info("Can't switch to frame");
            System.out.println("Can't switch to frame");
            printErrorAndStopTest(e);
        }*/
        Thread.sleep(2000);
        webDriver.switchTo().defaultContent();
        System.out.println("switch to default content");
        Thread.sleep(5000);
        webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 2);
        System.out.println("switch to 1st frame of 2");
        logger.info("Frame was changed");
        System.out.println("Frame was changed");
    }
    public Actions switchTo2ndFrameOf2(WebElement element) {

       /* webDriver.switchTo().defaultContent();
        // Thread.sleep(1000);
        webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 1);
        logger.info("Frame was changed");
        System.out.println("Frame was changed");*/

        List<SelenideElement> frames = $$(By.tagName("iframe"));
        logger.info(frames.size() + " - number of frames");
        Selenide.switchTo().defaultContent();
        Selenide.switchTo().frame(frames.size() - 1);
        logger.info("choosed 2nd frame");
        return this;

    }

    public void switchTo2ndFrameOf3(WebElement element) {

        webDriver.switchTo().defaultContent();
        // Thread.sleep(1000);
        webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 2);
        logger.info("Frame was changed");
        System.out.println("Frame was changed");
    }

    public void printErrorAndStopTest(Exception e) {
        logger.info("Can't work with element" + e);
        System.out.println("Can't work with element" + e);
        // Assert.fail - безусловная остановка теста
        Assert.fail("Can't work with element" + e);
    }

    public void switchToDefaultContentFromFrame(){
        try {
            Selenide.switchTo().defaultContent();
        }catch (Exception e){

            logger.info("Can't switch to default content from iframe");
            printErrorAndStopTest(e);
        }
    }

    public void waitUntilVisibilityOfAllelements(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));

    }

  /*  public void clickOnLastElementCloseBtn() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> CommunicationChannelDDList = webDriver.findElements(By.className("popup-feedback__close"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(CommunicationChannelDDList.size() + " - number of closeBtn");
        System.out.println(CommunicationChannelDDList.size() + " - number of DD");

        if (CommunicationChannelDDList.size() > 0) {
            CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 1).click();
            System.out.println("last close btn clicked");
        } else {
            //logger.info("!!! number of same elements '0'!!! ");
            System.out.println("!!! number of same elements '0'!!!");
        }
    }*/

    public void clickOnLastElementCloseBtn()  {



        List<SelenideElement> CommunicationChannelDDList = $$(".popup-feedback__close");
        logger.info(CommunicationChannelDDList.size() + " - number of closeBtn");

        try {
            CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 1).click();
            logger.info("last close btn clicked");
        }catch (Exception e){
            logger.info("can not find " + CommunicationChannelDDList);
        }

    }

    public void clickOnLastElementOfList(ElementsCollection elements, String text )  {

        //List<SelenideElement> ListOfElements = elements;
    //    ElementsCollection ListOfElements = elements;
        try {
            elements.get(elements.size() - 1).click();
            logger.info(text + "last element clicked");
        }catch (Exception e){
            logger.info("can not find " + elements);
        }

       /* elements.get(elements.size()-1).
          *//*      CommunicationChannelDDList.get(CommunicationChannelDDList.size() - 1).click();
        return $$(".mat-select-arrow-wrapper").get(2).waitUntil(Condition.appears,20000);*/



    }

    public void exitTest() {
        webDriver.quit();
    }

    public boolean isElementPresent(WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element is displayed -->" + isDisplayed);
            return element.isDisplayed();
            //return isDisplayed;
        } catch (Exception e) {
            logger.info("Element is displayed --> false");
            return false;
        }
    }


    /*public void checkExpectedResult(String message, boolean actualResult){
        Assert.assertEquals(message, true, actualResult);
    }*/

    public void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertTrue(message, actualResult);
    }

    public void switchTo1stFrameOf1(WebElement appealField) {
     /*   try {
            webDriver.switchTo().defaultContent();
            // Thread.sleep(1000);
            webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 1);
            logger.info("Frame was changed");
            //   System.out.println("Frame was changed");
        } catch (Exception e) {
            logger.info("Can't switch to frame");
            printErrorAndStopTest(e);
        }*/
        webDriver.switchTo().defaultContent();
        // Thread.sleep(1000);
        webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size() - 1);
        logger.info("Frame was changed");
        System.out.println("Frame was changed");
    }

    public void insertTextInToPeopePickerFieldUsingEnter(WebElement element, String text) {
        try {
           // Thread.sleep(2000);
            element.clear();
            element.sendKeys(text);
           // Thread.sleep(2000);
            element.sendKeys(Keys.ENTER);
            // Thread.sleep(5000);
            logger.info(text + "was input into element");
            // System.out.println(text + "was input into element");
        } catch (Exception e) {
            System.out.println("Can't enter text in to fieldExecutor" + e);
            printErrorAndStopTest(e);
        }

    }

    public Actions waitUntilAppear_15000(SelenideElement element) {
        element.waitUntil(Condition.appear,15000);
        return this;
    }

    public Actions waitUntilVisible_15000(SelenideElement element) {
        element.waitUntil(Condition.visible,15000);
        return this;
    }

    //====================== NEW ACTIONS

    /**
     * Enter the text in the web input elements
     * @param element selector of the element where a text should be entered on
     * @param text the text that should be entered in the field
     * @param fieldName the name of field that will be shown in the log
     */
    public Actions enterText(SelenideElement element, String text, String fieldName) {
        try {
            element.waitUntil(Condition.appears,10000).val(text);
            System.out.println("Text '" + text + "' inserted into " + fieldName);
        } catch (Exception e) {
            Assert.fail("Can`t insert text in field " + e);
        }

        return this;
    }

    /**
     * Web element that should be clicked on
     * @param element selector of an element
     * @param text the name of element that displays in the log
     */
    public Actions click(SelenideElement element, String text) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("Can`t click on element " + text + " " + e);
        }

        return this;
    }

    /**
     * Select option from the picket element
     * @param element element that should be selected
     */
    public Actions selectOption(SelenideElement element) {
        try {
            element.click();
            System.out.println("Element '" + element + "' selected ");
        } catch (Exception e) {
            Assert.fail("The element missing in the list" + e);
        }
        return this;
    }

    /**
     * Action for radio button
     * @param element selector to find this element
     * @param value value that should be selected
     * @param fieldName the name of field
     */
    public Actions selectRadioButton(SelenideElement element, String value, String fieldName) {
        try {
            element.click();
            System.out.println("Element '" + value + "' selected for " + fieldName);
        } catch (Exception e) {
            Assert.fail("The element missing in the list" + e);
        }
        return this;
    }


    /**
     * Select option from the dropdown menu
     * @param fieldName the name of field
     * @param element selector to find this element
     * @param itemIndex value that should be selected
     */
    public Actions dropdown(String fieldName, SelenideElement element, int itemIndex) {
        try {
            element.click();
            $(".mat-select-content").waitUntil(Condition.appear,10000).findAll(".ng-star-inserted").get(itemIndex).click();
            System.out.println("Element '" + itemIndex + "' selected for " + fieldName);
        } catch (Exception e) {
            Assert.fail("The element missing in the list" + e);
        }
        return this;
    }

    /**
     * insert a text in TinyMCE element
     * @param element selector to find this element
     * @param value value that should be selected
     * @param fieldName the name of field
     */
    public Actions enterTextInTinyMCE(SelenideElement element, String value, String fieldName) {
        try {
            Selenide.switchTo().defaultContent();
            Selenide.switchTo().frame(element);
            SelenideElement body = $(By.tagName("body"));
            Selenide.executeJavaScript("arguments[0].innerHTML=arguments[1]", body, value);
            body.click();
            Selenide.switchTo().defaultContent();
            System.out.println("The text '" + value + "' was entered into " + fieldName);
        } catch (Exception e) {
            Assert.fail("The element missing " + e);
        }
        return this;
    }

    /**
     * Select user from the list
     * @param element selector to find this element
     * @param user user to be selected
     * @param fieldName the name of field
     */
    public Actions picketUser(SelenideElement element, String user, String fieldName) {
        enterText(element, user, fieldName);
        selectOption($("mat-option").waitUntil(Condition.appear,10000));
        sleep(1000);
        return this;
    }
}
