package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
}
