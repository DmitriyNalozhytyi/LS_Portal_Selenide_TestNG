package tooltips;

import constants.Language;
import constants.SiteMenu;
import constants.USERS;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.tooltip.TooltipPage;
import parentTest.ParentTest;
import utils.CustomRandom;

@Epic("Tooltip")
public class ManageTooltipTest extends ParentTest {
    private final String tooltipName            = "TOOLTIP_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String tooltipModifiedName    = "TOOLTIP_MODIFIED_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create Tooltip")
    public void createTooltip() {

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);

        new MainPage().goTo(SiteMenu.TOOLTIP);

        new TooltipPage()
                .isPageOpens()
                .addTooltip(Language.RU, tooltipName, CustomRandom.getText(500))
                .checkIfTooltipCreated(Language.RU,tooltipName);
    }

    @Test(description = "Edit Tooltip", dependsOnMethods = "createTooltip")
    public void editTooltip() {

        new TooltipPage()
                .isPageOpens()
                .editTooltip(Language.RU, tooltipName, tooltipModifiedName, CustomRandom.getText(500))
                .checkIfTooltipCreated(Language.RU,tooltipModifiedName);
    }

    @Test(description = "Delete Tooltip", dependsOnMethods = "editTooltip")
    public void deleteTooltip() {

        new TooltipPage()
                .isPageOpens()
                .deleteTooltip(Language.RU, tooltipModifiedName)
                .checkIfTooltipDeleted(Language.RU,tooltipModifiedName);
    }

}
