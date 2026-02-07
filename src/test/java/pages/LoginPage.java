package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    By username = By.id("appUsername");
    By password = By.id("appPassword");
    By signIn  = By.id("btnSignIn");

    public void login(String user, String pass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(user);

        driver.findElement(password).sendKeys(pass);
        driver.findElement(signIn).click();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(signIn).isDisplayed();
    }
}

