package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class LoremIpsum extends ParentPage {
    public LoremIpsum() {

    }

    @FindBy(css = "input#generate")
    public WebElement btnGenerateLipsun;

    @FindBy(css = "#amount")
    public WebElement inputAmount;

    @FindBy(css = "#paras")
    public WebElement rbtnSelectTypeParagraphs;

    @FindBy(css = "#words")
    public WebElement rbtnSelectTypeWords;

    @FindBy(css = "#bytes")
    public WebElement rbtnSelectTypeBytes;

    @FindBy(css = "#lipsum")
    public WebElement lipsumText;



    public String getLorem(int a, int b) throws InterruptedException {         //Получаем случайный текст с сайта lipsum.com в количестве n АБЗАЦЕВ
        String lipsumcom = "window.open('https://ru.lipsum.com/','_blank');";
        ((JavascriptExecutor)webDriver).executeScript(lipsumcom);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles()); //Открываем новую вкладку браузера
        webDriver.switchTo().window(tabs.get(1)); //Переключаем фокус на новую складку
        Thread.sleep(1000); //Читаем молитву
        actions.enterText(inputAmount, "" + actions.randomNumber(a, b)); //Выбираем количество генерируемых абзацев/слов/байт/списков в диапазоне от a до b
        btnGenerateLipsun.click();
        Thread.sleep(2000);
        String lipsum = lipsumText.getText(); //Записываем полученный текст в переменную
        webDriver.close(); //Закрываем вкладку сайта lipsum.com
        webDriver.switchTo().window(tabs.get(0)); //Возвращаем фокус на исходную страницу
        return lipsum;
    }

    public String getLoremByBytes(int a, int b) throws InterruptedException {         //Получаем случайный текст с сайта lipsum.com в количестве n БАЙТ
        String lipsumcom = "window.open('https://ru.lipsum.com/','_blank');";
        ((JavascriptExecutor)webDriver).executeScript(lipsumcom);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles()); //Открываем новую вкладку браузера
        webDriver.switchTo().window(tabs.get(1)); //Переключаем фокус на новую складку
        Thread.sleep(1000); //Читаем молитву
        actions.click(rbtnSelectTypeBytes); //Выбираем количество чего мы хотим сгенерировать (абзацев, слов, байт, списков)
        actions.enterText(inputAmount, "" + actions.randomNumber(a, b)); //Выбираем количество генерируемых абзацев/слов/байт/списков в диапазоне от a до b
        btnGenerateLipsun.click();
        Thread.sleep(2000);
        String lipsum = lipsumText.getText(); //Записываем полученный текст в переменную
        webDriver.close(); //Закрываем вкладку сайта lipsum.com
        webDriver.switchTo().window(tabs.get(0)); //Возвращаем фокус на исходную страницу
        return lipsum;
    }

    public String getLoremByWords(int a, int b) throws InterruptedException {         //Получаем случайный текст с сайта lipsum.com в количестве n СЛОВ
        String lipsumcom = "window.open('https://ru.lipsum.com/','_blank');";
        ((JavascriptExecutor)webDriver).executeScript(lipsumcom);
        ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles()); //Открываем новую вкладку браузера
        webDriver.switchTo().window(tabs.get(1)); //Переключаем фокус на новую складку
        Thread.sleep(1000); //Читаем молитву
        actions.click(rbtnSelectTypeWords); //Выбираем количество чего мы хотим сгенерировать (абзацев, слов, байт, списков)
        actions.enterText(inputAmount, "" + actions.randomNumber(a, b)); //Выбираем количество генерируемых абзацев/слов/байт/списков в диапазоне от a до b
        btnGenerateLipsun.click();
        Thread.sleep(2000);
        String lipsum = lipsumText.getText(); //Записываем полученный текст в переменную
        webDriver.close(); //Закрываем вкладку сайта lipsum.com
        webDriver.switchTo().window(tabs.get(0)); //Возвращаем фокус на исходную страницу
        return lipsum;
    }
}
