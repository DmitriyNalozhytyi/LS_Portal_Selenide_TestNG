package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.PagePreLoader;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VacancyEditPage extends CreateVacancyPage{
    private final static String VACANCY_EDIT                       = "Редактирование вакансии";
    
    private final static SelenideElement pageContainer             = $(".news.reuse-wrapper.mat-card");

    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__title").waitUntil(Condition.appears,10000);
    }

    private SelenideElement getStatusButton(int status) {
        return $$("app-radio-select-field").get(0).findAll("button").get(status).waitUntil(Condition.appear,10000);
    }

    public static SelenideElement btnSaveDraftVacancy() {
        return pageContainer.findAll("..dynamic-form-button.mat-button.vacancy-publish-button").get(0);
    }

    /**
     * Check if page opens
     */
    public VacancyEditPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(pageTitle().getText(),  VACANCY_EDIT, VACANCY_EDIT + "cannot be found" );
        return this;
    }

    /**
     * Change the status of vacancy
     * @param fieldName the name of field
     * @param value the status like "Открытая"
     * @param status the status of a vacancy SUSPENDED, CLOSE, CANCEL
     */
    @Step("Approve and publish the vacancy")
    public VacancyEditPage changeStatus(String fieldName, String value, int status) {
        new Actions().selectRadioButton(getStatusButton(status),value,fieldName);
        return this;
    }

    /**
     * Click the button
     * @param name the name of button as string like "Save vacancy"
     * @param element the selector of button as SelenideElement
     */
    @Step("Click the button {0}")
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element,name);
    }
}
