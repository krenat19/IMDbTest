import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    WebDriver driver;

    private final By SEARCHBOX = By.xpath("//*[@id=\"navbar\"]/search-algolia/search-algolia-controls/input");
    private final By SEARCHBUTTON = By.xpath("//*[@id=\"navbar\"]/search-algolia/search-algolia-controls/a");
    private final By SIGNUPBUTTON = By.id("masthead-show-registration-btn");
    private final By SIGNUP_FIRSTNAME = By.id("registrationForm_firstName");
    private final By SIGNUP_LASTNAME = By.xpath("//*[@id=\"registrationForm_form\"]/div/ul/li[2]/input");
    private final By SIGNUP_EMAIL = By.xpath("//*[@id=\"registrationForm_form\"]/div/ul/li[3]/input");
    private final By SIGNUP_PASSWORD = By.id("registrationForm_password");
    private final By SIGNUP_NEWSLETTER_CHECKBOX = By.id("register_newsletter");
    private final By SIGNUP_RECAPTCHA_CHECKBOX = By.id("recaptcha-anchor");
    private final By SIGNUP_CREATEACCOUNT_BUTTON = By.id("registrationForm_submitBtn");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void SearchWithClick(String inputText) {
        driver.findElement(SEARCHBOX).sendKeys(inputText);
        driver.findElement(SEARCHBUTTON).click();
    }

    public void SignUp() {
        driver.findElement(SIGNUPBUTTON).click();
        driver.findElement(SIGNUP_FIRSTNAME).sendKeys("Géza");
        driver.findElement(SIGNUP_LASTNAME).sendKeys("Kiss");
        driver.findElement(SIGNUP_EMAIL).sendKeys("dovami4302@100xbit.com");
        driver.findElement(SIGNUP_PASSWORD).sendKeys("kissgeza23");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(SIGNUP_RECAPTCHA_CHECKBOX))).click();
        driver.findElement(SIGNUP_CREATEACCOUNT_BUTTON).click();
    }
}

