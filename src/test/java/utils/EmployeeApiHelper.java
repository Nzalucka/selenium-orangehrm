package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class EmployeeApiHelper {
    private static final String BASE_URL =
            "https://opensource-demo.orangehrmlive.com/web/index.php/api/v2";

    private static String getCookie() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver tempDriver = new ChromeDriver(options);

        tempDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");


        new WebDriverWait(tempDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        tempDriver.findElement(By.name("username")).sendKeys("Admin");
        tempDriver.findElement(By.name("password")).sendKeys("admin123");
        tempDriver.findElement(By.cssSelector("button[type='submit']")).click();

        new WebDriverWait(tempDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("dashboard"));

        String cookie = tempDriver.manage().getCookieNamed("orangehrm").getValue();
        tempDriver.quit();
        return cookie;
    }


    public static int createEmployee(String firstName, String lastName) {
        return given()
                .baseUri(BASE_URL)
                .cookie("orangehrm", getCookie())
                .contentType(ContentType.JSON)
                .body(new EmployeeRequest(firstName, lastName))
                .when().post("/pim/employees")
                .then().statusCode(200)
                .extract().jsonPath().getInt("data.empNumber");
    }

    public static void deleteEmployee(int empNumber) {
        given()
                .baseUri(BASE_URL)
                .cookie("orangehrm", getCookie())
                .contentType(ContentType.JSON)
                .body(new DeleteEmployeeRequest(empNumber))
                .when().delete("/pim/employees")
                .then().statusCode(200);
    }
}
