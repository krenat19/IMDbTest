import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class CalendarPage {

    WebDriver driver;
    LoginPage loginPage;

    private final By WELCOME_MESSAGE = By.xpath("//*[@class='welcome-user ng-tns-c186-0']");
    private final By LOGOUT_BUTTON = By.xpath("//div[@class='icon-container ng-tns-c186-0']/mat-icon[2]");
    private final By LOGIN_BUTTON = By.xpath("//span[@class='ng-tns-c186-0']");
    private final By HAMBURGER_BUTTON = By.xpath("//*[@class='mat-focus-indicator ng-tns-c186-0 mat-icon-button mat-button-base']");
    private final By REPORTS_BUTTON = By.xpath("//mat-sidenav//a[7]");
    private final By PROFILE_BUTTON = By.xpath("//*[@class='toolbar-container ng-tns-c186-0']/div/app-profile-pic");


    public CalendarPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProfilePage NavigateToProfilePage() {
        driver.findElement(PROFILE_BUTTON).click();
        return new ProfilePage(driver);
    }

    public ReportsPage NavigateToReportsPage() {
        driver.findElement(HAMBURGER_BUTTON).click();
        driver.findElement(REPORTS_BUTTON).click();
        return new ReportsPage(driver);
    }

    public String GetWelcomeMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(WELCOME_MESSAGE))).getText();
    }

    public String GetLogoutText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(LOGIN_BUTTON))).getText();
    }

    public void Logout() {
        loginPage = new LoginPage(driver);
        driver.get(TestData.LOGIN_URL);
        loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public void LogoutWithDuplicateLogin() {
        loginPage = new LoginPage(driver);
        driver.get(TestData.LOGIN_URL);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(TestData.LOGIN_URL);
        driver.switchTo().window(tabs.get(0));
        loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        driver.switchTo().window(tabs.get(1));
        loginPage.Login(TestData.SECOND_USER_EMAIL, TestData.SECOND_USER_PASSWORD);
        driver.switchTo().window(tabs.get(0));
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void LogoutWhenLoggingOutOnDifferentTab() {
        loginPage = new LoginPage(driver);
        driver.get(TestData.LOGIN_URL);
        loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(WELCOME_MESSAGE)));
        String currentURL = driver.getCurrentUrl();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(currentURL);
        driver.findElement(LOGOUT_BUTTON).click();
        driver.switchTo().window(tabs.get(0));
        driver.navigate().refresh();
    }
}
