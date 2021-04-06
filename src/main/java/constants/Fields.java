package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public interface Fields {
    String RECRUITER                            = "ФИО получателя:";
    String VACANCY_NAME                         = "Название вакансии";
    SelenideElement VACANCY_COMPANY             = $("#mat-select-0");
    SelenideElement VACANCY_CITY                = $("#mat-select-1");
    SelenideElement VACANCY_FUNCTION            = $("#mat-select-2");
    SelenideElement VACANCY_SCHEDULE            = $("#mat-select-3");
    SelenideElement JOB_APPLICANT_PHONE         = $(".main-input.vacancy-input.ng-pristine.ng-invalid");
    SelenideElement ACCOMPANYING_TEXT           = $("#mceu_2").find("iframe");
}
