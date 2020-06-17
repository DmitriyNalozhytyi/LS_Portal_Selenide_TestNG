package pages;

import libs.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    WebDriver webDriver;
    Actions actions;
    MainPage mainPage;
    AllNewsPage allNewsPage;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        //allNewsPage = new AllNewsPage(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
