package tests;

import base.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.EmployeeApiHelper;

public class EmployeeListTest extends BaseTest {
    private EmployeeListPage employeeListPage;
    private int empNumber;

   @BeforeMethod
   public void setUpPage() {
       empNumber= EmployeeApiHelper.createEmployee("Employee","Onetest");
       LoginPage loginPage = new LoginPage(driver);
       DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");
       dashboardPage.goToPim();
       employeeListPage = new EmployeeListPage(driver);

   }
    @AfterMethod
    public void cleanUp() {
        EmployeeApiHelper.deleteEmployee(empNumber);
    }

    @Test
    public void searchExistingEmployee() {
    employeeListPage.searchByName("Onetest");
    assertThat(employeeListPage.getResultsCount()).isGreaterThan(0);
    }

    @Test
    public void openEmployeeProfile(){
       employeeListPage.searchByName("Onetest");
        PersonalDetailsPage personalDetailsPage= employeeListPage.openFirstResult();
        assertThat(driver.getCurrentUrl()).contains("viewPersonalDetails");
    }
    @Test
    public void searchNonExistingEmployee() {

       employeeListPage.searchByNameNoResults("ffff");
       assertThat(employeeListPage.isNoRecordsFound()).isTrue();
    }
    @Test
    public void verifyTableHeaders() {
        employeeListPage.searchAll();
        assertThat(employeeListPage.getTableHeaders())
                .contains("First (& Middle) Name", "Last Name", "Id", "Actions");
    }
}
