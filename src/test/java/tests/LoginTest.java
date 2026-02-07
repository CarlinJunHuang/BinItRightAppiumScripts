package tests;

import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class LoginTest extends ProjectSpecificationMethods {

    @Test
    public void loginWithValidCredentials() {
        LoginPage login = new LoginPage(driver);

        login.login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );


        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHomePageDisplayed(),
                "Home page should be visible after valid login");
    }

//    @Test
//    public void loginWithInvalidCredentials() {
//        LoginPage login = new LoginPage(driver);
//        login.login("wronguser", "wrongpass");
//
//        Assert.assertTrue(login.isLoginPageDisplayed(),
//                "Login page should remain on invalid credentials");
//    }
}
