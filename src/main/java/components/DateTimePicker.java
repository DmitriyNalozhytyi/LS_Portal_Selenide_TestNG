package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import libs.Actions;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

/**
 * DateTimePicker - class to work with date time picker component on website
 */
public class DateTimePicker {

    private final SelenideElement container = $("owl-date-time-container");

    private SelenideElement btnPeriod() {
        return container.find(".owl-dt-control-period-button");
    }

    private SelenideElement calendar() {
        return container.find(".owl-dt-calendar-body");
    }

    private SelenideElement multiYearTable() {
        return container.find(".owl-dt-calendar-multi-year-table");
    }

    private SelenideElement multiDateTable() {
        return container.find(".owl-dt-calendar-body");
    }

    private ElementsCollection timer() {
        return container.findAll(".owl-dt-timer-input");
    }

    private SelenideElement hour() {
        return timer().get(0);
    }

    private SelenideElement minute() {
        return timer().get(1);
    }

    private ElementsCollection buttonsPanel() {
        return container.find(".owl-dt-container-buttons").findAll(".owl-dt-control.owl-dt-control-button.owl-dt-container-control-button");
    }

    private SelenideElement btnSelect() {
        return buttonsPanel().get(1);
    }

    /**
     * Set year
     * @param year string value for year should be in format YYYY
     */
    public DateTimePicker setYear(String year) {
        multiYearTable().find(withText(year)).click();
        return this;
    }

    /**
     * Set month
     * @param month string value for month should be in format MMMM
     */
    public DateTimePicker setMonth(String month) {
        calendar().find(withText(month)).click();
        return this;
    }

    /**
     * Set day
     * @param day string value for day should be in format dd
     */
    public DateTimePicker setDay(String day) {
        multiDateTable().find(withText(day)).click();
        return this;
    }

    /**
     * Set hour
     * @param hour string value for hour should be in format HH
     */
    public DateTimePicker setHour(String hour) {
        hour().setValue(hour);
        return this;
    }

    /**
     * Set minute
     * @param minute string value for minute should be in format mm
     */
    public DateTimePicker setMinute(String minute) {
        minute().setValue(minute);
        return this;
    }

    /**
     * Click the button on the data picker component
     * @param element button element that should be clicked
     * @param name the name/text of the button
     */
    public DateTimePicker clickButton(SelenideElement element, String name) {
        new Actions().click(element, name);
        return this;
    }

    /**
     * Set Date and Time
     * @param dateTime date and time in format "YYYY.MM.dd HH:mm"
     */
    public void setDate(String dateTime) {
        String[] parseDateTime  = dateTime.split(" ");
        String[] parseDate      = parseDateTime[0].split("\\.");
        String[] parseTime      = parseDateTime[1].split(":");

        String year             = parseDate[0];
        String month            = parseDate[1].substring(0,3);
        String day              = parseDate[2].substring(0,1).contains("0") ? parseDate[2].substring(1): parseDate[2];

        String hour             = parseTime[0];
        String minute           = parseTime[1];

        clickButton(btnPeriod(), "Выбрать период");
        setYear(year).setMonth(month).setDay(day);
        setHour(hour).setMinute(minute);

        clickButton(btnSelect(), "Выбрать");
    }
}
