package Test;

import Base.SwagLabsBase;
import Pages.CartPage;
import Pages.HeaderPage;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoadingPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
public class ShopingCartTest extends SwagLabsBase {
    String username;
    String password;

    @BeforeMethod
    public void setUpPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        headerPage = new HeaderPage();
        loadingPage = new LoadingPage();
        cartPage = new CartPage();

    }
    @Test
    public void CartButtonCheck () {
//        For  valid users (predefined)
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            logIn(username,password);
            headerPage.clickOnShopingCartButton();
            Assert.assertEquals(driver.getCurrentUrl(),excelReader.getStringData("URL", 1, 4));
            Assert.assertTrue(IsDisplayed(cartPage.checkOutButton));
        }
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
