package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Button {
    SelenideElement ADD_RECRUITER            = $(".vacancies-header__button");
    SelenideElement SAVE_RECRUITER           = $(".vacancy-publish-button.add-dialog__button_cancel");
    SelenideElement CREATE_VACANCY           = $(".vacancies-header__button");
    SelenideElement SAVE_VACANCY             = $(".vacancy-save-button");
    SelenideElement ON_APPROVAL_VACANCY      = $(".vacancy-publish-button");
    SelenideElement SAVE_AND_PUBLISH_VACANCY = $(".vacancy-publish-button");
}
