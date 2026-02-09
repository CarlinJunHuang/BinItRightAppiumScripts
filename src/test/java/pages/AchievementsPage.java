package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;

import java.time.Duration;

public class AchievementsPage {

    private final WebDriverWait wait;
    private final By achievementsList = LocatorUtil.idAnyPackage("rvAchievements");

    public AchievementsPage(AndroidDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAchievementsScreenDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(achievementsList)).isDisplayed();
    }
}