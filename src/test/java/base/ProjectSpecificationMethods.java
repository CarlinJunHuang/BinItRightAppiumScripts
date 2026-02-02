package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
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
				.setNoReset(true)
                .autoGrantPermissions();

        driver = new AndroidDriver(
        		URI.create("http://127.0.0.1:4723").toURL(),
                options
        );
		driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


