import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    private final By EMAIL_FIELD = By.id("emailOrUserName");
    private final By PASSWORD_FIELD = By.id("password");
    public final By LOGIN_BUTTON = By.xpath("//div[@class='action-container']//button[1]");
    private final By ACCEPT_COOKIES = By.xpath("//app-accept-cookie//button");
    public final By NOTIFICATION = By.xpath("//*[@class='notifier__notification-message ng-star-inserted']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Login(String username, String password) {
        driver.findElement(EMAIL_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public void ClickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void ClickAcceptCookies() {
        driver.findElement(ACCEPT_COOKIES).click();
    }
}

