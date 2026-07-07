package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDetailsTest extends BaseTest {
    private PersonalDetailsPage personalDetailsPage;
    @BeforeMethod
    public void setUpPage() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");
        dashboardPage.goToPim();

        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.searchByName("Amelia");
        personalDetailsPage = employeeListPage.openFirstResult();
    }
    @Test
    public void selectFemaleGender(){
        personalDetailsPage.isFemaleSelected();
        assertThat(personalDetailsPage.isFemaleSelected()).isTrue();
    }
}
