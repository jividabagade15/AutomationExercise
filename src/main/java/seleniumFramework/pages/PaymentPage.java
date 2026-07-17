package seleniumFramework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.base.BasePage;

/**
 * Page Object representing the Payment page.
 *
 * Provides methods to enter payment details,
 * submit the payment, and verify order confirmation.
 */
public class PaymentPage extends BasePage {

	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "name_on_card")
	private WebElement nameOnCard;

	@FindBy(className = "card-number")
	private WebElement cardNumber;

	@FindBy(name = "cvc")
	private WebElement cvc;

	@FindBy(css = "[placeholder='MM']")
	private WebElement expirationMonth;

	@FindBy(css = "[placeholder='YYYY']")
	private WebElement expirationYear;

	@FindBy(id = "submit")
	private WebElement payAndPlaceOrderButton;

	@FindBy(xpath = "//h2/b")
	private WebElement orderPlaced;

	/**
	 * Verifies whether the Payment page is displayed.
	 *
	 * @return true if the payment page is displayed
	 */
	public boolean isPaymentPageDisplayed() {
//		handleGoogleVignetteAd(() -> driver.navigate().back());
		waitUntilVisible(payAndPlaceOrderButton);
		return payAndPlaceOrderButton.isDisplayed();
	}

	/**
	 * Enters payment details required to place an order.
	 *
	 * @param name Card holder name
	 * @param cardNum Card number
	 * @param cvcNum Card security code
	 * @param month Expiry month
	 * @param year Expiry year
	 */
	public void enterPaymentDetails(String name, String cardNum, String cvcNum, String month, String year) {
		waitUntilVisible(nameOnCard);
		nameOnCard.sendKeys(name);
		handleGoogleVignetteAd(() -> driver.navigate().back());
		cardNumber.sendKeys(cardNum);
		cvc.sendKeys(cvcNum);
		expirationMonth.sendKeys(month);
		expirationYear.sendKeys(year);
	}

	/**
	 * Clicks the Pay and Place Order button.
	 */
	public void clickPayAndPlaceOrder() {
		handleGoogleVignetteAd(() -> driver.navigate().back());
		scrollToElement(payAndPlaceOrderButton);
		handleGoogleVignetteAd(() -> driver.navigate().back());
		waitUntilClickable(payAndPlaceOrderButton);
		payAndPlaceOrderButton.click();
	}

	/**
	 * Returns the order confirmation message.
	 *
	 * @return Order confirmation text
	 */
	public String getOrderConfirmation() {
		waitUntilVisible(orderPlaced);
		return orderPlaced.getText();
	}

}
