package Base;

import Pages.CartPage;
import Pages.HeaderPage;
import Pages.LoadingPage;
import Test.Header1Test;
import Test.LogInTest;
import Test.ShopingCartTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class SwagLabsBase {

    public static WebDriver driver;
    public WebDriverWait waiter;
    public ExcelReader excelReader;

    public String homePage;
    public String logedInPage;
    public LoadingPage loadingPage;
    public HeaderPage headerPage;
    public CartPage cartPage;

    public LogInTest logInTest;
    public Header1Test header1Test;
    public ShopingCartTest shopingCartTest;

    @BeforeClass
    public void StartUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("src/test/java/TestDataSwagLabs.xlsx");
        homePage = excelReader.getStringData("URL",1,0);
        logedInPage = excelReader.getStringData("URL", 1, 1);
    }
    public boolean IsDisplayed(WebElement element) {
//        Ova metoda funkcionise samo ako se elementi nalaze preko anotacija, ne preko getera
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {

        }
        return webelement;
    }
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void waitForVisibility(WebElement element) {
        waiter.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForClickability(WebElement element) {
        waiter.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void logIn (String username,String password) {
        driver.get(homePage);
        Assert.assertTrue(IsDisplayed(loadingPage.usernameField));
        loadingPage.inputUsername(username);
        loadingPage.inputPassword(password);
        loadingPage.clickOnLogInButton();
    }

    @AfterClass
    public void shutDown () {
        driver.close();
    }
}
