package constants;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public interface EmploymentType {
    SelenideElement PART_TIME           = $$(".radio-field-wrapper").get(2).findAll("button").get(1).waitUntil(Condition.appear,10000);
}
