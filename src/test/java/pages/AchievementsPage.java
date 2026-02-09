package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AchievementsPage {

    private static final String APP_ID = "iss.nus.edu.sg.webviews.binitrightmobileapp";

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By achievementsList = By.id(APP_ID + ":id/rvAchievements");

    public AchievementsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAchievementsScreenDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(achievementsList)).isDisplayed();
    }
}

