package pages.tooltip;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.PagePreLoader;
import components.Pagination;
import components.Table;
import constants.Language;
import constants.TOOLTIPS;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TooltipPage {
    private final static SelenideElement container = $("._b");
    int editElement                       = 0;
    int deleteElement                     = 1;

    public static SelenideElement btnAddTooltip() {
        return container.find(".tool-bar").find(".new_button");
    }

    public static SelenideElement btnPreviewTooltip() {
        return container.find(".tool-bar").find(".preview_button");
    }

    public static SelenideElement btnResetTooltip() {
        return container.find(".tool-bar").find(".reset_button");
    }

    private ElementsCollection tooltipMenu() {
        return $(".mat-menu-content").findAll(".custom-create-item");
    }

    private SelenideElement tooltipInfoSlide() {
        return tooltipMenu().get(0);
    }

    private SelenideElement tooltipSubscriptionSlide() {
        return tooltipMenu().get(1);
    }

    public static SelenideElement btnSaveTooltip() {
        return $(".dynamic-form-button-block").findAll("button").get(1);
    }

    public static SelenideElement inpTooltipName() {
        return $(".main-input");
    }

    private SelenideElement findTooltip(String name) {
        SelenideElement find = $(withText(name));
        do {
            new PagePreLoader().waitToLoad();
            if ($(withText(name)).isDisplayed()) {
                find = new Table().getRowByValue(name);
                break;
            }
            if (!new Pagination().isNexButtonActive()) {
                break;
            }
            new Pagination().next();
        }
        while (!new Pagination().isNexButtonActive() | !find.isDisplayed());

        return find;
    }

    private SelenideElement getActionButtonFor(String name, int action) {
        return findTooltip(name).findAll("td").get(0).findAll("button").get(action);
    }

    public enum ACTION {ADD_NEW, RESET_TOOLTIP_VIEW, PREVIEW}

    @Step("Check if tooltip page opened")
    public TooltipPage isPageOpens() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).exists(), "Tooltip page");
        return this;
    }

    /**
     * Add  tooltip for specific language
     * @param language the language of tooltip
     * @param tooltips type of tooltip: INFORMATION_SLIDE for "Информационный слайд"
     * @param name the name of tooltip
     * @param text the text of tooltip
     */
    @Step("Add tooltip {1} for language {0}")
    public TooltipPage addTooltip(Language language, TOOLTIPS tooltips, String name, String text) {
        action(ACTION.ADD_NEW);

        switch (tooltips) {
            case INFORMATION_SLIDE:
                new Actions().click(tooltipInfoSlide(), "Информационный слайд");

                new TooltipDialogBox()
                        .isDialogBoxOpened()
                        .switchToLang(language)
                        .setTextFor("Наименование", inpTooltipName(), name)
                        .setTinyMCEText("Текст подсказки", text, TooltipDialogBox.fldAccompanyingText())
                        .clickButton(btnSaveTooltip(), "Сохранить");
                break;
            case SUBSCRIPTION_SLIDE:
                new Actions().click(tooltipSubscriptionSlide(), "Информационный слайд");
                break;
        }
        return this;
    }



    /**
     * Check if tooltip was created
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Check if tooltip was created")
    public TooltipPage checkIfTooltipCreated(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        new PagePreLoader().waitToLoad();

        Assert.assertTrue(findTooltip(tooltipName).should(Condition.appear, Duration.ofSeconds(30)).isDisplayed(), tooltipName);
        return this;
    }

    /**
     * Check if tooltip was deleted
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Check if tooltip was deleted")
    public TooltipPage checkIfTooltipDeleted(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        sleep(2000);
        new PagePreLoader().waitToLoad();
        Assert.assertFalse(findTooltip(tooltipName).isDisplayed(), tooltipName);
        return this;
    }

    /**
     * Delete tooltip
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Delete tooltip {1}")
    public TooltipPage deleteTooltip(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        sleep(2000);
        getActionButtonFor(tooltipName,deleteElement).click();
        new ConfirmDialogBox().confirm(true);
        return this;
    }

    /**
     * Modify tooltip
     * @param language the language of tooltip
     * @param oldName old name of tooltip
     * @param newName new name of tooltip
     * @param text new text of tooltip
     */
    @Step("Edit tooltip {1} with new data")
    public TooltipPage editTooltip(Language language, String oldName, String newName, String text) {
        getActionButtonFor(oldName,editElement).click();

        new TooltipDialogBox()
                .isDialogBoxOpened()
                .switchToLang(language)
                .setTextFor("Наименование", inpTooltipName(), newName )
                .setTinyMCEText("Текст подсказки", text, TooltipDialogBox.fldAccompanyingText())
                .clickButton(btnSaveTooltip(), "Сохранить");
        return this;
    }

    /**
     * Delete tooltip if it exists.
     * @param tooltips type of tooltip. The list of tooltip types in the TOOLTIPS: SUBSCRIPTION_SLIDE, INFORMATION_SLIDE
     */
    @Step("Delete tooltip {0} if exists")
    public TooltipPage deleteIfExist(TOOLTIPS tooltips) {
        switch (tooltips) {
            case INFORMATION_SLIDE:
                if (findTooltip("Слайд подписок").isDisplayed()) {
                    deleteTooltip(Language.RU, "Слайд подписок");
                }
                break;
        }

        return this;
    }

    /**
     * Action that should be done on tooltip.
     * @param action the name of action. The list of actions is listed in ACTION:
     *      ADD_NEW - to add a new tooltip
     *      RESET_TOOLTIP_VIEW - to reset browsing flag
     *      PREVIEW - to preview tooltips
     */
    @Step("Click the {0}")
    public TooltipPage action(ACTION action) {
        switch (action) {
            case PREVIEW: new Actions().click(btnPreviewTooltip(), "Предпросмотр"); break;
            case ADD_NEW: new Actions().click(btnAddTooltip(), "Добавить"); break;
            case RESET_TOOLTIP_VIEW:
                new Actions().click(btnResetTooltip(), "Добавить");
                new ConfirmDialogBox().isDialogOpen().confirm(true);
                break;
        }
        return this;
    }


}