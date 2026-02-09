package utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class LocatorUtil {

    private LocatorUtil() {
    }

    // Match resource ids regardless of flavor/package suffix.
    public static By idAnyPackage(String id) {
        return AppiumBy.androidUIAutomator(
                "new UiSelector().resourceIdMatches(\".*:id/" + id + "\")"
        );
    }
}