package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLogin() {
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test
    public void invalidLogin() {
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"),
                "Expected error message was not shown");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}