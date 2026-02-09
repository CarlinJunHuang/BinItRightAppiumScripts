package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;

import java.time.Duration;

public class LoginPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By username = LocatorUtil.idAnyPackage("appUsername");
    private final By password = LocatorUtil.idAnyPackage("appPassword");
    private final By signIn = LocatorUtil.idAnyPackage("btnSignIn");
    private final By homeMarker = LocatorUtil.idAnyPackage("tvPointsCount");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private boolean isVisible(By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean isPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception ignored) {
            return false;
        }
    }

    private void dismissPermissionDialogs() {
        By[] candidates = new By[] {
                By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"),
                By.id("com.android.permissioncontroller:id/permission_allow_one_time_button"),
                By.id("com.android.permissioncontroller:id/permission_allow_button"),
                By.id("com.android.packageinstaller:id/permission_allow_button")
        };

        for (By locator : candidates) {
            try {
                if (!driver.findElements(locator).isEmpty()) {
                    driver.findElement(locator).click();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public boolean waitUntilLoginOrHome(int seconds) {
        long deadline = System.currentTimeMillis() + seconds * 1000L;
        while (System.currentTimeMillis() < deadline) {
            dismissPermissionDialogs();
            if (isPresent(username) || isPresent(homeMarker)) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    public boolean isHomeMarkerDisplayed() {
        return isPresent(homeMarker);
    }

    public void login(String user, String pass) {
        driver.context("NATIVE_APP");

        if (!waitUntilLoginOrHome(20)) {
            throw new RuntimeException(
                    "Neither login nor home marker found. package=" + driver.getCurrentPackage()
                            + ", activity=" + driver.currentActivity()
            );
        }

        if (!isPresent(username)) {
            if (isPresent(homeMarker)) {
                return;
            }
            throw new RuntimeException(
                    "Login fields not visible. package=" + driver.getCurrentPackage()
                            + ", activity=" + driver.currentActivity()
            );
        }

        WebElement userField = wait.until(ExpectedConditions.elementToBeClickable(username));
        userField.click();
        userField.clear();
        userField.sendKeys(user);

        WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(password));
        passField.click();
        passField.clear();
        passField.sendKeys(pass);

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }

        wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();
    }

    public boolean isLoginPageDisplayed() {
        if (!waitUntilLoginOrHome(15)) {
            return false;
        }
        return isVisible(username, 5);
    }
}
