import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginPageTest extends BaseTest {

    LoginPage loginpage;
    Util util;
    private final String URL = "https://igym-igym-dev.azurewebsites.net/authentication/login";



    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void LoginTest() {
        loginpage = new LoginPage(driver);
        driver.get(URL);
        loginpage.Login("r01lfgpopg@privacy-mail.top", "123456");
        loginpage.ClickLoginButton();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    public void EmptyLoginTest() {
        loginpage = new LoginPage(driver);
        driver.get(URL);
        loginpage.Login("", "");
        WebElement loginButton = driver.findElement(loginpage.LOGIN_BUTTON);
        boolean isLoginButtonActive = loginButton.isEnabled();
        Assertions.assertFalse(isLoginButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void LoginWithUnregisteredEmail() {
        loginpage = new LoginPage(driver);
        util = new Util(driver);
        driver.get(URL);
        util.IgymClickAcceptCookies();
        loginpage.Login("kukutyin@zabhegyezes.hu", "jelszo1234");
        loginpage.ClickLoginButton();
        WebElement nonExistingUserAlert = driver.findElement(loginpage.NOTIFICATION);
        boolean isAlertVisible = nonExistingUserAlert.isDisplayed();
        Assertions.assertTrue(isAlertVisible);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void LoginWithUnconfirmedEmail() {
        loginpage = new LoginPage(driver);
        util = new Util(driver);
        driver.get(URL);
        util.IgymClickAcceptCookies();
        loginpage.Login("rogici6615@186site.com", "training1000");
        loginpage.ClickLoginButton();
        WebElement unconfirmedEmail = driver.findElement(loginpage.NOTIFICATION);
        String alertText = unconfirmedEmail.getText();
        Assertions.assertEquals("Az email cím nincs megerősítve", alertText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void LoginWithoutPassword() {
        loginpage = new LoginPage(driver);
        driver.get(URL);
        loginpage.Login("r01lfgpopg@privacy-mail.top", "");
        WebElement loginButton = driver.findElement(loginpage.LOGIN_BUTTON);
        boolean isLoginButtonActive = loginButton.isEnabled();
        Assertions.assertFalse(isLoginButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void LoginWithIncorrectPassword() {
        loginpage = new LoginPage(driver);
        util = new Util(driver);
        driver.get(URL);
        util.IgymClickAcceptCookies();
        loginpage.Login("r01lfgpopg@privacy-mail.top", "1234567");
        loginpage.ClickLoginButton();
        WebElement incorrectPasswordAlert = driver.findElement(loginpage.NOTIFICATION);
        boolean isAlertVisible = incorrectPasswordAlert.isDisplayed();
        Assertions.assertTrue(isAlertVisible);
    }



}
