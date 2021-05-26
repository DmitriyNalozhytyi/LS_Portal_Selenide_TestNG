package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class to detect and wait when page pre loader finished
 */
public class PagePreLoader {
    private final SelenideElement container = $(".spinner-overlay.preloader");
    private final SelenideElement container1 = $(".loading-shade.mat-spinner");

    public void waitToLoad() {
        if(container.exists()) {
            container.waitUntil(Condition.disappears, 60000);
        } else if (container1.exists()) {
            container1.waitUntil(Condition.disappears, 60000);
        }
    }
}
//loading-shade