package tests;

import base.ProjectSpecificationMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class AppLaunchTest extends ProjectSpecificationMethods {

    @Test
    public void appShouldLaunch() {
        Assert.assertNotNull(driver, "Driver should be initialized");

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        boolean launchedToExpectedScreen = loginPage.isLoginPageDisplayed() || homePage.isHomePageDisplayed();
        Assert.assertTrue(launchedToExpectedScreen, "App did not launch to login or home screen");
    }
}