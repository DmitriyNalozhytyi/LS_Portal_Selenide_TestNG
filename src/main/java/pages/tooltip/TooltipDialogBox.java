package pages.tooltip;

import com.codeborne.selenide.SelenideElement;
import constants.Language;
import constants.Tabs;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class TooltipDialogBox {
    private final static SelenideElement container = $(".mat-dialog-container");

    public TooltipDialogBox isDialogBoxOpened() {
        Assert.assertTrue(container.exists(), "Tooltip dialog box");
        return this;
    }

    public static SelenideElement fldAccompanyingText() {
        return container.find(".mce-tinymce.mce-container.mce-panel").find("iframe");
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
            case RU: clickButton(Tabs.TOOLTIP_RU, "RU");
        }
        return this;
    }
}
