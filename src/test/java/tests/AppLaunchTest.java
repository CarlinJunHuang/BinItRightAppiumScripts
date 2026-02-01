package tests;

import org.testng.annotations.Test;

public class AppLaunchTest {

	@Test
    public void appShouldLaunch() extends ProjectSpecificationMethods{
        // If app launches without crash → test passes
        System.out.println("App launched successfully");

		assert driver != null;
    }
}
