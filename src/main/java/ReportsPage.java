import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ReportsPage {

    WebDriver driver;

    private final By REPORT_CARD = By.xpath("//*[@class='mat-card mat-focus-indicator example-card']");
    private final By FURTHER_REPORTS_BUTTON = By.xpath("//*[@class='button-accent' and contains(text(), 'További edzések')]");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//app-accept-cookie//button");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }


    public int CountReports(int pages) {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
        int numberOfCards = 0;
        for (int i = 1; i < pages; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(FURTHER_REPORTS_BUTTON))).click();
            //sleep is necessary as the elements are loading increasingly slower as we iterate through the pages
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            List<WebElement> reportCards = driver.findElements(REPORT_CARD);
            numberOfCards = reportCards.size();
        }
        return numberOfCards;
    }
}
