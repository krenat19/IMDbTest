import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class TrainersPageTest extends BaseTest {

    TrainersPage trainersPage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("SEARCH-01: Adatok listázása kereséssel")
    public void SearchForTrainersTest() {
        driver.get(TestData.TRAINERS_PAGE_URL);
        trainersPage = new TrainersPage(driver);
        trainersPage.AcceptCookies();
        String searchTerm = "Adri";
        List<WebElement> resultlist = trainersPage.SearchForTrainers(searchTerm);
        boolean matchingResult = trainersPage.CheckResults(resultlist, searchTerm);
        Assertions.assertTrue(matchingResult);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("SEARCH-02: Edzők neveinek mentése txt fájlba")
    public void SaveTrainersToFileTest() {
        driver.get(TestData.TRAINERS_PAGE_URL);
        trainersPage = new TrainersPage(driver);
        int foundTrainers = trainersPage.SaveTrainersToFile();
        //reads txt file and checks the number of lines
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"));
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
        Assertions.assertEquals(foundTrainers, lines);
    }
}

