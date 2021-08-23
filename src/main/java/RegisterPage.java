import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


    public boolean VerifyPDF() {
        boolean result = false;
        String address = driver.getCurrentUrl();
        if (address.contains(".pdf")) {
            result = true;
        }
        return result;

    }
    public void CheckingTermsAndConditions() {
        driver.findElement(TERMS_AND_CONDITIONS_LINK).click();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }


    public void CheckingPrivacyPolicy() {
        driver.findElement(PRIVACY_POLICY_LINK).click();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public int AcceptCookies() {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
        return driver.findElements(COOKIE_BOTTOM_BAR).size();
    }

    public void AcceptTermsAndConditions() {
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
    }

    public void AcceptPrivacyPolicy() {
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
    }

    public String GetNotificationText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(NOTIFICATION))).getText();
    }

    public boolean IsRegisterButtonActive() {
        return driver.findElement(REGISTER_BUTTON).isEnabled();
    }

    public void ClickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }


    public String RegisterBasic(String password, String confirmpassword) {
        util = new Util(driver);
        String email = util.GenerateNewEmail();
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.REGISTRATION_URL);
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(confirmpassword);
        return email;
    }

    public void RegisterHappyPath() {
        RegisterBasic(TestData.REG_PASSWORD, TestData.REG_PASSWORD);
        AcceptTermsAndConditions();
        AcceptPrivacyPolicy();
        ClickRegisterButton();
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        util.ConfirmEmail();
        driver.switchTo().window(tabs.get(1));
    }

    public void RegisterWithInvalidEmail() {
        driver.findElement(EMAIL).sendKeys(TestData.INVALID_EMAIL);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(TERMS_AND_CONDITIONS_CHECKBOX).click();
        driver.findElement(PRIVACY_POLICY_CHECKBOX).click();
    }

    public void RegisterAlreadyExistingUser() {
        driver.findElement(EMAIL).sendKeys(TestData.TRAINER_EMAIL);
        driver.findElement(PASSWORD).sendKeys(TestData.REG_PASSWORD);
        driver.findElement(CONFIRM_PASSWORD).sendKeys(TestData.REG_PASSWORD);
    }
}
