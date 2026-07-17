package seleniumFramework.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFramework.base.BasePage;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='address item box']/li[position()>1]")
	private WebElement deliveryAddress;

	@FindBy(xpath = "//ul[@class='address alternate_item box']/li[position()>1]")
	private WebElement billingAddress;

	@FindBy(css = ".table-condensed tbody .cart_description h4")
	private List<WebElement> checkoutProducts;

	@FindBy(xpath = "//a[text()='Place Order']")
	private WebElement placeOrderButton;

	/**
	 * Returns the delivery address displayed on the checkout page.
	 *
	 * @return Delivery address
	 */
	public String getDeliveryAddress() {
		waitUntilVisible(deliveryAddress);
		return deliveryAddress.getText();
	}

	/**
	 * Returns the billing address displayed on the checkout page.
	 *
	 * @return Billing address
	 */
	public String getBillingAddress() {
		waitUntilVisible(billingAddress);
		return billingAddress.getText();
	}

	/**
	 * Verifies whether the specified product is displayed in the checkout summary.
	 *
	 * @param productName Name of the product to verify
	 * @return true if the product is present, otherwise false
	 */
	public boolean isProductPresent(String productName) {
		
		if (checkoutProducts.isEmpty()) {
			throw new IllegalStateException("No products found on the checkout page.");
		}
		scrollToElement(checkoutProducts.get(0));
		return checkoutProducts.stream().anyMatch(product -> product.getText().contains(productName));
	}

	/**
	 * Places the order and navigates to the payment page.
	 *
	 * @return PaymentPage instance
	 */
	public PaymentPage placeOrder() {
		waitUntilVisible(billingAddress);
		scrollToElement(placeOrderButton);
		handleGoogleVignetteAd(() -> driver.navigate().back());

		waitUntilClickable(placeOrderButton);
		placeOrderButton.click();
		handleGoogleVignetteAd(() -> driver.get("https://automationexercise.com/payment"));

		return new PaymentPage(driver);
	}

}
