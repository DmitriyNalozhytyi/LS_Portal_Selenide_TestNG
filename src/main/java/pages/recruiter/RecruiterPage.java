package pages.recruiter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import components.ConfirmDialogBox;
import components.MessageDialogBox;
import components.PagePreLoader;
import components.Table;
import io.qameta.allure.Step;
import libs.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RecruiterPage {
    private static final String LIST_OF_RECRUITERS           = "Список рекрутеров";

    private final SelenideElement pageTitle = $(".vacancies-header__title").waitUntil(Condition.appear,10000);
    private final SelenideElement pageHeaderContainer = $(".vacancies-header");


    public static SelenideElement btnAddRecruiter() {
        return $(".vacancies-header__button");
    }

    /**
     * Check if recruiter page opens
     */
    public RecruiterPage isPageOpens() {
        new PagePreLoader().waitToLoad();
        Assert.assertEquals(pageTitle.getText(),  LIST_OF_RECRUITERS, LIST_OF_RECRUITERS + "cannot be found" );
        return this;
    }

    /**
     * Click the button on the form
     * @param name the name of the button
     * @param element selector to find this button in DOM
     */
    @Step("Click the button {0}")
    public void clickButton(String name, SelenideElement element) {
        new Actions().click(element, name);
    }

    /**
     * Check if recruiter with the name presents in the table
     * @param name the name of recruiter
     */
    @Step ("Check if recruiter present in the table")
    public void checkForRecruiter(String name) {
        sleep(5000);
        Assert.assertEquals( new Table().getCellValue(1,2), name, name + "cannot be found");
    }

    /**
     * Delete recruiter by name
     * @param name the name of recruiter
     */
    @Step("Delete recruiter {0}")
    public RecruiterPage delete(String name) {
        new Table().delete(name);
        new ConfirmDialogBox()
                .isDialogOpen()
                .confirm(true);
        return this;
    }

    /**
     * Close pop-up window
     */
    @Step("Close popup")
    public RecruiterPage closePopUp() {
        new MessageDialogBox().close();
        return this;
    }

    /**
     * Check for message in pop-up
     * @param text the text that should be compared with
     */
    public RecruiterPage checkPopUpMessage(String text) {
        Assert.assertEquals( new MessageDialogBox().getMessage(), text, text + " cannot be found");
        return this;
    }

    /**
     * Check that recruiter with the name is not present in the table
     * @param name the name of recruiter
     */
    public void checkIfRecruiterDeleted(String name) {
        Assert.assertNotEquals(new Table().getCellValue(1,2), name, "");
    }
}
