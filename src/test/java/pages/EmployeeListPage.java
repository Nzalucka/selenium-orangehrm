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

public class EmployeeListPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "input[placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(css = ".oxd-table-body .oxd-table-row")
    private List<WebElement> resultRows;

    private final By resultRowLocator = By.cssSelector(".oxd-table-body .oxd-table-row");

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
}
    @Step("Search employee by name: {name}")
    public EmployeeListPage searchByName(String name){
        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);
        searchButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRowLocator, 0));
        return this;
}
    public int getResultsCount() {
       return resultRows.size();
    }
    public PersonalDetailsPage openFirstResult() {
        resultRows.get(0).click();
        return new PersonalDetailsPage(driver);
    }
}

