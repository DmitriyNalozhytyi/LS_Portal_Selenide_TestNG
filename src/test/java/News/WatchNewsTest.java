package News;

import org.junit.Test;
import parentTest.ParentTest;

//@FindBy(css = "div:nth-of-type(" + Actions.randomNumber5() + ") > app-item .item-block__leading-paragraph.news_top_cont_lea > .default_link")

public class WatchNewsTest extends ParentTest {
    @Test
    public void watchNews(){
        mainPage.GoToAllNews();
        allNewsPage.selectRandomNews();
        newsPage.checkDate();
        newsPage.checkTA();
        newsPage.checkDeleteButton();
        newsPage.checkEditButton();
        newsPage.checkViews();
        newsPage.checkTitle();
        newsPage.checkImage();
        newsPage.checkDescription();
        newsPage.checkTag();
    }
}
