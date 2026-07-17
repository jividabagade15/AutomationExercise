package seleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumFramework.base.BaseTest;
import seleniumFramework.dataProviders.TestDataProvider;
import seleniumFramework.pages.CartPage;
import seleniumFramework.pages.CheckoutPage;
import seleniumFramework.pages.NavigationBar;
import seleniumFramework.pages.PaymentPage;
import seleniumFramework.utilities.ConfigReader;

public class PaymentTest extends BaseTest {
	
	private PaymentPage paymentPage;
	private CheckoutPage checkoutPage;
	private NavigationBar navbar;
	private CartPage cartPage;
	String product="Blue Top";
	/**
	 * Setup method executed before every payment test.
	 * Logs in user, adds product to cart and navigates to payment page.
	 * @throws IOException 
	 */
	@BeforeMethod(alwaysRun=true)
	public void setupPaymentPage() throws IOException {
		navbar = new NavigationBar(driver);
		navbar.goToLoginSignup();
		login(ConfigReader.getProperty("PaymentPageEmail"),ConfigReader.getProperty("PaymentPagePassword"));
		addToCart(product);
		cartPage= new CartPage(driver);
		checkoutPage= cartPage.goToCheckout();
		
	}
	
	/**
	 * Verify user is redirected to payment page after placing order.
	 */
	@Test(groups={"regression","smoke"})
	public void shouldOpenPaymentPage(){
	
		paymentPage=checkoutPage.placeOrder();
		Assert.assertTrue(paymentPage.isPaymentPageDisplayed(), "Payment page is not displayed.");
	}

	/**
	 * Verify user can complete payment and order confirmation is displayed.
	 */
	@Test(dataProvider="getCardData", dataProviderClass=TestDataProvider.class,groups={"regression","smoke"})
	public void shouldDisplayOrderConfirmation(HashMap<String,String> input) {
		
		paymentPage=checkoutPage.placeOrder();
		paymentPage.enterPaymentDetails(input.get("name"),input.get("cardNumber"),input.get("cvv"),input.get("month"),input.get("year"));
		paymentPage.clickPayAndPlaceOrder();
		Assert.assertEquals(paymentPage.getOrderConfirmation(),"ORDER PLACED!","Order is not placed");
	}

}
