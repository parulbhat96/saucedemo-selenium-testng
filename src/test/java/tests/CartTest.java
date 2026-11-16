package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    InventoryPage inventoryPage;

    @BeforeMethod
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void addOneItemToCart() {
        inventoryPage.addToCart("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartCount(), 1);
    }

    @Test
    public void addTwoItemsToCart() {
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getItemNames().size(), 2);
        Assert.assertTrue(cartPage.getItemNames().contains("Sauce Labs Backpack"));
    }

    @Test
    public void removeItemFromCart() {
        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("Sauce Labs Backpack");

        Assert.assertEquals(cartPage.getItemNames().size(), 0);
    }
}