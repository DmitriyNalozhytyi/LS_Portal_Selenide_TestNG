package parentTest;

import com.thedeanda.lorem.LoremIpsum;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import libs.Actions;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.publications.news.AllNewsPage;
import pages.publications.news.CreateNewsPage;
import pages.recruiter.AddRecruiterPage;
import pages.recruiter.RecruiterPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Deprecated
public class ParentTest_OLD {

    // подключаем Logger - создаем объект, который будет писать лог

    Logger logger = Logger.getLogger(getClass());


    WebDriver webDriver;
    protected AuthorizationPage authorizationPage;
    protected Actions actions;
    protected MainPage mainPage;
    protected CreateNewsPage createNewPublicationPage;
    protected LoremIpsum loremIpsum;
    protected AllNewsPage articlesPageAll;
    protected InterviewPageAll interviewPageAll;
    protected GridSliderPageEdit gridSliderPageEdit;
    protected PhotogallaryPageAll photogallaryPageAll;
    protected PhotogallaryPageAlbum photogallaryPageAlbum;
    protected CreateNewFeedback_Page_MainModerator createNewFeedback_Page_MainModerator;
    protected ViewListOfFeedbacks_Page_MainModerator viewListOfFeedbacks_page_mainModerator;

    protected RecruiterPage recruiterPage;
    protected AddRecruiterPage addRecruiterPage;



    @Before
    public void setUp() throws InterruptedException {
        //File driver = new File("./src/drivers/chromedriver.exe");
        File driver = new File("./src/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", driver.getAbsolutePath());

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        authorizationPage = new AuthorizationPage();
        actions = new Actions();
        mainPage = new MainPage();
        createNewPublicationPage = new CreateNewsPage();
        loremIpsum = new LoremIpsum();
        articlesPageAll = new AllNewsPage();
        interviewPageAll = new InterviewPageAll();
        gridSliderPageEdit = new GridSliderPageEdit();
        photogallaryPageAll = new PhotogallaryPageAll();
        photogallaryPageAlbum = new PhotogallaryPageAlbum();
        createNewFeedback_Page_MainModerator = new CreateNewFeedback_Page_MainModerator();
        viewListOfFeedbacks_page_mainModerator = new ViewListOfFeedbacks_Page_MainModerator();


//        authorizationPage.authorization();
        //authorizationPage.authorization();

    }

    @After

    @Step
    public void tearDown()
    {
        webDriver.quit();
    }
    @Step
    protected void checkExpectedResult(String message, boolean actualResult){
        Assert.assertEquals(message, true, actualResult);
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        String fileName;
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }
        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }
        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }
        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };


}
