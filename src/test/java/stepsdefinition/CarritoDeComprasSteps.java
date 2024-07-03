package stepsdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.HomePage;
import page.LoginPage;
import page.ProductPage;
import page.CartPage;

import java.time.Duration;

public class CarritoDeComprasSteps {

    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe"); // Asegúrate de que esta ruta es correcta
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qalab.bensg.com/store");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        homePage.clickLoginLink();
        loginPage.enterUsername(usuario);
        loginPage.enterPassword(clave);
        loginPage.clickLoginButton();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        driver.findElement(By.xpath("//a[contains(text(), '" + categoria + "')]")).click();
        driver.findElement(By.xpath("//a[contains(text(), '" + subcategoria + "')]")).click();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        productPage.setQuantity(cantidad);
        productPage.addToCart();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        assert cartPage.isProductAddedMessageDisplayed();
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        String totalText = cartPage.getTotalPrice();
        double expectedTotal = 19.12 * 2; // Precio unitario * cantidad
        assert totalText.equals(String.format("S/ %.2f", expectedTotal));
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        cartPage.proceedToCheckout();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        String titulo = driver.getTitle();
        assert titulo.equals("Carrito");
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        String totalText = cartPage.getCartTotal();
        double expectedTotal = 19.12 * 2; // Precio unitario * cantidad
        assert totalText.equals(String.format("S/ %.2f", expectedTotal));
    }
}
