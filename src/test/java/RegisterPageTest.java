import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterPageTest extends BaseTest {

    Util util;
    RegisterPage registerPage;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("REG-01: Normál regisztráció")
    public void RegisterHappyPathTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterHappyPath();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(TestData.LOGIN_URL, currentURL);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-02: Regisztráció invalid email-címmel")
    public void RegisterWithInvalidEmailTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithInvalidEmail();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-03: Regisztráció érvénytelen jelszóval")
    public void RegisterWithInvalidPasswordTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithInvalidPassword();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-04: Regisztráció nem egyező jelszavakkal")
    public void RegisterWithMismatchedPasswordTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithMismatchedPassword();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-05: Regisztráció ÁSZF és adatvédelmi feltételek elfogadása nélkül")
    public void RegisterWithoutAcceptingTermsAndConditionsAndPrivacyPolicyTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithoutAcceptingTermsAndConditionsAndPrivacyPolicy();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-06: Regisztráció adatvédelmi feltételek elfogadása nélkül")
    public void RegisterWithoutAcceptingPrivacyPolicyTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithoutAcceptingPrivacyPolicy();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-07: Regisztráció ÁSZF elfogadása nélkül")
    public void RegisterWithoutAcceptingTermsAndConditionsTest() {
        registerPage = new RegisterPage(driver);
        boolean isRegistrationButtonActive = registerPage.RegisterWithoutAcceptingTermsAndConditions();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-08: Regisztráció már létező felhasználóval")
    public void RegisterAlreadyExistingUserTest() {
        registerPage = new RegisterPage(driver);
        String notificationMessage = registerPage.RegisterAlreadyExistingUser();
        Assertions.assertEquals(TestData.USER_ALREADY_EXISTS_NOTIFICATION, notificationMessage);
    }
}

