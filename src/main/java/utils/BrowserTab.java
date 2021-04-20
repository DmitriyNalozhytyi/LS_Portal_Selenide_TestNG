package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BrowserTab {
    ArrayList<String> tabs;

    public BrowserTab() {
        this.tabs = new ArrayList<>(getWebDriver().getWindowHandles());
    }

    @Step("Open a new tab")
    public void openNewTab() {
        ((JavascriptExecutor) getWebDriver()).executeScript("window.open()", "", "width=1800,height=1080");
        this.tabs = new ArrayList<>(getWebDriver().getWindowHandles());
    }

    /**
     * Switch to tab
     * @param tabNumber tab position on UI
     */
    @Step("Switch to {0}")
    public void switchTo(int tabNumber) {
        Selenide.switchTo().window(tabs.get(tabNumber));
    }
}
