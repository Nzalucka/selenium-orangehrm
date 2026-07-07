package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(name = "username")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class, 'oxd-alert-content-text')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//span[text()='Required']")
    private List<WebElement>  requiredMessages;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    public void waitForDashboard() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }
    @Step("Logging in as: {username}")
    public void login(String username, String password){
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    @Step("Logging in as {username} and navigating to dashboard")
    public DashboardPage loginAs(String username, String password) {
        login(username, password);
        waitForDashboard();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
    public List<String> getRequiredMessage() {
        wait.until(ExpectedConditions.visibilityOfAllElements(requiredMessages));
    return requiredMessages.stream().map(WebElement::getText)
                .collect(Collectors.toList());

    }
    public int getRequiredMessagesCount(){
        return requiredMessages.size();
    }


}
