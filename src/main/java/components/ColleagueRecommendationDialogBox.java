package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class ColleagueRecommendationDialogBox {
    private final static String RECOMMEND_COLLEAGUE_PAGE_TITLE   = "Рекомендовать коллегу";
    private final SelenideElement container                      = $(".recommend-dialog");

    private SelenideElement colleagueContainer() {
        return container.findAll(".main-input.vacancy-input").get(0);
    }

    private SelenideElement pageTitle() {
        return container.find(".recommend-dialog__title").waitUntil(Condition.appears,10000);
    }

    public ColleagueRecommendationDialogBox selectColleague(String name) {
        new Actions().picketUser(colleagueContainer(), name, "Введите Ф.И.О. руководителя");
        return this;
    }

    public ColleagueRecommendationDialogBox isPageOpens() {
        Assert.assertEquals(pageTitle().getText(), RECOMMEND_COLLEAGUE_PAGE_TITLE, "The title of the page" );
        return this;
    }

    public ColleagueRecommendationDialogBox setValueFor(String field, String value, SelenideElement element) {
        new Actions().enterText(element, value, field);
        return this;
    }

    public ColleagueRecommendationDialogBox setTextInMultiLine(String field, String value, SelenideElement element) {
        new Actions().enterTextInTinyMCE(element, value, field);
        return this;
    }

    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element,name);
    }
}
