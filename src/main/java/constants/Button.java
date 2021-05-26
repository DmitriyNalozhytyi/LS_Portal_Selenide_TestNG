package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public interface Button {
    SelenideElement ADD_RECRUITER                   = $(".vacancies-header__button");
    SelenideElement SAVE_RECRUITER                  = $(".vacancy-publish-button.add-dialog__button_cancel");
    SelenideElement CREATE_VACANCY                  = $(".vacancies-header__button");
    SelenideElement SAVE_VACANCY                    = $(".vacancy-save-button");
    SelenideElement SAVE_DRAFT_VACANCY              = $$(".dynamic-form-button.mat-button.vacancy-publish-button").get(0);
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
    SelenideElement RECOMMEND_COLLEAGUE             = $(".vacancy-buttons__recommend.vacancy-save-button.mat-button");
    SelenideElement SEND_RECOMMENDATION             = $(".recommend-dialog__button.vacancy-publish-button.mat-button");
    SelenideElement VACANCY_SHARE_VMP               = $$(".vacancy-header__button.vacancy-header__button_share").get(1);
    SelenideElement VACANCY_SHARE                   = $(".vacancy-header__button.vacancy-header__button_share");
    SelenideElement VACANCY_SEND                    = $(".dynamic-form-button.mat-button.vacancy-publish-button");
    SelenideElement RESPONSE_SHARE                  = $$(".vacancy-cancel-button.response-dialog__button").get(0);
    SelenideElement VACANCY_APPLICATION_CANCEL      = $(".vacancy-cancel-button.response-dialog__button.response-dialog__button_cancel");
    SelenideElement VACANCY_RECOMMENDATION_CANCEL   = $(".vacancy-cancel-button.recommend-dialog__button.recommend-dialog__button_cancel");
    SelenideElement COPY_VACANCY_ON_VP              = $(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(0);
    SelenideElement EDIT_VACANCY_ON_VP              = $(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(1);
    SelenideElement DELETE_VACANCY_ON_VP            = $(".vacancy-item-header__info-title").findAll(".icon.icon-frame").get(2);
    SelenideElement COPY_VACANCY_ON_VDP             = $(".vacancy-header__info-title").findAll(".icon.icon-frame").get(0);
    SelenideElement EDIT_VACANCY_ON_VDP             = $(".vacancy-header__info-title").findAll(".icon.icon-frame").get(1);
    SelenideElement DELETE_VACANCY_ON_VDP           = $(".vacancy-header__info-title").findAll(".icon.icon-frame").get(2);
    SelenideElement DELETE_CLOSED_VACANCY_ON_VDP    = $(".vacancy-header__info-title").findAll(".icon.icon-frame").get(1);
    SelenideElement TOOLTIP_ADD                     = $(".tool-bar").find(".new_button");
    SelenideElement TOOLTIP_SAVE                    = $(".dynamic-form-button-block").findAll("button").get(1);
    SelenideElement PORTAL_LANGUAGE_RU              = $(".language-list").findAll(".language-option").get(1);
}