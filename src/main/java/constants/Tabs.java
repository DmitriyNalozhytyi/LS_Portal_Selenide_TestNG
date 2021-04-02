package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Tabs {
    SelenideElement VACANCY_OPENED                  = $("#mat-tab-label-0-0");
    SelenideElement VACANCY_ON_APPROVAL             = $("#mat-tab-label-0-1");
    SelenideElement VACANCY_DRAFT                   = $("#mat-tab-label-0-2");
    SelenideElement VACANCY_ARCHIVE                 = $("#mat-tab-label-0-3");
}
