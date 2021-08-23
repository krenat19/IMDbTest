import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportsPageTest extends BaseTest {

    LoginPage loginPage;
    CalendarPage calendarPage;
    ReportsPage reportsPage;


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LIST-01: Többoldalas lista bejárása")
    public void CountReportsTest() {
        driver.get(TestData.LOGIN_URL);
        loginPage = new LoginPage(driver);
        calendarPage = loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        reportsPage = calendarPage.NavigateToReportsPage();
        //number of how many pages you want to iterate through
        int pagesToScan = 20;
        int numberOfCards = reportsPage.CountReports(pagesToScan);
        Assertions.assertEquals(numberOfCards, pagesToScan * 20);
    }
}
