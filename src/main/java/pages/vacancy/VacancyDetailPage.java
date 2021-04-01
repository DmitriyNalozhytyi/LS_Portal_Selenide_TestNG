package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.WindowTitle;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class VacancyDetailPage {
    private final SelenideElement pageContainer = $(".vacancy");

    /*private SelenideElement pageTitle() {
        return pageContainer.find(".vacancy-header__title").waitUntil(Condition.appear,10000);
    }

    public VacancyDetailPage isPageOpens() {
        Assert.assertEquals(pageTitle.getText(),  WindowTitle.LIST_OF_RECRUITERS, WindowTitle.LIST_OF_RECRUITERS + "cannot be found" );
        return this;
    }*/
}
