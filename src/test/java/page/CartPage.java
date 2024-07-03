package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By popupMessage = By.xpath("//div[@id='layer_cart']//h2[contains(text(), 'Producto añadido correctamente a su carrito de compra')]");
    By totalPrice = By.xpath("//span[@id='layer_cart_product_price']");
    By checkoutButton = By.xpath("//a[@title='Proceder a la compra']");
    By cartTotal = By.xpath("//span[@id='total_price']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductAddedMessageDisplayed() {
        return driver.findElement(popupMessage).isDisplayed();
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public String getCartTotal() {
        return driver.findElement(cartTotal).getText();
    }
}
