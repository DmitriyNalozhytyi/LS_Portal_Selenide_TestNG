package parentTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.listeners.JSListeners;
import utils.listeners.TestListeners;

import static com.codeborne.selenide.Selenide.open;

@Listeners({TestListeners.class})
public abstract class ParentTest {
    Logger logger = Logger.getLogger(getClass());

    public static void initDriver() {

        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();

        Configuration.holdBrowserOpen=false;
   //     Configuration.headless=true;
        Configuration.browserSize = "1800x1080"; // or try "1280x1024";
        Configuration.startMaximized=false;

        System.setProperty("chromeoptions.args", "--disable-gpu");
        System.setProperty("chromeoptions.args", "--no-sandbox");
        System.setProperty("chromeoptions.args", "--disable-dev-shm-usage");
        System.setProperty("chromeoptions.args", "--incognito");
        System.setProperty("chromeoptions.args", "--start-maximized");
        Configuration.browserCapabilities.setCapability("acceptSslCerts", true);
        Configuration.browserCapabilities.setCapability("acceptInsecureCerts", true);


//        System.setProperty("chromeoptions.args", "--single-process");
//        Configuration.browserCapabilities.setCapability("--disable-gpu", true);
//        Configuration.browserCapabilities.setCapability("--no-sandbox", true);
        //        System.setProperty("--ignore-certificate-errors", "false");
    }

    @Step("Open site page and close pop-ups if they appear")
    public static void openBrowser(String url) {
        initDriver();
        open(url);
//        new JSWaiter(new WebDriverWait(getWebDriver(), 10000)).waitJS();
    }

    @BeforeClass
    public static void openSite() {
        SelenideLogger.addListener("JSErrorsListener", new JSListeners());
        openBrowser(Config.HostsData.METINVEST.value[0]);
    }

    @AfterClass
    public void tearsDown() {
        WebDriverRunner.getWebDriver().quit();
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
            if ( WebDriverRunner.getWebDriver() == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
        }
        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                WebDriverRunner.getWebDriver().quit();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };
}
