package pages.vacancy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.PagePreLoader;
import components.Table;
import constants.*;
import io.qameta.allure.Step;
import libs.Actions;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.AuthorizationPage;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class VacancyManagementPage {
    private static final String VACANCY_MANAGEMENT           = "Управление вакансиями";

    private static final SelenideElement pageContainer      = $(".news.reuse-wrapper");
    private final SelenideElement pageTitle                 = pageContainer.find(".vacancies-header__title").should(Condition.appear, Duration.ofSeconds(30));
    private final SelenideElement searchElement             = pageContainer.find(".toggle-search__submit-wrapper");
    private final SelenideElement searchInput               = pageContainer.find(".toggle-search__input");

    private SelenideElement getActions(int item) {
        return $(".mat-menu-content").findAll("button").get(item);
    }

    public static SelenideElement tbVacancyArchive() {
        return pageContainer.find("#mat-tab-label-0-3").should(Condition.appear,Duration.ofSeconds(10));
    }

    public static SelenideElement tbVacancyArchive_VMP() {
        return pageContainer.find("#mat-tab-label-1-3").should(Condition.appear,Duration.ofSeconds(10));
    }

    public static SelenideElement tbVacancyOnApproval() {
        return $("#mat-tab-label-0-1").should(Condition.appear,Duration.ofSeconds(10));
    }

    public static SelenideElement tbVacancyOpened() {
        return pageContainer.find("#mat-tab-label-0-0").should(Condition.appear,Duration.ofSeconds(10));
    }

    public static SelenideElement tbVacancyOpened_VMP() {
        return pageContainer.find("#mat-tab-label-1-0").should(Condition.appear,Duration.ofSeconds(10));
    }

    public static SelenideElement tbVacancyDraft() {
        SelenideElement tabVD = $("");
        if (pageContainer.find("#mat-tab-label-0-2").exists()) {
            tabVD =  pageContainer.find("#mat-tab-label-0-2");
        } else if (pageContainer.find("#mat-tab-label-1-2").exists()) {
            tabVD = pageContainer.find("#mat-tab-label-1-2");
        }
        return tabVD;
    }

    public static SelenideElement btnCreateVacancy() {
        return pageContainer.find(".vacancies-header__button");
    }

    /**
     * Check if Vacancy Management page opens
     */
    @Step("Verify that Vacancy Management page opens")
    public VacancyManagementPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(pageTitle.getText(),  VACANCY_MANAGEMENT, "The page title" );
        return this;
    }

    /**
     * Click the button
     * @param name the name of button as string like "Create vacancy"
     * @param element the selector of button as SelenideElement
     */
    @Step("Click the button {0}")
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element,name);
    }

    /**
     * Switch tabs on Vacancy Management page
     * @param name tab name like "Черновик", "Открытые", "На утверждении"
     * @param element  the selector of button as SelenideElement. It should be provided from Tabs.*
     */
    @Step("Switch to tab {0}")
    public VacancyManagementPage switchTo(String name, SelenideElement element) {
        new Actions().click(element,name);
        sleep(1000);
        return this;
    }

    /**
     * Check if vacancy presents in the table
     * @param vacancyName tha name of vacancy
     */
    public VacancyManagementPage checkForVacancy(String vacancyName) {
        sleep(2000);
        Assert.assertEquals( new Table().getCellValue(1,1), vacancyName, "The name of vacancy");
        return this;
    }

    public void checkForVacancyAbsence() {
        sleep(2000);
        Assert.assertFalse( new Table().isContentPresent(), "The name of vacancy");
    }

    /**
     * Select action for vacancy like delete, copy, edit
     * @param vacancyName the name of vacancy
     * @param action name of action. The list of actions can be found in VacancyAction
     */
    @Step("Select {1} for {0}")
    public VacancyManagementPage selectActionFor(String vacancyName, VacancyAction action) {
        search(vacancyName);
        sleep(3000);

        switch (action) {
            case COPY   : vacancyMenu().selectAction(getActions(1)); break;
            case EDIT   : vacancyMenu().selectAction(getActions(0)); break;
            case DELETE :
                vacancyMenu().selectAction(getActions(2));
                new ConfirmDialogBox().isDialogOpen().confirm(true); break;
            case DELETE_CLOSED_VACANCY:
                vacancyMenu().selectAction(getActions(1));
                new ConfirmDialogBox().isDialogOpen().confirm(true); break;
        }
        return this;
    }

    /**
     * Open vacancy menu. It is tree vertical dots in the beginning of the records of the table
     */
    @Step("Open vacancy menu")
    public VacancyManagementPage vacancyMenu() {
        new Actions().click(new Table().getElement(1,1),"Меню действий");
        return this;
    }

    /**
     * Select action from the vacancy menu
     * @param action action from the list as SelenideElement
     */
    @Step
    public VacancyManagementPage selectAction(SelenideElement action) {
        new Actions().click(action, action.getText());
        return this;
    }

    /**
     * Search vacancy on the page
     * @param text the name of vacancy to search
     */
    @Step("Search for {0}")
    public VacancyManagementPage search(String text) {
        new Actions()
                .click(searchElement,"Поиск по вакансиям")
                .enterText(searchInput,text,"Поиск вакансий");
        searchInput.sendKeys(Keys.ENTER);
        new PagePreLoader().waitToLoad();
        new Actions()
                .click(searchElement,"Поиск по вакансиям");
        return this;
    }

    /**
     * Create and approve a vacancy. This method can be used in case to check issues connected to a vacancy like sharing, job application, recommendation and so on.
     * @param admin admin account who approve the vacancy. The list of users can be found in USERS
     * @param vacancyName the name of vacancy
     */
    @Step("Create and approve the vacancy {1}")
    public VacancyManagementPage createAndApproveVacancy(USERS admin, String vacancyName) {

        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для всех", CreateVacancyPage.btnForAll())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .clickButton("На утверждение", CreateVacancyPage.btnOnApprovalVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .search(vacancyName)
                .checkForVacancy(vacancyName);


        new AuthorizationPage().loginAs(admin);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .changeStatus("Статус", "Открытая", VacancyStatus.OPEN)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        return this;
    }

    public VacancyManagementPage createVacancyASSupervisor(String vacancyName) {

        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город",CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить и опубликовать", CreateVacancyPage.btnSaveAndPublishVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        return this;
    }

    public VacancyManagementPage createVacancyAsRecruiter(String vacancyName) {
        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для всех", CreateVacancyPage.btnForAll())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .clickButton("На утверждение", CreateVacancyPage.btnOnApprovalVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("На утверждении", VacancyManagementPage.tbVacancyOnApproval())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        return this;
    }

    public VacancyManagementPage createDraftVacancyAsSupervisor(String vacancyName) {

        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить и опубликовать", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновики", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyName)
                .checkForVacancy(vacancyName);
        return this;
    }

    public VacancyManagementPage createDraftVacancyAsRecruiter(String vacancyName) {

        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы",CreateVacancyPage.ddSchedule(), 1)
                .clickButton("Сохранить и опубликовать", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Черновики", VacancyManagementPage.tbVacancyDraft())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        return this;
    }

    public VacancyManagementPage createVacancyForArchiveASSupervisor(String vacancyName, String statusName, int status) {
        clickButton("Создать вакансию", VacancyManagementPage.btnCreateVacancy());

        new CreateVacancyPage()
                .isCreateVacancyPage()
                .setTextFor("Название вакансии", CreateVacancyPage.inpVacancyName(), vacancyName)
                .setValueFor("Тип вакансии", "Для сотрудников", CreateVacancyPage.btnForStaff())
                .selectFor("Предприятие", CreateVacancyPage.ddCompany(), 1)
                .selectFor("Город", CreateVacancyPage.ddCity(), 1)
                .setValueFor("Уровень позиции", "N-1", CreateVacancyPage.btnLevelPosition_N1())
                .setValueFor("Тип занятости", "Частичная занятость", CreateVacancyPage.btnEmployment_PartTime())
                .selectFor("Функция", CreateVacancyPage.ddFunction(), 1)
                .selectFor("График работы", CreateVacancyPage.ddSchedule(), 1)
                .selectResponsibleForSW(USERS.DEV_TESTUSER15, Data.RECRUITER_2)
                .clickButton("Сохранить и опубликовать", CreateVacancyPage.btnSaveAndPublishVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .changeStatus("Статус", statusName, status)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", VacancyManagementPage.tbVacancyArchive())
                .search(vacancyName)
                .checkForVacancy(vacancyName);

        return this;
    }

    public VacancyManagementPage createVacancyForArchiveASRecruiter(String vacancyName, String statusName, int status) {

        createAndApproveVacancy(USERS.DEV_TESTUSER15, vacancyName);

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Открытые", VacancyManagementPage.tbVacancyOpened())
                .search(vacancyName)
                .checkForVacancy(vacancyName)
                .selectActionFor(vacancyName, VacancyAction.EDIT);

        new VacancyEditPage()
                .isPageOpens()
                .changeStatus("Статус", statusName, status)
                .clickButton("Сохранить", CreateVacancyPage.btnSaveVacancy());

        new MainPage().goTo(Pages.VACANCY_MANAGEMENT);

        new VacancyManagementPage()
                .isPageOpens()
                .switchTo("Архив", VacancyManagementPage.tbVacancyArchive())
                .search(vacancyName)
                .checkForVacancy(vacancyName);
        return this;
    }

    /**
     * It opens the vacancy page details. It is not required to call search() method in the code as it included in this method
     * @param name the name of vacancy
     */
    @Step("Select {0} to open vacancy page details")
    public void openVacancyDetails(String name) {
        search(name);
        new Table().getElement(1,2).click();
    }



}
