import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ProfilePage {

    WebDriver driver;
    Util util;

    private final By ABOUT_ME_SECTION = By.xpath("//*[@formcontrolname='aboutMe']");
    private final By SAVE_BUTTON = By.xpath("//*[@class='form-wrapper']//button");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//app-accept-cookie//button");
    private final By PROFILE_DESCRIPTION = By.xpath("//*[@class='details']");
    private final By COMPANY_NAME = By.xpath("//*[@formcontrolname='name']");
    private final By COMPANY_POSTAL_CODE = By.xpath("//*[@formcontrolname='postalCode']");
    private final By COMPANY_CITY = By.xpath("//*[@formcontrolname='city']");
    private final By COMPANY_ADDRESS = By.xpath("//*[@formcontrolname='address']");
    private final By COMPANY_REG_NUMBER = By.xpath("//*[@formcontrolname='registrationNumber']");
    private final By COMPANY_VAT_NUMBER = By.xpath("//*[@formcontrolname='vatNumber']");
    private final By COMPANY_BANK_ACCOUNT = By.xpath("//*[@formcontrolname='bankAccount']");
    private final By SAVE_COMPANY_DATA_BUTTON = By.xpath("//*[@class='form-container']//button[contains(text(), 'Mentés')]");
    private final By NOTIFICATION = By.xpath("//*[@role='status']");

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

    //reads text from file and sends it to the specified element
    public void InputData(By element, int index) {
        util = new Util(driver);
        List<String> data = util.ReadFromFile("companydata.txt");
        if (util.retryingFindClick(element)) {
            driver.findElement(element).sendKeys(Keys.CONTROL + "a");
            driver.findElement(element).sendKeys(Keys.BACK_SPACE);
            driver.findElement(element).sendKeys(data.get(index));
        }
    }

    public String AddCompanyData() {
        InputData(COMPANY_NAME, 0);
        InputData(COMPANY_POSTAL_CODE, 1);
        InputData(COMPANY_CITY, 2);
        InputData(COMPANY_ADDRESS, 3);
        InputData(COMPANY_REG_NUMBER, 4);
        InputData(COMPANY_VAT_NUMBER, 5);
        InputData(COMPANY_BANK_ACCOUNT, 6);
        driver.findElement(SAVE_COMPANY_DATA_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(NOTIFICATION))).getText();
    }
}
