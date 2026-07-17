package seleniumFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumFramework.base.BaseTest;
import seleniumFramework.base.Retry;
import seleniumFramework.pages.CartPage;
import seleniumFramework.pages.NavigationBar;
import seleniumFramework.utils.ConfigReader;

public class CartTest extends BaseTest {

	private CartPage cartPage;
	private NavigationBar navbar;
	String productName1 = "Blue Top";
	String productName2 = "Winter Top";

	/**
	 * Setup method executed before each test. Logs in user and navigates to
	 * Products page.
	 * @throws IOException 
	 */
	@BeforeMethod(alwaysRun = true)
	public void setupCartTest() throws IOException {
		navbar = new NavigationBar(driver);
		navbar.goToLoginSignup();
		login(ConfigReader.getProperty("CartPageEmail"), ConfigReader.getProperty("CartPagePassword"));
		navbar.goToProducts();
		cartPage = new CartPage(driver);
	}

	/**
	 * Verify user can add a product to cart successfully.
	 */
	@Test(groups = { "regression","smoke", "sanity" }, retryAnalyzer=Retry.class)
	public void shouldAddProductToCart() {
		cartPage.addProductToCart(productName2);
		Assert.assertTrue(cartPage.isProductAddedToCart(), "Product was not added to the cart.");
		cartPage.viewCart();
	}

	/**
	 * Verify user can remove a product from cart successfully.
	 */
	@Test(groups = { "regression","sanity" })
	public void shouldRemoveProductFromCart() {

		cartPage.addProductToCart(productName1);
		cartPage.viewCart();
		Assert.assertTrue(cartPage.removeProduct(productName1), "Product could not be removed");

	}

	/**
	 * Verify user can add multiple products to cart.
	 */
	@Test(groups={"regression"})
	public void shouldAddMultipleProductsToCart() {

		cartPage.addProductToCart(productName1);
		cartPage.continueShopping();
		cartPage.addProductToCart(productName2);
		cartPage.viewCart();
		Assert.assertTrue(cartPage.getProductCount() >= 2, "Products should be present in cart.");

	}

}
