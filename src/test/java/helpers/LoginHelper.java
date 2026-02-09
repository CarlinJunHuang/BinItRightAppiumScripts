package helpers;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginHelper {

    public static HomePage loginWithValidUser(AndroidDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        if (loginPage.isLoginPageDisplayed()) {
            loginPage.login(
                    ConfigReader.get("valid.username"),
                    ConfigReader.get("valid.password")
            );
        }

        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "Login failed: Home page not visible"
        );

        return homePage;
    }
}