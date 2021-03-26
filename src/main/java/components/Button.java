package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button {
    private final WebElement parentContainer;
    private final String name;

    public Button(WebElement parentContainer, String name) {
        this.parentContainer = parentContainer;
        this.name = name;
    }

    public void click() {
        element().click();
    }

    private WebElement element() {
        return parentContainer.findElement(By.xpath("//*[text()='"+name+"']"));
    }
}
