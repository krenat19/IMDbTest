import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    WebDriver driver;

    private final By SEARCHBOX = By.xpath("//*[@id=\"navbar\"]/search-algolia/search-algolia-controls/input");
    private final By SEARCHBUTTON = By.xpath("//*[@id=\"navbar\"]/search-algolia/search-algolia-controls/a");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void SearchWithClick(String inputText) {
        driver.findElement(SEARCHBOX).sendKeys(inputText);
        driver.findElement(SEARCHBUTTON).click();
    }
}

