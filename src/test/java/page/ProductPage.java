package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    By quantityInput = By.xpath(".//input[@name='qty']");
    By addToCartButton = By.xpath(".//button[contains(@class, 'add-to-cart')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setQuantity(int quantity) {
        driver.findElement(quantityInput).clear();
        driver.findElement(quantityInput).sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
