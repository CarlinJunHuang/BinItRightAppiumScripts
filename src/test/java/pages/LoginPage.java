package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    private By username =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(0)"
            );

    private By password =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(1)"
            );

    By signIn  = By.id("btnSignIn");

    public void login(String user, String pass) {

        WebElement userField = wait.until(
                ExpectedConditions.presenceOfElementLocated(username)
        );
        userField.click();
        userField.sendKeys(user);

        WebElement passField = wait.until(
                ExpectedConditions.presenceOfElementLocated(password)
        );
        passField.click();
        passField.sendKeys(pass);

        // Hide keyboard
        try {
            driver.hideKeyboard();
            Thread.sleep(1000);
        } catch (Exception ignored) {}


        driver.findElement(signIn).click();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElements(signIn).size() > 0;
    }
}
