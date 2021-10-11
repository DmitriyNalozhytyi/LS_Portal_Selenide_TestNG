package tooltips;

import constants.*;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.SubscriptionPage;
import pages.tooltip.TooltipPage;
import pages.tooltip.TooltipPopup;
import parentTest.ParentTest;

import java.util.List;

@Epic("ToolTip")
public class SubscriptionSlideTest extends ParentTest {

    @Test(description = "Check subscription on Tags while adding the slide")
    public void checkSubscriptionOnTag() {
        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);
        new MainPage().goTo(Pages.SUBSCRIPTION_SETTINGS);

        List<String> tags = new SubscriptionPage()
                .isPageOpens()
                .switchTo(SUBSCRIPTION.TAGS)
                .getAllTags();

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);

        new MainPage().goTo(Pages.TOOLTIP);

        new TooltipPage()
                .isPageOpens()
                .deleteIfExist(TOOLTIPS.INFORMATION_SLIDE)
                .checkIfTooltipDeleted(Language.RU,"Слайд подписок")
                .addTooltip(Language.RU, TOOLTIPS.SUBSCRIPTION_SLIDE,"","")
                .checkIfTooltipCreated(Language.RU,"Слайд подписок")
                .action(TooltipPage.ACTION.RESET_TOOLTIP_VIEW);

        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER14);

        new TooltipPopup()
                .isDialogOpened()
                .checkIfTagsInTheList(tags);
    }

}
