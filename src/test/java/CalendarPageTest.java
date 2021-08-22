import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class CalendarPageTest extends BaseTest {

    CalendarPage calendarPage;


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("LOGOUT-01: Kijelentkezés kattintással")
    public void LogoutTest() {
        calendarPage = new CalendarPage(driver);
        String iconText = calendarPage.Logout();
        Assertions.assertEquals("BELÉPÉS", iconText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGOUT-02: Kijelentkezés második felhasználó bejelentkezése esetén")
    public void LogoutWithDuplicateLoginTest() {
        calendarPage = new CalendarPage(driver);
        String welcomeMessage = calendarPage.LogoutWithDuplicateLogin();
        Assertions.assertEquals("Hello, " + TestData.SECOND_USER_EMAIL, welcomeMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGOUT-03: Kijelentkezés másik fülön való kijelentkezés után")
    public void LogoutWhenLoggingOutOnDifferentTabTest() {
        calendarPage = new CalendarPage(driver);
        String iconText = calendarPage.LogoutWhenLoggingOutOnDifferentTab();
        Assertions.assertEquals("BELÉPÉS", iconText);
    }
}
