package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class AddPublicationImage {
    File currentDirFile = new File(".");
    String projectDir = currentDirFile.getAbsolutePath() + "/src/test/testData/img/";

    private final SelenideElement pageContainer = $("._b");

    private ElementsCollection getButtons() {
        return pageContainer.find(".img-buttons").findAll(".mat-raised-button");
    }

    private SelenideElement btnSave() {
        return getButtons().get(1);
    }

    private SelenideElement fldFileUpload() {
        return pageContainer.find(byAttribute("type","file"));
    }

    public void upload(String image) {
        File file = new File(projectDir+image);
        fldFileUpload().uploadFile(file);
        btnSave().click();
    }
}
