package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.EmployeeApiHelper;

import static org.assertj.core.api.Assertions.assertThat;

public class AddEmployeeTest extends BaseTest {
    private AddEmployeePage addEmployeePage;
    private EmployeeListPage employeeListPage;
    private int empNumber;

    @BeforeMethod
    public void setUpPage() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.loginAs("Admin", "admin123");
        dashboardPage.goToPim();
        addEmployeePage = new AddEmployeePage(driver);
        employeeListPage = new EmployeeListPage(driver);
    }
    @Test
    public void addEmployeeWithValidData() {
        addEmployeePage.clickAddEmployee();
        addEmployeePage.fillEmployeeName("Natali", "Testing");
        addEmployeePage.save();
        assertThat(addEmployeePage.isRedirectedToPersonalDetails()).isTrue();

        String url = driver.getCurrentUrl();
        String[] parts = url.split("empNumber/");
        empNumber = Integer.parseInt(parts[1]);
    }
    @Test
    public void deleteEmployee() {

        empNumber= EmployeeApiHelper.createEmployee("Natalia", "Testowa");
        employeeListPage.searchByName("Testowa");
        employeeListPage.deleteFirstRowEmployee();
        assertThat(employeeListPage.getResultsCount()).isEqualTo(0);
empNumber=0;
    }


    }
