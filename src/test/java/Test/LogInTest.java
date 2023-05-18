package Test;

import Base.SwagLabsBase;
import Pages.HeaderPage;
import Pages.LoadingPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogInTest extends SwagLabsBase {
    String username;
    String password;


    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loadingPage = new LoadingPage();
    }
    @Test
    public void userCanLogIn () {
//        Looping through valid users (predefined) /valid password
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = excelReader.getStringData("LoginValid", 1, 1);
            logIn(username,password);
            Assert.assertEquals(driver.getCurrentUrl(),logedInPage);
        }
    }
    @Test
    public void userCanNotLogInLOU () {
//        invalid user ( predefined) /valid password
        username = excelReader.getStringData("LoginValid", 1, 2);
        password = excelReader.getStringData("LoginValid", 1, 1);
        logIn(username,password);
        Assert.assertTrue(IsDisplayed(loadingPage.errorMark));
    }
    @Test
    public void userCanNotLogInWithEmptyPassword () {
//        Looping through valid users (predefined)
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            username = excelReader.getStringData("LoginValid", i, 0);
            password = "";
            logIn(username,password);
            Assert.assertTrue(IsDisplayed(loadingPage.errorMark));
        }
    }
    @Test
    public void userCanNotLogInWithEmptyPasswordLU () {
//        invalid users (predefined)
            username = excelReader.getStringData("LoginValid", 1, 2);
            password = "";
            logIn(username,password);
            Assert.assertTrue(IsDisplayed(loadingPage.errorMark));
    }
    @Test
    public void userCanNotLogInWithInvalidPassword () {
//       Looping throudh invalid paswords with valid users
        for (int i =1; i<= excelReader.getLastRow("LoginValid"); i++) {
            for (int j =1; j<= excelReader.getLastRow("Invalid password"); j++) {
                username = excelReader.getStringData("LoginValid",i,0);
                password = excelReader.getStringData("Invalid password", j,0);
                logIn(username,password);
                Assert.assertTrue(IsDisplayed(loadingPage.errorMark));
            }
        }
    }
    @Test
    public void userCanNotLogInWithInvalidPasswordLU () {
//       Looping throudh invalid paswords with invalid users
        for (int i =1; i<= excelReader.getLastRow("Invalid Username"); i++) {
            for (int j =1; j<= excelReader.getLastRow("Invalid password"); j++) {
                username = excelReader.getStringData("Invalid Username",i,0);
                password = excelReader.getStringData("Invalid password", j,0);
                logIn(username,password);
                Assert.assertTrue(IsDisplayed(loadingPage.errorMark));
            }
        }
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
