package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocatorUtil;

import java.time.Duration;
import java.util.List;

public class FindRecyclingBinPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By mapContainer = LocatorUtil.idAnyPackage("mapContainer");
    private final By recyclerView = LocatorUtil.idAnyPackage("binsRecyclerView");

    private final By chipBlueBin = LocatorUtil.idAnyPackage("chipBlueBin");
    private final By chipEwaste = LocatorUtil.idAnyPackage("chipEwaste");
    private final By binTypeText = LocatorUtil.idAnyPackage("txtType");

    private final By firstDirectionButton = LocatorUtil.idAnyPackage("btnDirections");

    public FindRecyclingBinPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(16));
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapContainer));
        wait.until(ExpectedConditions.visibilityOfElementLocated(recyclerView));
    }

    public boolean isMapVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mapContainer)).isDisplayed();
    }

    public boolean isListVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(recyclerView)).isDisplayed();
    }

    public void filterBlueBins() {
        wait.until(ExpectedConditions.elementToBeClickable(chipBlueBin)).click();
        waitForListRefresh();
    }

    public void filterEWaste() {
        wait.until(ExpectedConditions.elementToBeClickable(chipEwaste)).click();
        waitForListRefresh();
    }

    private void waitForListRefresh() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(binTypeText, 0));
    }

    public boolean areAllBinsOfType(String expectedType) {
        List<WebElement> types = driver.findElements(binTypeText);
        if (types.isEmpty()) {
            return false;
        }

        for (WebElement type : types) {
            if (!type.getText().trim().equalsIgnoreCase(expectedType)) {
                return false;
            }
        }
        return true;
    }

    public void clickFirstDirection() {
        List<WebElement> buttons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(firstDirectionButton)
        );
        buttons.get(0).click();
    }

    public boolean isGoogleMapsOpened() {
        WebDriverWait pkgWait = new WebDriverWait(driver, Duration.ofSeconds(12));

        boolean opened = pkgWait.until(d -> {
            String pkg = driver.getCurrentPackage();
            System.out.println("Current package: " + pkg);
            return pkg.contains("google.android.apps.maps");
        });

        driver.navigate().back();
        return opened;
    }
}