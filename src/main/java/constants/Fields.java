package constants;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public interface Fields {
    String RECRUITER                            = "ФИО получателя:";
    String VACANCY_NAME                         = "Название вакансии";
    SelenideElement VACANCY_COMPANY             = $("#mat-select-0").scrollIntoView(true);
    SelenideElement VACANCY_CITY                = $("#mat-select-1").scrollIntoView(true);
    SelenideElement VACANCY_FUNCTION            = $("#mat-select-2").scrollIntoView(true);
    SelenideElement VACANCY_SCHEDULE            = $("#mat-select-3").scrollIntoView(true);
}
