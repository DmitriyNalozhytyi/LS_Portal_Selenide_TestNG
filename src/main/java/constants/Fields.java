package constants;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public interface Fields {
    SelenideElement VACANCY_COMPANY                 = $("#mat-select-0");
    SelenideElement VACANCY_CITY                    = $("#mat-select-1");
    SelenideElement VACANCY_FUNCTION                = $("#mat-select-2");
    SelenideElement VACANCY_SCHEDULE                = $("#mat-select-3");
    SelenideElement JOB_APPLICANT_PHONE             = $(".form-element.form-element__first.vacancy-field").find("input");
    SelenideElement ACCOMPANYING_TEXT               = $(".mce-tinymce.mce-container.mce-panel").find("iframe");
    SelenideElement VACANCY_SHARE_EMAIL             = $(".main-input.vacancy-input");
    SelenideElement CV                              = $(".upload-element.mat-button");
    SelenideElement ACCOMPANYING_TEXT_VALIDATION    = $(".vacancy-tinymce-field");
    SelenideElement RECOMMEND_COLLEAGUE_NAME        = $$(".main-input.vacancy-input").get(0);
}
