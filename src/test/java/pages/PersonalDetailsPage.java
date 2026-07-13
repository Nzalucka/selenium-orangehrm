package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//label[contains(.,'Female')]")
    private WebElement femaleRadioLabel;

    @FindBy(xpath = "//label[contains(.,'Male')]")
    private WebElement maleRadioLabel;

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Select gender: female")
    public void selectFemale() {
        wait.until(ExpectedConditions.visibilityOf(femaleRadioLabel));
        wait.until(ExpectedConditions.elementToBeClickable(femaleRadioLabel));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleRadioLabel);
    }
    @Step("Select gender: male")
    public void selectMale() {
        maleRadioLabel.click();
    }
    public boolean isFemaleSelected(){
        WebElement span = femaleRadioLabel.findElement(By.tagName("span"));
        System.out.println("Span class: " + span.getAttribute("class"));
        return span.getAttribute("class").contains("oxd-radio-input--focus");
    }

}
