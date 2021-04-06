package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Button {
    SelenideElement ADD_RECRUITER                   = $(".vacancies-header__button");
    SelenideElement SAVE_RECRUITER                  = $(".vacancy-publish-button.add-dialog__button_cancel");
    SelenideElement CREATE_VACANCY                  = $(".vacancies-header__button");
    SelenideElement SAVE_VACANCY                    = $(".vacancy-save-button");
    SelenideElement ON_APPROVAL_VACANCY             = $(".vacancy-publish-button");
    SelenideElement SAVE_AND_PUBLISH_VACANCY        = $(".vacancy-publish-button");
    SelenideElement VACANCY_RESPOND                 = $(".vacancy-buttons__respond");
    SelenideElement APPLY_WITHOUT_RESUME            = $(".mat-slide-toggle-input").parent();
    SelenideElement AGREEMENT                       = $(".mat-checkbox-inner-container.mat-checkbox-inner-container-no-side-margin");
    SelenideElement SEND_APPLICATION                = $(".response-dialog__button.vacancy-publish-button.mat-button");
    SelenideElement VACANCY_MANAGEMENT              = $(".vacancy-nav-menu").findAll(".vacancy-nav-menu__item").get(1);
    SelenideElement VACANCY_RESPONSES               = $(".vacancy-header__button.vacancy-header__button_share");
    SelenideElement RESPONSE_DECLINE                = $(".vacancy-cancel-button.response-dialog__button.response-dialog__button_cancel.mat-button.ng-star-inserted");
    SelenideElement MISMATCHED_QUALIFICATION        = $(".reject-card__radiobuttons.mat-radio-group").findAll("mat-radio-button").get(0);
    SelenideElement RESPONSE_ON_APPROVAL            = $(".response-dialog__button.vacancy-publish-button");
    SelenideElement RESPONSE_CANDIDATE_ACCEPT       = $(".response-dialog__button.vacancy-publish-button");
}
