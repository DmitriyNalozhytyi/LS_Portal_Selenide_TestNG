package pages.tooltip;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class TooltipPopup {
    private final SelenideElement container = $("app-tips-slider");

    private ElementsCollection tagItems() {
        return container.find(".popup-items").findAll(".mat-chip-list-wrapper");
    }

    /**
     * Find container which contains selected tags
     * @return Selenide element of the selected tags container
     */
    private SelenideElement getSelectedTagContainer() {
        return tagItems().get(0);
    }

    /**
     * Checks if tooltip pop-up opened
     */
    @Step("Check if tooltip popup appears")
    public TooltipPopup isDialogOpened() {
        Assert.assertTrue(container.should(Condition.appear, Duration.ofSeconds(60)).isDisplayed(), "Tooltip popup");
        return this;
    }

    /**
     * Check if provided list of <b><i>tags</i></b> presents on the pop-up in the selected tags section
     * @param tags list of Tags to compare with
     */
    @Step("Check if tags {0} in the present")
    public void checkIfTagsInTheList(List<String> tags) {
        List<String> selectedTags = getSelectedTagContainer().findAll("mat-chip").texts();
        Collections.sort(tags);
        Collections.sort(selectedTags);
        Assert.assertEquals(tags, selectedTags, "tags " + tags);
    }
}
