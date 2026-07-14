package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input[placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(css = ".oxd-table-body .oxd-table-row")
    private List<WebElement> resultRows;

    @FindBy(css = "span.oxd-checkbox-input")
    private List<WebElement> checkboxes;
    @FindBy(xpath = "//button[contains(.,'Delete Selected')]")
    private WebElement deleteSelectedButton;

    @FindBy(xpath = "//button[contains(.,'Yes, Delete')]")
    private WebElement confirmDeleteButton;

    @FindBy(css = ".oxd-table-header-cell")
    private List<WebElement> tableHeaders;


    private final By resultRowLocator = By.cssSelector(".oxd-table-body .oxd-table-row");

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
}
    @Step("Search employee by name: {name}")
    public EmployeeListPage searchByName(String name){

        wait.until(ExpectedConditions.visibilityOf(employeeNameInput));


        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);
        searchButton.click();
        wait.until(ExpectedConditions.urlContains("pim"));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRowLocator, 0));
        return this;
}
    @Step("Search employee by name with no results: {name}")
    public EmployeeListPage searchByNameNoResults(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput));
        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='No Records Found']")));
        return this;
    }
    public boolean isNoRecordsFound() {
        return driver.findElement(
                        By.xpath("//span[text()='No Records Found']"))
                .isDisplayed();
    }
    public int getResultsCount() {
       return resultRows.size();
    }
    public PersonalDetailsPage openFirstResult() {
        resultRows.get(0).click();
        return new PersonalDetailsPage(driver);
    }

    public void selectFirstRowCheckbox(){
        checkboxes.get(0).click();

    }
    public void deleteFirstRowEmployee(){
        selectFirstRowCheckbox();
        deleteSelectedButton.click();
        confirmDeleteButton.click();
    }
    @Step("Search all employees")
    public EmployeeListPage searchAll() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRowLocator, 0));
        return this;
    }
    public List<String>getTableHeaders(){
        wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
        return tableHeaders.stream().map(WebElement::getText).collect(Collectors.toList());

    }
}

