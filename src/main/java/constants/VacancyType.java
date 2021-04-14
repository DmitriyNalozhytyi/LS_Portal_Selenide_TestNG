package constants;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public interface VacancyType {
    SelenideElement FOR_STAFF                   = $$("app-radio-select-field").get(0).findAll("button").get(0).waitUntil(Condition.appear,10000);
    SelenideElement FOR_ALL                     = $$("app-radio-select-field").get(0).findAll("button").get(1).waitUntil(Condition.appear,10000);
}
