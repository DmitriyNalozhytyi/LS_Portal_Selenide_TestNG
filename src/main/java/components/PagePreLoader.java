package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class to detect and wait when page pre loader finished
 */
public class PagePreLoader {
    private final SelenideElement container = $(".spinner-overlay.preloader");

    public void waitToLoad() {
        container.waitUntil(Condition.disappears,60000);
    }
}
