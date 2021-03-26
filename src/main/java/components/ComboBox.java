package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComboBox {

    private final WebElement parentContainer;
    private final String field;

    public ComboBox(WebElement parentContainer, String field) {
        this.parentContainer = parentContainer;
        this.field = field;
    }

    private WebElement element() {
       return parentContainer.findElement(By.xpath("//*[text()='"+field+"']/parent::*/div/input"));
    }

    private WebElement item() {
        return parentContainer.findElement(By.className("modern-option__title"));
    }

    public void select(String text) {
        element().sendKeys(text);
        item().click();
    }

}
