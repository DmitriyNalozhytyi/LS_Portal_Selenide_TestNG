package utils.listeners;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import io.qameta.allure.Attachment;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class JSListeners implements LogEventListener {

    @Override
    public void afterEvent(LogEvent logEvent) {
        if (logEvent.getStatus() == LogEvent.EventStatus.FAIL) {
            LogEntries logEntries = getWebDriver().manage().logs().get(LogType.BROWSER);
            String message = "Message: \nURL of the page: " + url() + "\n";
            for (LogEntry logEntry : logEntries) {
                message = message.concat("\n" + logEntry.toString());
                System.out.println(logEntry.toString());
            }
        }
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {
    }

    @Attachment(value = "Attachment")
    public static String logRequest(String stream) {
        return stream;
    }
}
