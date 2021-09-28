package publications.create;


import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import constants.*;
import org.testng.annotations.Test;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.publications.PublicationViewPage;
import pages.publications.news.AllNewsPage;
import pages.publications.news.CreateNewsPage;
import parentTest.ParentTest;
import utils.CustomRandom;
import utils.DateTime;

public class AnnouncementTest extends ParentTest
{
    Lorem lorem = LoremIpsum.getInstance();

    private final String announcementNameRU      = "ANNOUNCEMENT_RU_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);
    private final String announcementNameUA      = "ANNOUNCEMENT_UA_" + CustomRandom.getText(CustomRandom.ALPHABET_UPPER_CASE,5);

    @Test(description = "Create a new Announcement")
    public void createNews() {


        new AuthorizationPage().loginAs(USERS.DEV_TESTUSER4);
        new MainPage().goTo(Pages.NEWS);

        new AllNewsPage()
                .isPageOpened(Language.RU)
                .openCreatePublicationPage(PUBLICATION.ANNOUNCEMENT);

        new CreateNewsPage()
                .isPageOpens()
                .switchTo(Language.RU)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), announcementNameRU)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .switchTo(Language.UA)
                .setTextFor("Заголовок", CreateNewsPage.inpTitleField(), announcementNameUA)
                .setTinyMCEText("Описание новости", lorem.getWords(25), CreateNewsPage.inpDescriptionField())
                .addImageToSlider()
                .selectContentType(CONTENT_TYPE.PHOTO)
                .setDateTimeFor("Дата и время публикации", CreateNewsPage.dtStartPublication(), DateTime.getDate("YYYY.MMMM.dd HH:mm", 0, "ru"))
                .setDateTimeFor("Целевая аудитория", CreateNewsPage.dtStartPublication(), DateTime.getDate("YYYY.MMMM.dd HH:mm", 3, "ru"))
                .selectValueFor("Целевая аудитория", CreateNewsPage.ddTargetAudience(), 1)
                .enterTag("#ATest1", "#ATest2")
                .clickButton(CreateNewsPage.btnSaveAndPublish(), "Сохранить и опубликовать");

        new PublicationViewPage()
                .isPageOpened()
                .isPublicationPresent(Language.RU, announcementNameRU)
                .isPublicationPresent(Language.UA, announcementNameUA)
                .clickButton("Return to the All News Page", PublicationViewPage.btnBack());

        new AllNewsPage()
                .isPageOpened(Language.RU);
    }
}
