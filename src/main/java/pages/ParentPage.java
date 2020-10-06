package pages;

import libs.Actions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentPage {
    WebDriver webDriver;
    Actions actions;
    Logger logger = Logger.getLogger(getClass());
  /*  MainPage mainPage;
    NewsPageAll newsPageAll;*/

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
//        WebDriverWait wait  = new WebDriverWait(webDriver, 60);
        //allNewsPage = new AllNewsPage(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
