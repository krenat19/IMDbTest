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
        calendarPage.Logout();
        String iconText = calendarPage.GetLogoutText();
        Assertions.assertEquals("BELÉPÉS", iconText);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGOUT-02: Kijelentkezés második felhasználó bejelentkezése esetén")
    public void LogoutWithDuplicateLoginTest() {
        calendarPage = new CalendarPage(driver);
        calendarPage.LogoutWithDuplicateLogin();
        String welcomeMessage = calendarPage.GetWelcomeMessage();
        Assertions.assertEquals("Hello, " + TestData.SECOND_USER_EMAIL, welcomeMessage);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("LOGOUT-03: Kijelentkezés másik fülön való kijelentkezés után")
    public void LogoutWhenLoggingOutOnDifferentTabTest() {
        calendarPage = new CalendarPage(driver);
        calendarPage.LogoutWhenLoggingOutOnDifferentTab();
        String iconText = calendarPage.GetLogoutText();
        Assertions.assertEquals("BELÉPÉS", iconText);
    }
}
