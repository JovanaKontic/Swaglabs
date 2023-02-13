package Pages;

import Base.SwagLabsBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends SwagLabsBase {
    public HeaderPage() {PageFactory.initElements(driver,this);
    }

    public @FindBy (id = "react-burger-menu-btn") WebElement menuButton;
    public @FindBy (className = "bm-menu-wrap") WebElement sideBar;
    public @FindBy (id = "inventory_sidebar_link") WebElement allItems;
    public @FindBy (id = "about_sidebar_link") WebElement aboutSidebarButton;
    public @FindBy (id = "logout_sidebar_link") WebElement logOutButton;
    public @FindBy (id = "reset_sidebar_link") WebElement resetAppButton;
    public @FindBy (id = "react-burger-cross-btn") WebElement exitButton;
    public @FindBy (id = "shopping_cart_container") WebElement shopingCartButton;
    public @FindBy (css = ".btn.btn_primary.btn_small.btn_inventory") WebElement addToCart;
    public  @FindBy (className = "shopping_cart_link") WebElement shopingcartCounter;

//========================================
    public void clickOnMenuButton () {
        menuButton.click();
    }
    public void clickOnAboutSidebarButton () {
        aboutSidebarButton.click();
    }
    public void clickOnLogOutButton () {
        logOutButton.click();
    }
    public void clickOnShopingCartButton () {
        shopingCartButton.click();
    }
    public void clickOnAllItems () {
        allItems.click();
    }
    public void clickOnResetAppButton () {
        resetAppButton.click();
    }
    public void clickOnExitButton () {
        exitButton.click();
    }
    public void clickOnAddToCart () {
        addToCart.click();
    }





}