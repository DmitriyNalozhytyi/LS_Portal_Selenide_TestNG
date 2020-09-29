package pages;

import libs.Actions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    WebDriver webDriver;
    Actions actions;
    Logger logger = Logger.getLogger(getClass());
  /*  MainPage mainPage;
    NewsPageAll newsPageAll;*/

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        //allNewsPage = new AllNewsPage(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
