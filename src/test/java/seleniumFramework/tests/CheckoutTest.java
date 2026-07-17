package seleniumFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumFramework.base.BaseTest;
import seleniumFramework.pages.CartPage;
import seleniumFramework.pages.CheckoutPage;
import seleniumFramework.pages.NavigationBar;
import seleniumFramework.utilities.ConfigReader;

public class CheckoutTest extends BaseTest {
	private CheckoutPage checkoutPage;
	String product = "Blue Top";
	private CartPage cartPage;
	private NavigationBar navbar;

	/**
	 * Setup method executed before every checkout test. Logs in user and adds
	 * product to cart.
	 * @throws IOException 
	 */
	@BeforeMethod(alwaysRun = true)
	public void setupCheckoutTest() throws IOException {
		navbar = new NavigationBar(driver);
		navbar.goToLoginSignup();
		login(ConfigReader.getProperty("CheckoutPageEmail"), ConfigReader.getProperty("CheckoutPagePassword"));
		addToCart(product);
		cartPage = new CartPage(driver);

	}

	/**
	 * Verify billing address and delivery address are same.
	 */
	@Test(groups={"regression"})
	public void verifyDeliveryAddress() {
		checkoutPage = cartPage.goToCheckout();
		Assert.assertEquals(checkoutPage.getBillingAddress(), checkoutPage.getDeliveryAddress());

	}

	/**
	 * Verify added product is displayed on checkout page.
	 */
	@Test(groups={"regression"})
	public void verifyProductOnCheckout() {
		checkoutPage = cartPage.goToCheckout();
		Assert.assertTrue(checkoutPage.isProductPresent(product));

	}

}
