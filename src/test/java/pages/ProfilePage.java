package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;
import utils.ScrollUtil;

import java.time.Duration;

public class ProfilePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = LocatorUtil.idAnyPackage("logoutBtn");
    private final By profileName = LocatorUtil.idAnyPackage("profileName");

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isProfileScreenDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileName)).isDisplayed();
    }

    public void scrollToLogout() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().resourceIdMatches(\".*:id/logoutBtn\"))"
                )
        );
    }

    public LoginPage logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));

        for (int i = 0; i < 3; i++) {
            ScrollUtil.scrollDown(driver);
            if (!driver.findElements(logoutButton).isEmpty()) {
                break;
            }
        }

        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.executeScript("mobile: clickGesture", java.util.Map.of(
                "elementId",
                ((RemoteWebElement) logoutBtn).getId()
        ));

        return new LoginPage(driver);
    }
}