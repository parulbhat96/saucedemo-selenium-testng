package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtils.getSheetData("testdata/LoginData.xlsx", "Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedResult.equals("success")) {
            InventoryPage inventoryPage = new InventoryPage(driver);
            Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains(expectedResult),
                    "Expected error containing: " + expectedResult);
        }
    }
}