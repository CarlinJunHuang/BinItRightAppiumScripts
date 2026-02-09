package utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class LocatorUtil {

    private static final String DEFAULT_APP_PACKAGE = "iss.nus.edu.sg.webviews.binitrightmobileapp";

    private LocatorUtil() {
    }

    // Prefer exact resource-id matching for stability and speed.
    public static By idAnyPackage(String id) {
        String appPackage = System.getProperty("app.package", DEFAULT_APP_PACKAGE);
        return AppiumBy.id(appPackage + ":id/" + id);
    }
}
