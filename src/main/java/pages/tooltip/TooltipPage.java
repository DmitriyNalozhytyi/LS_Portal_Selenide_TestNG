package pages.tooltip;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.Table;
import constants.Button;
import constants.Fields;
import constants.Input;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;
import pages.MainPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TooltipPage {
    private final SelenideElement container = $("._b");
    private final SelenideElement btnAdd  = Button.TOOLTIP_ADD;
    private final SelenideElement btnSave = Button.TOOLTIP_SAVE;

    @Step("Check if tooltip page opened")
    public TooltipPage isPageOpens() {
        Assert.assertTrue(container.exists(), "Tooltip page");
        return this;
    }

    /**
     * Add  tooltip for specific language
     * @param language the language of tooltip
     * @param name the name of tooltip
     * @param text the text of tooltip
     */
    @Step("Add tooltip {1} for language {0}")
    public TooltipPage addTooltip(Language language, String name, String text) {
        new Actions().click(btnAdd, "Добавить");

        new TooltipDialogBox()
                .isDialogBoxOpened()
                .switchToLang(language)
                .setTextFor("Наименование", Input.TOOLTIP_NAME, name)
                .setTinyMCEText("Текст подсказки", text, Fields.ACCOMPANYING_TEXT)
                .clickButton(btnSave, "Сохранить");
        return this;
    }

    /**
     * Check if tooltip was created
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Check if tooltip was created")
    public void checkIfTooltipCreated(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        Assert.assertTrue($(withText(tooltipName)).waitUntil(Condition.appears,10000).exists(), tooltipName);
    }

    /**
     * Check if tooltip was deleted
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Check if tooltip was deleted")
    public void checkIfTooltipDeleted(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        sleep(2000);
        Assert.assertFalse($(withText(tooltipName)).exists(), tooltipName);
    }

    /**
     * Delete tooltip
     * @param language the language of the portal
     * @param tooltipName the name of tooltip
     */
    @Step("Delete tooltip {1}")
    public TooltipPage deleteTooltip(Language language, String tooltipName) {
        new MainPage().switchAppToLang(language);
        SelenideElement deleteElement = new Table().getRowByValue(tooltipName).findAll("td").get(0).findAll("button").get(1);
        deleteElement.click();
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
        SelenideElement editElement = new Table().getRowByValue(oldName).findAll("td").get(0).findAll("button").get(0);

        editElement.click();

        new TooltipDialogBox()
                .isDialogBoxOpened()
                .switchToLang(language)
                .setTextFor("Наименование", Input.TOOLTIP_NAME, newName )
                .setTinyMCEText("Текст подсказки", text, Fields.ACCOMPANYING_TEXT)
                .clickButton(btnSave, "Сохранить");
        return this;
    }
}
