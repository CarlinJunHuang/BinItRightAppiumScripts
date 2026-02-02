package tests;

import base.ProjectSpecificationMethods;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AppLaunchTest extends ProjectSpecificationMethods {

	@Test
    public void appShouldLaunch() {
        // If app launches without crash → test passes
        System.out.println("App launched successfully");

		Assert.assertNotNull(driver, "Driver should be initialized");
    }
}
