package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class VacancyEdit {
    private final SelenideElement pageContainer             = $(".news.reuse-wrapper.mat-card");

    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__title").waitUntil(Condition.appear,10000);
    }

    /**
     * Check if page opens
     */
    public VacancyEdit isPageOpens() {
        Assert.assertEquals(pageTitle().getText(),  WindowTitle.VACANCY_EDIT, WindowTitle.VACANCY_EDIT + "cannot be found" );
        return this;
    }

    /**
     * Change the status of vacancy
     * @param fieldName the name of field
     * @param value the status like "Открытая"
     * @param element the selector as SelenideElement
     */
    @Step("Approve and publish the vacancy")
    public VacancyEdit changeStatus(String fieldName, String value, SelenideElement element) {
        new Actions().selectRadio(element,value,fieldName);
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
