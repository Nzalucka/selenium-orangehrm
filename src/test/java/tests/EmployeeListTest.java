package tests;

import base.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class EmployeeListTest extends BaseTest {
    private EmployeeListPage employeeListPage;

   @BeforeMethod
   public void setUpPage() {
       LoginPage loginPage = new LoginPage(driver);
       DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");
       dashboardPage.goToPim();
       employeeListPage = new EmployeeListPage(driver);

   }
    @Test
    public void searchExistingEmployee() {
    employeeListPage.searchByName("Amelia");
    assertThat(employeeListPage.getResultsCount()).isGreaterThan(0);
    }

    @Test
    public void openEmployeeProfile(){
       employeeListPage.searchByName("Amelia");
        PersonalDetailsPage personalDetailsPage= employeeListPage.openFirstResult();
        assertThat(driver.getCurrentUrl()).contains("viewPersonalDetails");
    }
    @Test
    public void AddEmployee(){
        AddEmployeePage addEmployeePage=new AddEmployeePage(driver);
        addEmployeePage.clickAddEmployee();
        addEmployeePage.fillEmployeeName("Natali", "Testing");
        addEmployeePage.save();
        assertThat(addEmployeePage.isRedirectedToPersonalDetails()).isTrue();
    }
    @Test
    public void testDeleteEmployee(){
       employeeListPage.searchByName("Natalia Testowa");
       employeeListPage.deleteFirstRowEmployee();
       assertThat(employeeListPage.getResultsCount()).isEqualTo(0);
    }
}
