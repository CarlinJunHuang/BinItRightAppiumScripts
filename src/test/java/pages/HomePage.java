package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;
import utils.ScrollUtil;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class HomePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By pointsCounter = LocatorUtil.idAnyPackage("tvPointsCount");
    private final By findBinsCard = LocatorUtil.idAnyPackage("cardFindBins");
    private final By newsTab = LocatorUtil.idAnyPackage("nav_news");
    private final By eventsTab = LocatorUtil.idAnyPackage("nav_events");
    private final By profileTab = LocatorUtil.idAnyPackage("nav_profile");
    private final By recycleNowButton = LocatorUtil.idAnyPackage("btnRecycleNow");
    private final By achievementsCard = LocatorUtil.idAnyPackage("cardAchievements");

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(16));
    }

    public void waitForHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pointsCounter));
    }

    public boolean isHomePageDisplayed() {
        try {
            driver.context("NATIVE_APP");
            waitForHomePage();
            return true;
        } catch (Exception e) {
            System.out.println("Home page not detected: " + e.getMessage());
            return false;
        }
    }

    public void openFindRecyclingBins() {
        driver.context("NATIVE_APP");

        for (int i = 0; i < 6; i++) {
            if (!driver.findElements(findBinsCard).isEmpty()) {
                WebElement card = wait.until(ExpectedConditions.elementToBeClickable(findBinsCard));
                try {
                    card.click();
                } catch (Exception clickException) {
                    driver.executeScript("mobile: clickGesture", Map.of(
                            "elementId", ((RemoteWebElement) card).getId()
                    ));
                }
                return;
            }
            ScrollUtil.scrollDown(driver);
        }

        throw new RuntimeException("Find Recycling Bins card not found on Home screen.");
    }

    public void swipeUp(AndroidDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                startX,
                startY
        ));
        swipe.addAction(finger.createPointerDown(
                PointerInput.MouseButton.LEFT.asArg()
        ));
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(600),
                PointerInput.Origin.viewport(),
                startX,
                endY
        ));
        swipe.addAction(finger.createPointerUp(
                PointerInput.MouseButton.LEFT.asArg()
        ));

        driver.perform(Collections.singletonList(swipe));
    }

    public AchievementsPage goToAchievements() {
        waitForHomePage();
        wait.until(ExpectedConditions.elementToBeClickable(achievementsCard)).click();
        return new AchievementsPage(driver);
    }

    public NewsPage goToNews() {
        wait.until(ExpectedConditions.elementToBeClickable(newsTab)).click();
        return new NewsPage(driver);
    }

    public EventsPage goToEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(eventsTab)).click();
        return new EventsPage(driver);
    }

    public ProfilePage goToProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileTab)).click();
        return new ProfilePage(driver);
    }

    public void clickRecycleNow() {
        for (int i = 0; i < 4; i++) {
            if (!driver.findElements(recycleNowButton).isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(recycleNowButton)).click();
                return;
            }
            ScrollUtil.scrollDown(driver);
        }
        throw new RuntimeException("Recycle Now button not found on Home screen.");
    }
}