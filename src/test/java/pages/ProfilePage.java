package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScrollUtil;

import java.time.Duration;

public class ProfilePage {

    private static final String APP_ID = "iss.nus.edu.sg.webviews.binitrightmobileapp";

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = By.id(APP_ID + ":id/logoutBtn");
    private final By profileName = By.id(APP_ID + ":id/profileName");

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isProfileScreenDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(profileName)
        ).isDisplayed();
    }

    public void scrollToLogout() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().resourceId(\""
                                + APP_ID
                                + ":id/logoutBtn\"))"
                )
        );
    }

    public LoginPage logout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));

        for (int i = 0; i < 3; i++) {
            ScrollUtil.scrollDown(driver);
            try {
                Thread.sleep(400);
            } catch (InterruptedException ignored) {
            }

            if (!driver.findElements(logoutButton).isEmpty()) {
                break;
            }
        }

        org.openqa.selenium.WebElement logoutBtn = driver.findElement(logoutButton);
        driver.executeScript("mobile: clickGesture", java.util.Map.of(
                "elementId",
                ((org.openqa.selenium.remote.RemoteWebElement) logoutBtn).getId()
        ));

        return new LoginPage(driver);
    }
}

