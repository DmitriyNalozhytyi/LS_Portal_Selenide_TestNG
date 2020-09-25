package mainPage;

import org.junit.Test;
import parentTest.ParentTest;

public class GridSliderTest extends ParentTest {
    @Test
    public void EditGridSlider() throws InterruptedException {
        mainPage.goToGridSliderEdit();
        gridSliderPageEdit.deleteRandomBlock();
        gridSliderPageEdit.addNewColorBlock();
        gridSliderPageEdit.writeTitle("ATest " + actions.currentTime());
        gridSliderPageEdit.writeDescription(loremIpsum.getLoremByBytes(50, 200));
        gridSliderPageEdit.writeLink("https://google.com");
        gridSliderPageEdit.selectRandomIcon();
        gridSliderPageEdit.selectColor();
        gridSliderPageEdit.clickSaveBlock();
        gridSliderPageEdit.clickSaveGrid();
    }

    @Test
    public void addBlockWithImage() throws InterruptedException {
        mainPage.goToGridSliderEdit();
        gridSliderPageEdit.deleteRandomBlock();
        gridSliderPageEdit.addNewBlockWithImage();
        gridSliderPageEdit.writeTitle("ATest " + actions.currentTime());
        gridSliderPageEdit.writeDescription(loremIpsum.getLoremByBytes(50, 200));
        gridSliderPageEdit.writeLink("https://google.com");
        gridSliderPageEdit.addImage();
        gridSliderPageEdit.clickSaveBlock();
        gridSliderPageEdit.clickSaveGrid();
    }
}
