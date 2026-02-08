package base;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ProjectSpecificationMethods {
	
	protected AndroidDriver driver;
	
	@BeforeMethod
    public void setUp() throws Exception {

        String apkPath = System.getProperty("apk.path");

        if (apkPath == null) {
            throw new RuntimeException("APK path not provided");
        }

       UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName("Android")
               .setDeviceName("Android Emulator")
               .setAutomationName("UiAutomator2")
               .setApp(apkPath)
				.setNoReset(false)
               .setFullReset(true)
               .autoGrantPermissions()

        // UiAutomator2Options options = new UiAutomator2Options()
        //         .setPlatformName("Android")
        //         .setAutomationName("UiAutomator2")
        //         .setDeviceName("8ARY0Q2RN")
        //         .setUdid("8ARY0Q2RN")
        //         .setApp(apkPath)
        //         .setAutoGrantPermissions(true)
        //         .setNoReset(false)

			.amend("uiautomator2ServerInstallTimeout", 120000)
        	.amend("adbExecTimeout", 120000)
        	.amend("uiautomator2ServerLaunchTimeout", 120000);

       	 	driver = new AndroidDriver(
        	URI.create("http://127.0.0.1:4723").toURL(),
            options
        );

        System.out.println("Current activity: " + driver.currentActivity());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        handlePermissions();
    }

    private void handlePermissions() {
        try {
            driver.findElement(
                    By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")
            ).click();
        } catch (Exception ignored) {}

        try {
            driver.findElement(
                    By.id("com.android.permissioncontroller:id/permission_allow_button")
            ).click();
        } catch (Exception ignored) {}
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


