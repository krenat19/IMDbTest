import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class LoginPageTest extends BaseTest {

    LoginPage loginpage;
    CalendarPage calendarPage;
    Util util;


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("LOGIN-01: Normál bejelentkezés")
    public void LoginTest() {
        loginpage = new LoginPage(driver);
        loginpage.NavigateToLoginPage();
        calendarPage = loginpage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        String msg = calendarPage.GetWelcomeMessage();
        Assertions.assertEquals("Hello, " + TestData.TRAINER_FIRSTNAME, msg);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-02: Bejelentkezés üres mezőkkel")
    public void EmptyLoginTest() {
        loginpage = new LoginPage(driver);
        boolean isLoginButtonActive = loginpage.EmptyLogin();
        Assertions.assertFalse(isLoginButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-03: Bejelentkezés nem regisztrált email-címmel")
    public void LoginWithUnregisteredEmailTest() {
        loginpage = new LoginPage(driver);
        String notificationMessage = loginpage.LoginWithUnregisteredEmail();
        Assertions.assertEquals(TestData.USER_DOESNT_EXIST_NOTIFICATION, notificationMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-04: Bejelentkezés regisztrált, de nem megerősített email-címmel")
    public void LoginWithUnconfirmedEmailTest() {
        loginpage = new LoginPage(driver);
        String notificationMessage = loginpage.LoginWithUnconfirmedEmail();
        Assertions.assertEquals(TestData.UNCONFIRMED_EMAIL_ADDRESS_NOTIFICATION, notificationMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-05: Bejelentkezés felhasználónév megadása nélkül")
    public void LoginWithoutUsernameTest() {
        loginpage = new LoginPage(driver);
        boolean isLoginButtonActive = loginpage.LoginWithoutUsername();
        Assertions.assertFalse(isLoginButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-06: Bejelentkezés jelszó megadása nélkül")
    public void LoginWithoutPasswordTest() {
        loginpage = new LoginPage(driver);
        boolean isLoginButtonActive = loginpage.LoginWithoutPassword();
        Assertions.assertFalse(isLoginButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGIN-07: Bejelentkezés helytelen jelszóval")
    public void LoginWithIncorrectPasswordTest() {
        loginpage = new LoginPage(driver);
        String notificationMessage = loginpage.LoginWithIncorrectPassword();
        Assertions.assertEquals(TestData.UNSUCCESSFUL_LOGIN_NOTIFICATION, notificationMessage);
    }


}
