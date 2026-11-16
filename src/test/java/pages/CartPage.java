package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;

    private By itemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getItemNames() {
        List<WebElement> items = driver.findElements(itemNames);
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void removeItem(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}