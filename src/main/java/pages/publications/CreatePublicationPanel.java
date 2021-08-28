package pages.publications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.PUBLICATION;

/**
 * Class to work with container to create different types of publications
 */
public class CreatePublicationPanel {
    private final SelenideElement parentContainer;

    public CreatePublicationPanel(SelenideElement pageContainer) {
        this.parentContainer = pageContainer;
    }

    /**
     *
     * @return container to create different types of publications
     */
    private SelenideElement getCreatePublicationContainer() {
        return parentContainer.find("app-redirect-to-create-news-content");
    }

    /**
     *
     * @return panel of buttons in the container
     */
    private SelenideElement getButtonsPanel() {
        return getCreatePublicationContainer().find("mat-radio-group");
    }

    /**
     *
     * @return
     */
    private ElementsCollection getButtons() {
        return getButtonsPanel().findAll("mat-radio-button");
    }

    /**
     *
     * @return button to create a publication
     */
    private SelenideElement getCreatePublicationButton() {
        return getCreatePublicationContainer().find(".create-btn.mat-raised-button");
    }

    /**
     * Select a type of publication that should be created
     * @param publication type of publication
     */
    public void openPageToCreate(PUBLICATION publication) {
        switch (publication) {
            case NEWS:      getButtons().get(0).click(); break;
            case ARTICLES:  getButtons().get(1).click(); break;
        }
        getCreatePublicationButton().click();
    }

}
