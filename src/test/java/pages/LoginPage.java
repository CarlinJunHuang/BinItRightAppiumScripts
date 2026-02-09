package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private final By username = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"iss.nus.edu.sg.webviews.binitrightmobileapp:id/appUsername\")"
    );

    private final By password = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"iss.nus.edu.sg.webviews.binitrightmobileapp:id/appPassword\")"
    );

    private final By signIn = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"iss.nus.edu.sg.webviews.binitrightmobileapp:id/btnSignIn\")"
    );

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
        } catch (Exception ignored) {
        }

        driver.findElement(signIn).click();
    }

    public boolean isLoginPageDisplayed() {

        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(username))
                .isDisplayed();
    }
}
