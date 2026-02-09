package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;

import java.time.Duration;

public class NewsPage {

    private final WebDriverWait wait;
    private final By newsList = LocatorUtil.idAnyPackage("recyclerViewNews");

    public NewsPage(AndroidDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isNewsScreenDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(newsList)).isDisplayed();
    }
}