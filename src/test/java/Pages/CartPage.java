package Pages;

import Base.SwagLabsBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends SwagLabsBase {
    public CartPage() {
        PageFactory.initElements(driver,this);
    }
    public @FindBy (id = "checkout") WebElement checkOutButton;


//    ==================================
    public void clickOnCheckOutButton () {
        checkOutButton.click();
    }

}
