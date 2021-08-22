import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TrainersPage {

    WebDriver driver;

    private final By SEARCH_FIELD = By.xpath("//input[@formcontrolname='searchFilter']");
    private final By TRAINER_CARD = By.xpath("//mat-card");
    private final By TRAINER_NAME = By.xpath("//div[@class='title']");
    //private final By MORE_INFO_BUTTON = By.xpath("//mat-card-actions/button");

    public TrainersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean SearchForTrainers(String searchTerm) {
        driver.findElement(SEARCH_FIELD).sendKeys(searchTerm);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> results = driver.findElements(TRAINER_CARD);
        int match = 0;
        for (WebElement i: results) {
            if (i.getText().contains(searchTerm)) {
                match += 1;
            } /*else if (!i.getText().contains(searchTerm)){
                int index = results.indexOf(i);
                i.findElement(By.xpath("//*[@class='mat-card-actions']/button[" + String.valueOf(index) + "]")).click();
            } */
        }
        boolean correctResult = false;
        if (match == results.size()) {
            correctResult = true;
        }
        System.out.println(match);
        System.out.println(results.size());
        return correctResult;
    }

    public int SaveTrainersToFile() {
        List<WebElement> trainers = driver.findElements(TRAINER_NAME);
        try {
            FileWriter writer = new FileWriter("trainers.txt");
            for (WebElement name: trainers) {
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

}
