package pages.vacancy;

import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.PagePreLoader;
import constants.Filter;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class VacancyPage {
    private final static String VACANCY                = "Вакансии";
    private final static SelenideElement pageContainer = $(".vacancies-wrapper.reuse-wrapper");

    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancies-header__title");
    }

    private SelenideElement searchInput() {
        return pageContainer.find(".main-input.vacancies-filters__search");
    }

    private SelenideElement getVacancy() {
        return pageContainer.find(".vacancy-item-header__info-title");
    }

    private VacancyPage filterDataByName(String value, String fieldName) {
        new Actions()
                .enterText(searchInput(), value, fieldName);
        searchInput().sendKeys(Keys.ENTER);
        new PagePreLoader().waitToLoad();
        return this;
    }

    public static SelenideElement btnCopyVacancy() {
        return pageContainer.find(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(0);
    }

    public static SelenideElement btnEditVacancy() {
        return pageContainer.find(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(1);
    }

    public static SelenideElement btnDeleteVacancy() {
        return pageContainer.find(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(2);
    }

    /**
     * Verify if page opens
     */
    @Step("Verify if page opens")
    public VacancyPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(pageTitle().getText(), VACANCY, "The title");
        return this;
    }

    /**
     * Filter vacancy by attributes
     * @param attribute filter attribute. The list of attributes can be found in Filter.
     * @param value the value criteria
     * @param field field name
     */
    @Step("Select value {1} for filter {2}")
    public VacancyPage filterBy(Filter attribute, String value, String field) {
        switch (attribute) {
            case NAME: filterDataByName(value, field);
        }
        return this;
    }

    /**
     * Open vacancy detail page
     * @param name name of the vacancy
     */
    @Step("Open details of {0}")
    public void openVacancyDetails(String name) {
        new Actions().click(getVacancy(), name);
    }

    public void copyVacancy() {
        new Actions().click(btnCopyVacancy(), "Копировать Вакансию");
    }

    public void editVacancy() {
        new Actions().click(btnEditVacancy(), "Редактровать Вакансию");
    }

    public void checkForVacancy(String vacancyName) {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(getVacancy().getText(), vacancyName, "The vacancy");
    }

    public VacancyPage deleteVacancy() {
        new Actions().click(btnDeleteVacancy(), "Редактровать Вакансию");
        new ConfirmDialogBox().confirm(true);
        return this;
    }

    public void checkForVacancyAbsence() {
        new PagePreLoader().waitToLoad();
        Assert.assertFalse(getVacancy().exists(), "The vacancy");
    }
}
