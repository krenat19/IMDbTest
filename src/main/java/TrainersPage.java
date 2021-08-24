import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TrainersPage {

    WebDriver driver;
    Util util;

    private final By SEARCH_FIELD = By.xpath("//input[@formcontrolname='searchFilter']");
    private final By ACCEPT_COOKIES_BUTTON = By.xpath("//app-accept-cookie//button");
    private final By TRAINER_NAME = By.xpath("//div[@class='title']");
    private final By MORE_INFO_BUTTON = By.xpath("//mat-card-actions/button");
    private final By NAME_ON_CARD = By.xpath("//*[@role='dialog']//*[@class='title']");
    private final By TRAINER_CARD = By.xpath("//*[@role='dialog']");

    public TrainersPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> SearchForTrainers(String searchTerm) {
        driver.findElement(SEARCH_FIELD).sendKeys(searchTerm);
        //sleep needed in order to wait for loading of elements
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElements(MORE_INFO_BUTTON);
    }

    //method opens every trainer card and checks content against search term
    //(this is needed because some data is only visible after clicking on the "More info" button)
    public boolean CheckResults(List<WebElement> list, String searchTerm) {
        util = new Util(driver);
        boolean result = false;
        int index = 0;
        int correctResult = 0;
        for (WebElement i : list) {
            list.get(index).click();
            String name = driver.findElement(NAME_ON_CARD).getText();
            if (name.contains(searchTerm.toUpperCase())) {
                correctResult += 1;
                index += 1;
            }
            //avoids StaleElementException
            if (util.retryingFindClick(TRAINER_CARD)) {
                driver.findElement(TRAINER_CARD).sendKeys(Keys.ESCAPE);
            }
        }
        if (correctResult == list.size()) {
            result = true;
        }
        return result;
    }

    public int SaveTrainersToFile() {
        List<WebElement> trainers = driver.findElements(TRAINER_NAME);
        try {
            FileWriter writer = new FileWriter("trainers.txt");
            for (WebElement name : trainers) {
                String trainer = name.getText();
                System.out.println(trainer);
                writer.write(trainer + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return trainers.size();
    }

    public void AcceptCookies() {
        driver.findElement(ACCEPT_COOKIES_BUTTON).click();
    }

}
