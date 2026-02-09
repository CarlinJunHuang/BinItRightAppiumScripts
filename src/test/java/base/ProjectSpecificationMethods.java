package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;
import java.time.Duration;

public class ProjectSpecificationMethods {

    private static final String APP_PACKAGE = "iss.nus.edu.sg.webviews.binitrightmobileapp";

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        String apkPath = System.getProperty("apk.path");
        if (apkPath == null || apkPath.isBlank()) {
            throw new RuntimeException("APK path not provided");
        }

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setApp(apkPath)
                .setNoReset(false)
                .setFullReset(false)
                .autoGrantPermissions()
                .amend("uiautomator2ServerInstallTimeout", 120000)
                .amend("adbExecTimeout", 120000)
                .amend("uiautomator2ServerLaunchTimeout", 120000)
                .amend("newCommandTimeout", 180);

        driver = new AndroidDriver(
                URI.create("http://127.0.0.1:4723").toURL(),
                options
        );

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        try {
            driver.activateApp(APP_PACKAGE);
        } catch (Exception ignored) {
        }

        System.out.println("Current package: " + driver.getCurrentPackage());
        System.out.println("Current activity: " + driver.currentActivity());

        handlePermissions();
    }

    private void clickIfPresent(By locator) {
        try {
            if (!driver.findElements(locator).isEmpty()) {
                driver.findElement(locator).click();
            }
        } catch (Exception ignored) {
        }
    }

    private void handlePermissions() {
        // Try several known permission dialog IDs used across Android images.
        for (int i = 0; i < 3; i++) {
            clickIfPresent(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
            clickIfPresent(By.id("com.android.permissioncontroller:id/permission_allow_one_time_button"));
            clickIfPresent(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            clickIfPresent(By.id("com.android.packageinstaller:id/permission_allow_button"));

            try {
                Thread.sleep(350);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}