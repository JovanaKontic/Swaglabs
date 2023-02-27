package Test;

import Base.SwagLabsBase;
import Pages.HeaderPage;
import Pages.LoadingPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Header1Test extends SwagLabsBase {
    String username;
    String password;
    @BeforeMethod
    public void setUpPage() {
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        headerPage = new HeaderPage();
        loadingPage = new LoadingPage();
    }
    @Test
    public void menuButtonIsClickable() {
//        All valid users - menu bar appearing
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            logIn(username, password);
            Assert.assertEquals(driver.getCurrentUrl(),logedInPage);
            headerPage.clickOnMenuButton();
            waitForVisibility(headerPage.sideBar);
            Assert.assertTrue(IsDisplayed(headerPage.sideBar));
        }
    }
    public void menuBar() {
        logIn(username, password);
        headerPage.clickOnMenuButton();
        waitForVisibility(headerPage.sideBar);
    }
        @Test
    public void allItemsClickable () {
//        All valid users - All Items
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            logIn(username, password);
            headerPage.clickOnShopingCartButton();
            headerPage.clickOnMenuButton();
            waitForVisibility(headerPage.sideBar);
            headerPage.clickOnAllItems();
            Assert.assertEquals(driver.getCurrentUrl(), excelReader.getStringData("URL",1,1));
        }
    }
    @Test
    public void aboutButtonClickableSU () {
//        standard_user - about button leading to sauce labs
        username = excelReader.getStringData("LoginValid", 1, 0);
        password = excelReader.getStringData("LoginValid", 1, 1);
        menuBar();
        headerPage.clickOnAboutSidebarButton();
        Assert.assertEquals(driver.getCurrentUrl(),excelReader.getStringData("URL",1,2));
    }
    @Test
    public void aboutButtonClickablePU () {
//        problem_user - about button error
        username = excelReader.getStringData("LoginValid", 2, 0);
        password = excelReader.getStringData("LoginValid", 1, 1);
        menuBar();
        headerPage.clickOnAboutSidebarButton();
        Assert.assertEquals(driver.getCurrentUrl(),excelReader.getStringData("URL",1,3));
    }
    @Test
    public void aboutButtonClickablePGU () {
//        performance_glitch_user - about button error
        username = excelReader.getStringData("LoginValid", 3, 0);
        password = excelReader.getStringData("LoginValid", 1, 1);
        menuBar();
        headerPage.clickOnAboutSidebarButton();
        Assert.assertEquals(driver.getCurrentUrl(),excelReader.getStringData("URL",1,2));
    }
    @Test
    public void UserCanLogOut () {
//        All valid users - log out
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            menuBar();
            headerPage.clickOnLogOutButton();
            Assert.assertTrue(IsDisplayed(loadingPage.usernameField));
            Assert.assertTrue(IsDisplayed(loadingPage.logInButton));
            Assert.assertEquals(driver.getCurrentUrl(),excelReader.getStringData("URL", 1, 0));
        }
    }
    @Test
    public void resetAppStateClickable() {
//        All valid users - resetting
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            logIn(username, password);
            headerPage.clickOnAddToCart();
            Assert.assertEquals(headerPage.shopingcartCounter.getText(),"1");
            headerPage.clickOnMenuButton();
            waitForClickability(headerPage.resetAppButton);
            headerPage.clickOnResetAppButton();
            Assert.assertEquals(headerPage.shopingcartCounter.getText(),"");
        }
    }
        @Test
        public void exitButtonClickable () {
//        All valid users - exit button
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            menuBar();
            waitForClickability(headerPage.exitButton);
            headerPage.clickOnExitButton();
            Assert.assertTrue(IsDisplayed(headerPage.menuButton));
        }
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
