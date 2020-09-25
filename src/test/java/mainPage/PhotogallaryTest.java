package mainPage;

import org.junit.Test;
import parentTest.ParentTest;

public class PhotogallaryTest extends ParentTest {

    @Test
    public void createNewAlbum() throws InterruptedException {
        mainPage.goToAllAlbums();
        photogallaryPageAll.createNewAlbum();
        photogallaryPageAll.writeTitle("ATest " + actions.currentTime());
        photogallaryPageAll.writeShortDescription(loremIpsum.getLoremByBytes(50, 65));
        photogallaryPageAll.writeDescription(loremIpsum.getLoremByBytes(50, 205));
        photogallaryPageAll.chooseTypeOfEvent();
        photogallaryPageAll.chooseTA();
        photogallaryPageAll.disableLikes();
        photogallaryPageAll.disableComments();
        photogallaryPageAll.disablePhotoDownloading();
        photogallaryPageAll.disablePhotoSharing();
        photogallaryPageAll.saveAlbum();
        photogallaryPageAlbum.checkAlbumTitle();
        photogallaryPageAlbum.checkAlbumDescription();
        photogallaryPageAlbum.downloadImageToAlbum();
        actions.refreshPage(); //Непрацуит шабака дикая
        photogallaryPageAlbum.checkPhotoInAlbum(); //Работает через раз потому, что иногда портал не подгружает новые фотографии до перезагрузки страницы.
    }
}
