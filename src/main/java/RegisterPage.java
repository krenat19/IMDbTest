import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RegisterPage {

    WebDriver driver;
    Util util;
    private final By EMAIL = By.id("email");
    private final By PASSWORD = By.id("password");
    private final By CONFIRM_PASSWORD = By.id("confirmPassword");
    private final By TERMS_AND_CONDITIONS_CHECKBOX = By.xpath("//*[@formcontrolname='termsAndConditions']//div[1]");
    private final By PRIVACY_POLICY_CHECKBOX = By.xpath("//*[@formcontrolname='privacy']//div[1]");
    private final By REGISTER_BUTTON = By.xpath("//*[@class='action-container']//button[1]");
    private final By NOTIFICATION = By.xpath("//*[@class='notifier__notification-message ng-star-inserted']");
    private final By TERMS_AND_CONDITIONS_LINK = By.xpath("//*[@formcontrolname='termsAndConditions']//a");
    private final By PRIVACY_POLICY_LINK = By.xpath("//*[@formcontrolname='privacy']//a");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//app-accept-cookie//button");
    private final By COOKIE_BOTTOM_BAR = By.xpath("//app-accept-cookie//section");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean CheckingTermsAndConditions() {
        boolean result = false;
        driver.findElement(TERMS_AND_CONDITIONS_LINK).click();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String address = driver.getCurrentUrl();
        if (address.contains(".pdf")) {
            result = true;
        }
        return result;
    }

    public boolean CheckingPrivacyPolicy() {
        boolean result = false;
        driver.findElement(PRIVACY_POLICY_LINK).click();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String address = driver.getCurrentUrl();
        if (address.contains(".pdf")) {
            result = true;
        }
        return result;
    }

    public int AcceptCookies() {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
        return driver.findElements(COOKIE_BOTTOM_BAR).size();
    }



    public String RegisterWithoutConfirming() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        driver.findElement(REGISTER_BUTTON).click();
        return reg_email;
    }

    public void RegisterHappyPath() {
        RegisterWithoutConfirming();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        util.ConfirmEmail();
        driver.switchTo().window(tabs.get(1));
    }

    public boolean RegisterWithInvalidEmail() {
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(TestData.INVALID_EMAIL);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public boolean RegisterWithInvalidPassword() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.INVALID_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.INVALID_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public boolean RegisterWithMismatchedPassword() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.MISMATCHED_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public boolean RegisterWithoutAcceptingTermsAndConditionsAndPrivacyPolicy() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public boolean RegisterWithoutAcceptingPrivacyPolicy() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public boolean RegisterWithoutAcceptingTermsAndConditions() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public String RegisterAlreadyExistingUser() {
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(TestData.TRAINER_EMAIL);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        driver.findElement(REGISTER_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String notificationMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(NOTIFICATION))).getText();
        return notificationMessage;
    }
}
