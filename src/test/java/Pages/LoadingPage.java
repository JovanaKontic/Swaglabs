package Pages;

import Base.SwagLabsBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoadingPage extends SwagLabsBase {
    public LoadingPage() {
        PageFactory.initElements(driver,this);
    }
    public @FindBy (id = "user-name") WebElement usernameField;
    public @FindBy (id = "password") WebElement passwordField;
    public @FindBy (id = "login-button") WebElement logInButton;
    public @FindBy (className = "error-button") WebElement errorMark;
// ============================================

    public void inputUsername (String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    public void inputPassword (String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLogInButton () {
        logInButton.click();
    }

// ZA NEKU DRUGU KLASU
// GETBUTTONS JE GETTER LISTE WEB ELEMENATA
//    "name" je kako je napisano u DOMu

//    public void clickOnButton(String name) {
//        for (int i = 0; i < getButtons().size(); i++) {
//            if (getButtons().get(i).getText().equalsIgnoreCase(name)) {
//                getButtons().get(i).click();
//                break;
//            }
//        }
//    }

}
