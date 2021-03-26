package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Input {
    private final WebElement parentContainer;
    private final String field;

    public Input(WebElement parentContainer, String field) {
        this.parentContainer = parentContainer;
        this.field = field;
    }

    private WebElement element() {
        return parentContainer.findElement(By.xpath("//*[text()='"+field+"']/parent::*/div/input"));
    }

    public void enter(String text) {
        element().sendKeys(text);
    }


}
