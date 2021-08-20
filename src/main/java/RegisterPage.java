import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class RegisterPage {

    WebDriver driver;
    Util util;
    private final By EMAIL = By.id("email");
    private final By PASSWORD = By.id("password");
    private final By CONFIRM_PASSWORD = By.id("confirmPassword");
    private final By TERMS_AND_CONDITIONS_CHECKBOX = By.xpath("//*[@id='mat-checkbox-1']//div[1]");
    private final By PRIVACY_POLICY_CHECKBOX = By.xpath("//*[@id='mat-checkbox-2']//div[1]");
    private final By REGISTER_BUTTON = By.xpath("//*[@class='action-container']//button[1]");
    public final By NOTIFICATION = By.xpath("//*[@class='notifier__notification-message ng-star-inserted']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void RegisterHappyPath() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        util.IgymClickAcceptCookies();
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        driver.findElement(REGISTER_BUTTON).click();
        driver.switchTo().window(tabs.get(0));
        util.ConfirmEmail();
        driver.switchTo().window(tabs.get(1));
    }

    public boolean RegisterWithInvalidEmail() {
        util = new Util(driver);
        driver.get(TestData.REGISTRATION_URL);
        util.IgymClickAcceptCookies();
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
        util.IgymClickAcceptCookies();
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
        util.IgymClickAcceptCookies();
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
        util.IgymClickAcceptCookies();
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
        util.IgymClickAcceptCookies();
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
        util.IgymClickAcceptCookies();
        driver.findElement(EMAIL).sendKeys(reg_email);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
        boolean isRegisterButtonActive = driver.findElement(REGISTER_BUTTON).isEnabled();
        return isRegisterButtonActive;
    }

    public String RegisterAlreadyExistingUser() {
        util = new Util(driver);
        driver.get(TestData.REGISTRATION_URL);
        util.IgymClickAcceptCookies();
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
