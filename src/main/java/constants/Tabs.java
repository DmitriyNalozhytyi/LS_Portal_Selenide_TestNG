package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Tabs {
    SelenideElement VACANCY_OPENED                  = $("#mat-tab-label-0-0");
    SelenideElement VACANCY_OPENED_ON_DP            = $("#mat-tab-label-1-0");
    SelenideElement VACANCY_ON_APPROVAL             = $("#mat-tab-label-0-1");
    SelenideElement VACANCY_DRAFT                   = $("#mat-tab-label-0-2");
    SelenideElement VACANCY_ARCHIVE                 = $("#mat-tab-label-0-3");
    SelenideElement VACANCY_RECOMMENDATIONS         = $(".mat-tab-labels").findAll(".mat-tab-label").get(1);
    SelenideElement VACANCY_SHARE_BY_EMAIL          = $("#mat-tab-label-1-1");
    SelenideElement VACANCY_DRAFT_VMP               = $("#mat-tab-label-1-2");
    SelenideElement VACANCY_ARCHIVE_VMP             = $("#mat-tab-label-1-3");
    SelenideElement TOOLTIP_RU                      = $(".mat-tab-label-container .mat-tab-list"). findAll(".mat-tab-label").get(1);
}
