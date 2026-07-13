package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, 'pim')]")
    private WebElement pimMenuItem;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
        return driver.getCurrentUrl().contains("dashboard");
    }

    @Step("Navigate to PIM module")
    public void goToPim() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenuItem)).click();
        wait.until(ExpectedConditions.urlContains("pim"));
    }

}
