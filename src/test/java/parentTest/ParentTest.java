package parentTest;

import libs.Actions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ParentTest {

    WebDriver webDriver;
    protected AuthorizationPage authorizationPage;
    protected Actions actions;
    protected MainPage mainPage;
    protected AllNewsPage allNewsPage;
    protected NewNewsPage newNewsPage;
    protected LoremIpsum loremIpsum;


    @Before
    public void setUp(){
        File driver = new File("./src/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driver.getAbsolutePath());

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        authorizationPage = new AuthorizationPage(webDriver);
        actions = new Actions(webDriver);
        mainPage = new MainPage(webDriver);
        allNewsPage = new AllNewsPage(webDriver);
        newNewsPage = new NewNewsPage(webDriver);
        loremIpsum = new LoremIpsum(webDriver);
    }

    @After
    public void tearDown(){
        //  webDriver.quit();
    }
}
