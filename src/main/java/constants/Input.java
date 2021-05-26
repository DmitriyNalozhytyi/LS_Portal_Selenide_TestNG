package constants;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Input {
    /** Input element to set the name of vacancy on the create vacancy page  */
    SelenideElement VACANCY_NAME                = $(".main-input.vacancy-input.ng-valid");
    SelenideElement TOOLTIP_NAME                = $(".main-input");
}
