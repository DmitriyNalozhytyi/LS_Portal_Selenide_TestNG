package constants;

import com.codeborne.selenide.SelenideElement;
import pages.vacancy.recruiter.RecruiterPage;

import static com.codeborne.selenide.Selenide.$;

public interface Button {
    SelenideElement ADD_RECRUITER            = $(".vacancies-header__button");
    SelenideElement SAVE_RECRUITER           = $(".vacancy-publish-button.add-dialog__button_cancel");
}
