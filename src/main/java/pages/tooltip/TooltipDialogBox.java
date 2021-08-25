package pages.tooltip;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Language;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TooltipDialogBox {
    private final static SelenideElement container = $(".mat-dialog-container");

    /**
     * Close tooltip dialog box
     */
    public static SelenideElement btnClose() {
        return container.find(".mat-icon-button").waitUntil(Condition.appears, 10000);
    }

    /**
     * Find MultiLine element
     */
    public static SelenideElement fldAccompanyingText() {
        return container.find(".mce-tinymce.mce-container.mce-panel").find("iframe");
    }

    /**
     * Find RU tab on the dialog box
     */
    public static SelenideElement tabTooltipRU() {
        return container.find(".mat-tab-label-container .mat-tab-list"). findAll(".mat-tab-label").get(1);
    }

    /**
     * Verify if tooltip dialog box opened
     */
    @Step("Verify if tooltip dialog box opened")
    public TooltipDialogBox isDialogBoxOpened() {
        Assert.assertTrue(container.exists(), "Tooltip dialog box");
        return this;
    }

    /**
     * Enter the text into input
     * @param field field name
     * @param element Selenide element to find this field. The list of fields can be found in Fields.
     * @param text value to enter
     */
    @Step("Enter text {1} into the field {0}")
    public TooltipDialogBox setTextFor(String field, SelenideElement element, String text) {
        new Actions().enterText(element, text, field);
        return this;
    }

    /**
     * Enter the text into Tiny MCE editor
     * @param field field name
     * @param text the text that should be inserted
     * @param element selector of the element. For Tiny MCE editor use Fields.
     */
    @Step("Enter the text {1} into {0}")
    public TooltipDialogBox setTinyMCEText(String field, String text, SelenideElement element) {
        new Actions().enterTextInTinyMCE(element, text, field);
        return this;
    }

    /**
     *
     * @param name name of button
     * @param element Selenide element. The list of buttons there Button.
     */
    @Step("Click the button {0}")
    public void clickButton(SelenideElement element, String name) {
        new Actions().click(element, name);
    }

    public TooltipDialogBox switchToLang(Language language) {
        switch (language) {
            case RU: clickButton(tabTooltipRU(), "RU");
        }
        return this;
    }

    /**
     * Close tooltip dialog box
     */
    @Step("Close tooltip dialog box")
    public void close() {
        sleep(5000);
        if (container.exists()) {
            new Actions().click(btnClose(), "Закрыть всплывающую подсказку");
            container.waitUntil(Condition.disappears, 10000);
        }
    }
}
