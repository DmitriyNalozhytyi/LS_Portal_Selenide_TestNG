package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.Table;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class VacancyPage {
    private final SelenideElement pageContainer = $(".news.reuse-wrapper");
    private final SelenideElement pageTitle = pageContainer.find(".vacancies-header__title").waitUntil(Condition.appear,10000);

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
     * @param name tab name like "Черновик", "Открытые"
     * @param element  the selector of button as SelenideElement. It should be provided from Tabs.*
     */
    @Step("Switch to tab {0}")
    public VacancyPage switchTo(String name, SelenideElement element) {
        new Actions().click(element,name);
        return this;
    }

    /**
     * Check if vacancy presents in the table
     * @param vacancyName tha name of vacancy
     */
    public void checkForVacancy(String vacancyName) {
        Assert.assertEquals( new Table().getCellValue(1,1), vacancyName, vacancyName + "cannot be found");
    }
}
