package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    // ===== POSITIVE =====

    @Test
    public void loginWithValidCredentials(){
        loginPage.login("Admin", "admin123");
        loginPage.waitForDashboard();
        assertThat(driver.getCurrentUrl()).contains("dashboard");
    }
    // ===NEGATIVE===
    @Test
    public void loginWithInvalidCredentials() {
    loginPage.login("Admin","wrongPassword");
        assertThat(loginPage.getErrorMessage()).contains("Invalid credentials");
    }

    @Test
    public void loginWithInwalidUsername(){
        loginPage.login("wronguser", "admin123");
        assertThat(loginPage.getErrorMessage()).contains("Invalid credentials");
    }

    @Test
    public void loginWithEmptyCredentials(){
        loginPage.login("","");
        assertThat(loginPage.getRequiredMessage().contains("Required"));
    }

}
