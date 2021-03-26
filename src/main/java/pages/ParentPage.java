package pages;

import libs.Actions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    WebDriver webDriver;
    Actions actions;
    Logger logger = Logger.getLogger(getClass());


    /*public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
//        WebDriverWait wait  = new WebDriverWait(webDriver, 60);
        //allNewsPage = new AllNewsPage(webDriver);
        PageFactory.initElements(webDriver, this);
    }
*/
    public ParentPage() {
        actions = new Actions();
    }

}
