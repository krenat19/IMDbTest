import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    WebDriver driver;

    private final By ABOUT_ME_SECTION = By.xpath("//*[@formcontrolname='aboutMe']");
    private final By SAVE_BUTTON = By.xpath("//*[@class='form-wrapper']//button");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//app-accept-cookie//button");
    private final By PROFILE_DESCRIPTION = By.xpath("//*[@class='details']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void AcceptCookies() {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
    }

    public String AddIntroduction(String intro) {
        driver.findElement(ABOUT_ME_SECTION).sendKeys(Keys.CONTROL + "a");
        driver.findElement(ABOUT_ME_SECTION).sendKeys(Keys.DELETE);
        driver.findElement(ABOUT_ME_SECTION).sendKeys(intro);
        driver.findElement(SAVE_BUTTON).click();
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(PROFILE_DESCRIPTION))).getText();
    }

    public String DeleteIntroduction() {
        driver.findElement(ABOUT_ME_SECTION).sendKeys(Keys.CONTROL + "a");
        driver.findElement(ABOUT_ME_SECTION).sendKeys(Keys.BACK_SPACE);
        driver.findElement(SAVE_BUTTON).click();
        driver.navigate().refresh();
        return driver.findElement(PROFILE_DESCRIPTION).getText();
    }


}
