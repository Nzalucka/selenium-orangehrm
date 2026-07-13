package tests;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;
import utils.EmployeeApiHelper;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDetailsTest extends BaseTest {
    private PersonalDetailsPage personalDetailsPage;
    private int empNumber;


    @BeforeMethod
    public void setUpPage() {
        // Step 1 API
         empNumber = EmployeeApiHelper.createEmployee("Test", "Automation");
        System.out.println("Created employee: " + empNumber);

        // Step 2
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");
        dashboardPage.goToPim();

        // Step 3
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.searchByName("Test");
        personalDetailsPage = employeeListPage.openFirstResult();
    }
    @AfterMethod
    public void cleanUp() {
        EmployeeApiHelper.deleteEmployee(empNumber);
        System.out.println("Deleted employee: " + empNumber);
    }
    @Test
    public void selectFemaleGender(){
        personalDetailsPage.selectFemale();
        assertThat(personalDetailsPage.isFemaleSelected()).isTrue();
    }
}
