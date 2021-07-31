import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    WebDriver driver;

    @BeforeEach
    public void Setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Search() {
        String searchTitle = "Terminator";
        driver.get("https://www.imdb.com/");
        driver.findElement(MainPage.SearchBox).sendKeys(searchTitle);
        driver.findElement(MainPage.SearchButton).click();

        WebElement HeaderText = driver.findElement(SearchResults.HeaderTitle);
        Assertions.assertEquals("Results for " + "\"" + searchTitle + "\"", HeaderText.getText());
    }

    @AfterEach
    public void Close() {
        driver.quit();
    }
}
