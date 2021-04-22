package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Table {
    private final SelenideElement container = $("table");

    public String getCellValue(int row, int col) {
        return cell(row-1,col).getText();
    }

    private ElementsCollection rows() {
        return container.find("tbody").findAll("tr");
    }

    private ElementsCollection cols(int row) {
        return rows().get(row).findAll("td");
    }

    private SelenideElement cell(int row, int col) {
        return cols(row).get(col);
    }

    private SelenideElement deleteElement(String name) {
        return rows().find(Condition.matchText(name)).findAll("td").get(0);
    }

    public void delete(String name) {
        deleteElement(name).click();
    }

    public SelenideElement getElement(int row, int col) {
        return cell(row-1,col-1).waitUntil(Condition.appears, 15000);
    }

    public boolean isContentPresent() {
        return container.find("tbody").find("tr").exists();
    }
}
