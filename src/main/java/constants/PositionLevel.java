package constants;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public interface PositionLevel {
    SelenideElement N_1                 = $$(".radio-field-wrapper").get(1).findAll("button").get(0).waitUntil(Condition.appear,10000);
}
