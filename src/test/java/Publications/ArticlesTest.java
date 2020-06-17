package Publications;

import org.junit.Test;
import parentTest.ParentTest;

public class ArticlesTest extends ParentTest {
    @Test
    public void createNewArticle(){
        mainPage.goToAllArticles();
        allArticlesPage.clickOnRBtnNewArticle();
        allArticlesPage.ClickOnBtnCreate();
    }
}
