package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.USERS;
import constants.WindowTitle;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class CreateVacancyPage {
    private final SelenideElement pageContainer = $(".news.reuse-wrapper.mat-card");
    private final SelenideElement inptResponce = $(".main-input.vacancy-input.ng-pristine.ng-invalid");

    private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__title").waitUntil(Condition.appear,10000);
    }

    private SelenideElement getOptionToSelect() {
        return $("#mat-autocomplete-1").find(byAttribute("tabindex","0")).waitUntil(Condition.appear,10000);
    }

    /**
     * Check if page to create a vacancy opens
     */
    public CreateVacancyPage isCreateVacancyPage() {
        Assert.assertEquals(pageTitle().getText(),  WindowTitle.NEW_VACANCY_PAGE, WindowTitle.NEW_VACANCY_PAGE + "cannot be found" );
        return this;
    }

    /**
     * Enter text in the input field
     * @param fieldName the name of field that should be displayed in allure report
     * @param element selector to find input field
     * @param text text that should be entered in the input field
     */
    @Step("Enter the text '{2}' into the field '{0}'")
    public CreateVacancyPage setTextFor(String fieldName, SelenideElement element, String text) {
        new Actions().enterText(element, text, fieldName);
        return this;
    }

    /**
     * Select value for radio button element
     * @param fieldName the name of field
     * @param value the value that should be selected
     * @param element selector to find this element
     */
    @Step("Select {1} for {0}")
    public CreateVacancyPage setValueFor(String fieldName, String value, SelenideElement element) {
        new Actions().selectRadio(element,value,fieldName);
        return this;
    }

    /**
     * Work with dropdown element
     * @param fieldName the name of the field
     * @param value the value that should be selected
     * @param element selector to find this element
     */
    @Step("Select {1} for {0}")
    public CreateVacancyPage selectFor(String fieldName, String value, SelenideElement element) {
        new Actions().dropdown(fieldName, value, element);
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

    /**
     * Set responsible person. Only for superviser or admin
     * @param user set the user
     * @param responsibleUser  the user who is response for hire
     */
    @Step("Select responsible user {1}")
    public CreateVacancyPage selectResponsibleForSW(USERS user, String responsibleUser) {
        if (user.equals(USERS.DEV_TESTUSER15)) {
            new Actions()
                    .enterText(inptResponce, responsibleUser, "Введите Ф.И.О. руководителя")
                    .selectOption(getOptionToSelect());
            sleep(1000);
        }
        return this;
    }
}
