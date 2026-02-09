package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventsPage {

    private static final String APP_ID = "iss.nus.edu.sg.webviews.binitrightmobileapp";

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By eventsList = By.id(APP_ID + ":id/recyclerViewEvent");

    public EventsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isEventsScreenDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(eventsList)
        ).isDisplayed();
    }
}

