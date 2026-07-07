package tests;

import base.BaseTest;
import static org.assertj.core.api.Assertions.assertThat;import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.PersonalDetailsPage;

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
}
