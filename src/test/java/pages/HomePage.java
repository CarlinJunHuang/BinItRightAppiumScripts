package pages;

import utils.ScrollUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    private By pointsCounter = By.id("iss.nus.edu.sg.webviews.bin:id/tvPointsCount");
    private By findBinsCard  = By.id("iss.nus.edu.sg.webviews.bin:id/cardFindBins");
    private By recycleNowButton = By.id("btnRecycleNow");
    private By homeTitle =
            By.xpath("//android.widget.TextView[@text='Bin It Right']");

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }


    public void waitForHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pointsCounter));
    }

    public boolean isHomePageDisplayed() {
        try {
            driver.context("NATIVE_APP");

            // Home screen navigation indicator (not API-based)
            wait.until(ExpectedConditions.presenceOfElementLocated(homeTitle));

            return true;
        } catch (Exception e) {
            System.out.println("Home page not detected: " + e.getMessage());
            return false;
        }
    }


    public void openFindRecyclingBins() {
        waitForHomePage();

        int maxSwipes = 6;

        for (int i = 0; i < maxSwipes; i++) {
            if (driver.findElements(findBinsCard).size() > 0) {
                WebElement card = driver.findElement(findBinsCard);
                wait.until(ExpectedConditions.elementToBeClickable(card)).click();
                System.out.println("Clicked Find Recycling Bins card");
                return;
            }
            swipeUp();
        }

        throw new RuntimeException("Find Recycling Bins card not found after scrolling");
    }


    private void swipeUp() {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.75);
        int endY   = (int) (size.height * 0.25);

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(600)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void clickRecycleNow() {
        ScrollUtil.scrollDown(driver);
        driver.findElement(recycleNowButton).click();
    }
}
