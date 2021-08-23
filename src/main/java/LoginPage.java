import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    Util util;
    RegisterPage registerPage;

    private final By EMAIL_FIELD = By.id("emailOrUserName");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.xpath("//div[@class='action-container']//button[1]");
    private final By NOTIFICATION = By.xpath("//*[@class='notifier__notification-message ng-star-inserted']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String GetNotificationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String notificationMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(NOTIFICATION))).getText();
        return notificationMessage;
    }


    public boolean IsLoginButtonActive() {
        return driver.findElement(LOGIN_BUTTON).isEnabled();
    }

    public CalendarPage Login(String username, String password) {
        driver.findElement(EMAIL_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new CalendarPage(driver);
    }



    public void LoginWithUnregisteredEmail() {
        util = new Util(driver);
        String reg_email = util.GenerateNewEmail();
        driver.get(TestData.LOGIN_URL);
        Login(reg_email, TestData.TRAINER_PASSWORD);
    }

    public void LoginWithUnconfirmedEmail() {
        registerPage = new RegisterPage(driver);
        String reg_email = registerPage.RegisterWithoutConfirming();
        driver.get(TestData.LOGIN_URL);
        Login(reg_email, TestData.REG_PASSWORD);
    }



}

