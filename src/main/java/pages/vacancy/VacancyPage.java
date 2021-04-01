package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.Table;
import constants.VacancyAction;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class VacancyPage {
    private final SelenideElement pageContainer             = $(".news.reuse-wrapper");
    private final SelenideElement pageTitle                 = pageContainer.find(".vacancies-header__title").waitUntil(Condition.appear,10000);
    private final SelenideElement searchElement             = pageContainer.find(".toggle-search__submit-wrapper");
    private final SelenideElement searchInput               = pageContainer.find(".toggle-search__input");

    private SelenideElement getActions(int item) {
        return $(".mat-menu-content").findAll("button").get(item);
    }

    /**
     * Check if Vacancy management page opens
     */
    public VacancyPage isPageOpens() {
        Assert.assertEquals(pageTitle.getText(),  WindowTitle.VACANCY_MANAGEMENT, WindowTitle.VACANCY_MANAGEMENT + "cannot be found" );
        return this;
    }

    /**
     * Click the button
     * @param name the name of button as string like "Create vacancy"
     * @param element the selector of button as SelenideElement
     */
    @Step("Click the button {0}")
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element,name);
    }

    /**
     * Switch tabs on Vacancy Management page
     * @param name tab name like "Черновик", "Открытые", "На утверждении"
     * @param element  the selector of button as SelenideElement. It should be provided from Tabs.*
     */
    @Step("Switch to tab {0}")
    public VacancyPage switchTo(String name, SelenideElement element) {
        new Actions().click(element,name);
        sleep(1000);
        return this;
    }

    /**
     * Check if vacancy presents in the table
     * @param vacancyName tha name of vacancy
     */
    public void checkForVacancy(String vacancyName) {
        Assert.assertEquals( new Table().getCellValue(1,1), vacancyName, vacancyName + "cannot be found");
    }

    /**
     * Set action for vacancy
     * @param vacancyName the name of vacancy
     * @param action action name from VacancyAction list
     */
    @Step("Select {1} for {0}")
    public VacancyPage selectActionFor(String vacancyName, VacancyAction action) {
        search(vacancyName);

        switch (action) {
            case EDIT: vacancyMenu().selectAction(getActions(1));
        }
        return this;
    }

    /**
     * Open vacancy menu
     */
    @Step("Open vacancy menu")
    public VacancyPage vacancyMenu() {
        new Actions().click(new Table().getElement(1,1),"Меню действий");
        return this;
    }

    /**
     * Select action for a vacancy
     * @param action action from the list as SelenideElement
     */
    @Step
    public VacancyPage selectAction(SelenideElement action) {
        new Actions().click(action, action.getText());
        return this;
    }

    /**
     * Search vacancy on the page
     * @param text the name of vacancy to search
     */
    @Step("Search for {0}")
    public VacancyPage search(String text) {
        new Actions()
                .click(searchElement,"Поиск по вакансиям")
                .enterText(searchInput,text,"Поиск вакансий");
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }
}
