package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//button[contains(.,'Add')]")
    private WebElement addEmployeeButton;
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
        addEmployeeButton.click();
    }
    public void fillEmployeeName(String firstName, String lastName){
        wait.until(ExpectedConditions.visibilityOf(firstNameField));

        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void save() {
        saveButton.click();
    }
    public boolean isRedirectedToPersonalDetails() {
        return wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
    }

}
