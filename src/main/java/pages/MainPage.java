package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends ParentPage {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "_link-dashboard")
    private WebElement BtnAllnews;

    public void GoToAllNews(){
        actions.click(BtnAllnews);
    }
}
