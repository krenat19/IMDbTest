import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class ProfilePageTest extends BaseTest{

    ProfilePage profilePage;

    LoginPage loginPage;
    CalendarPage calendarPage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("DATA-01: Új adat bevitele a bemutatkozás mezőbe")
    public void AddIntroductionTest() {
        driver.get(TestData.LOGIN_URL);
        loginPage = new LoginPage(driver);
        loginPage = new LoginPage(driver);
        calendarPage = loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        profilePage = calendarPage.NavigateToProfilePage();
        profilePage.AcceptCookies();
        String msg = profilePage.AddIntroduction(TestData.NEW_INTRODUCTION);
        Assertions.assertEquals(TestData.NEW_INTRODUCTION.toUpperCase(), msg);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("DATA-02: Meglévő adat módosítása")
    public void ModifyIntroductionTest() {
        driver.get(TestData.LOGIN_URL);
        loginPage = new LoginPage(driver);
        calendarPage = loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        profilePage = calendarPage.NavigateToProfilePage();
        profilePage.AcceptCookies();
        String msg = profilePage.AddIntroduction(TestData.MODIFIED_INTRODUCTION);
        Assertions.assertEquals(TestData.MODIFIED_INTRODUCTION.toUpperCase(), msg);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("DATA-03: Meglévő adat törlése")
    public void DeleteIntroductionTest() {
        driver.get(TestData.LOGIN_URL);
        loginPage = new LoginPage(driver);
        calendarPage = loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        profilePage = calendarPage.NavigateToProfilePage();
        profilePage.AcceptCookies();
        String msg = profilePage.DeleteIntroduction();
        Assertions.assertEquals("", msg);

    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("DATA-04: Adatok sorozatos feltöltése külső fájlból")
    public void AddCompanyDataTest() {
        driver.get(TestData.LOGIN_URL);
        loginPage = new LoginPage(driver);
        calendarPage = loginPage.Login(TestData.TRAINER_EMAIL, TestData.TRAINER_PASSWORD);
        profilePage = calendarPage.NavigateToProfilePage();
        profilePage.AcceptCookies();
        String msg = profilePage.AddCompanyData();
        Assertions.assertEquals(TestData.SUCCESSFUL_COMPANY_DATA_UPLOAD, msg);

    }
}
