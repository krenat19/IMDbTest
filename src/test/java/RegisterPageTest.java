import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegisterPageTest extends BaseTest {

    RegisterPage registerPage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("T&C-01: Általános felhasználási feltételek meglétének ellenőrzése")
    @Disabled("A headless mód nem támogatja a PDF fájlokat, ezért a teszt Disabled módba került. Lokálisan, nem headless módban helyesen lefut (pl. IntelliJ segítségével).")
    public void CheckingTermsAndConditionsTest() {
        registerPage = new RegisterPage(driver);
        driver.get(TestData.REGISTRATION_URL);
        registerPage.CheckingTermsAndConditions();
        boolean result = registerPage.VerifyPDF();
        Assertions.assertTrue(result);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("T&C-02: Adatvédelmi feltételek meglétének ellenőrzése")
    @Disabled("A headless mód nem támogatja a PDF fájlokat, ezért a teszt Disabled módba került. Lokálisan, nem headless módban helyesen lefut (pl. IntelliJ segítségével).")
    public void CheckingPrivacyPolicyTest() {
        registerPage = new RegisterPage(driver);
        driver.get(TestData.REGISTRATION_URL);
        registerPage.CheckingPrivacyPolicy();
        boolean result = registerPage.VerifyPDF();
        Assertions.assertTrue(result);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("T&C-03: Cookie-k elfogadása")
    public void AcceptCookiesTest() {
        registerPage = new RegisterPage(driver);
        driver.get(TestData.REGISTRATION_URL);
        int CookieBarDisplayed = registerPage.AcceptCookies();
        Assertions.assertEquals(0, CookieBarDisplayed);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("REG-01: Normál regisztráció")
    public void RegisterHappyPathTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterHappyPath();
        Assertions.assertEquals(TestData.LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-02: Regisztráció invalid email-címmel")
    public void RegisterWithInvalidEmailTest() {
        registerPage = new RegisterPage(driver);
        driver.get(TestData.REGISTRATION_URL);
        registerPage.RegisterWithInvalidEmail();
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-03: Regisztráció érvénytelen jelszóval")
    public void RegisterWithInvalidPasswordTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterBasic(TestData.INVALID_PASSWORD, TestData.INVALID_PASSWORD);
        registerPage.AcceptTermsAndConditions();
        registerPage.AcceptPrivacyPolicy();
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-04: Regisztráció nem egyező jelszavakkal")
    public void RegisterWithMismatchedPasswordTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterBasic(TestData.REG_PASSWORD, TestData.MISMATCHED_PASSWORD);
        registerPage.AcceptTermsAndConditions();
        registerPage.AcceptPrivacyPolicy();
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-05: Regisztráció ÁSZF és adatvédelmi feltételek elfogadása nélkül")
    public void RegisterWithoutAcceptingTermsAndConditionsAndPrivacyPolicyTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterBasic(TestData.REG_PASSWORD, TestData.REG_PASSWORD);
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-06: Regisztráció adatvédelmi feltételek elfogadása nélkül")
    public void RegisterWithoutAcceptingPrivacyPolicyTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterBasic(TestData.REG_PASSWORD, TestData.REG_PASSWORD);
        registerPage.AcceptTermsAndConditions();
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-07: Regisztráció ÁSZF elfogadása nélkül")
    public void RegisterWithoutAcceptingTermsAndConditionsTest() {
        registerPage = new RegisterPage(driver);
        registerPage.RegisterBasic(TestData.REG_PASSWORD, TestData.REG_PASSWORD);
        registerPage.AcceptPrivacyPolicy();
        boolean isRegistrationButtonActive = registerPage.IsRegisterButtonActive();
        Assertions.assertFalse(isRegistrationButtonActive);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("REG-08: Regisztráció már létező felhasználóval")
    public void RegisterAlreadyExistingUserTest() {
        registerPage = new RegisterPage(driver);
        driver.get(TestData.REGISTRATION_URL);
        registerPage.RegisterAlreadyExistingUser();
        registerPage.AcceptTermsAndConditions();
        registerPage.AcceptPrivacyPolicy();
        registerPage.ClickRegisterButton();
        String notificationMessage = registerPage.GetNotificationText();
        Assertions.assertEquals(TestData.USER_ALREADY_EXISTS_NOTIFICATION, notificationMessage);
    }
}

