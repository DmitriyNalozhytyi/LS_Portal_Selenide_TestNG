package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlesPage extends ParentPage {

    @FindBy(css = ".detail-wrapper__quote")
    public WebElement fieldNewsTitle;

    @FindBy(css = "ngx-gallery-image > div:nth-child(1)")
    public WebElement fieldImage;

    @FindBy(css = ".text-block__article-text p")
    public WebElement fieldDescription;

    @FindBy(css = ".tag-block__wrapper .default_link")
    public WebElement fieldTag;

    @FindBy(css = ".detail-wrapper__tags-item_link .default_link")
    public WebElement fieldTA;

    @FindBy(css = ".header mat-icon[role='img'] > svg")
    public WebElement iconViews;

    @FindBy(css = ".header .clw_item_num")
    public WebElement counterViews;

    @FindBy(css = ".detail-wrapper_hed-right-edite [role='img']:nth-of-type(1)")
    public WebElement btnEditNews;

    @FindBy(css = ".detail-wrapper_hed-right-edite [role='img']:nth-of-type(2)")
    public WebElement btnDeleteNews;

    @FindBy(css = ".date-block")
    public WebElement fieldDate;

    @FindBy(css = ".mat-stroked-button.mat-stroked-button_left > .mat-button-wrapper > mat-icon[role='img']")
    public WebElement iconPrevious;

    @FindBy(css = ".mat-stroked-button_left .mat-button-wrapper span")
    public WebElement btnPrevious;

    @FindBy(css = ".mat-stroked-button.mat-stroked-button_right > .mat-button-wrapper > mat-icon[role='img']")
    public WebElement iconNext;

    @FindBy(css = ".mat-stroked-button_right .mat-button-wrapper span")
    public WebElement btnNext;

    @FindBy(css = ".clw__add-emoji-trigger [fill-rule]")
    public WebElement iconLike;

    @FindBy(css = ".clw_emoji-block .clw_item_num")
    public WebElement counterLike;

    @FindBy(css = ".clw__add-emoji-trigger span")
    public WebElement btnLike;

    @FindBy(css = ".clw_item_details")
    public WebElement counterComments;

    @FindBy(css = "#content-editable")
    public WebElement inputComment;

    @FindBy(css = "._link-dashboard.bottom")
    public WebElement btnSendComment;

    @FindBy(css = "app-comment-item:nth-of-type(1) .ng-star-inserted.user-comment__add-emoji-wrapper > .mat-button.ng-star-inserted.user-comment__send-subcomment")
    public WebElement btnReplyToComment;

    @FindBy(css = ".subcomment-container #content-editable")
    public WebElement inputCommentReply;

    @FindBy(css = ".subcomment-container ._link-dashboard")
    public WebElement btnSendCommentReply;

    @FindBy(css = "span:nth-of-type(2) > .breadcrumb__link")
    public WebElement btnBackToAllNews;
}
