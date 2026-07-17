package seleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.base.BasePage;

public class CartPage extends BasePage {
	NavigationBar navbar;

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		navbar = new NavigationBar(driver);
	}

	@FindBy(css = ".single-products")
	private List<WebElement> products;

	@FindBy(className = "modal-content")
	private WebElement addToCartModal;

	@FindBy(css = " tbody tr")
	private List<WebElement> cartItems;

	@FindBy(id = "cart_info_table")
	private WebElement cartTable;

	@FindBy(css = ".modal-content .btn-success")
	private WebElement continueShoppingBtn;

	@FindBy(xpath = "//a[text()='Proceed To Checkout']")
	private WebElement proceedToCheckout;

	private By cartLink = By.linkText("View Cart");

	private By productNameLocator = By.cssSelector("td h4");
	private By deleteItemLocator = By.cssSelector("td.cart_delete a");
	private By addToCart=By.cssSelector(".add-to-cart");
	private By addToCartBtn = By.xpath("//div[@class='overlay-content']/a[text()='Add to cart']");

	/**
	 * Adds the specified product to the shopping cart.
	 *
	 * @param searchItem Product name to add
	 */
	public void addProductToCart(String searchItem) {
		Actions action = new Actions(driver);
		handleGoogleVignetteAd(() -> navbar.goToProducts());

		for (WebElement product : products) {
			if (product.findElement(By.tagName("p")).getText().contains(searchItem)) {
				try {
//					scrollToElement(product);
					scrollToElement(product.findElement(addToCart));
					action.moveToElement(product.findElement(By.tagName("img"))).build().perform();
//					handleGoogleVignetteAd(() -> driver.navigate().back());
					waitUntilVisible(addToCartBtn);
					product.findElement(addToCartBtn).click();
					break;
				} catch (WebDriverException e) {
					
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",
							product.findElement(addToCartBtn));
					break;
				}
			}

		}
	}

	public boolean isProductAddedToCart() {
		waitUntilVisible(continueShoppingBtn);
		return continueShoppingBtn.isDisplayed();
	}

	/**
	 * Continues shopping by closing the add-to-cart modal.
	 */
	public void continueShopping() {
		handleGoogleVignetteAd(() -> driver.navigate().back());
		waitUntilClickable(continueShoppingBtn);
		continueShoppingBtn.click();
		waitUntilInvisible(addToCartModal);
	}

	public int getProductCount() {
		waitUntilVisible(cartTable);
		return cartItems.size();
	}

	/**
	 * Removes the specified product from the cart.
	 *
	 * @param product Product name
	 * @return true if product is removed successfully
	 */
	public boolean removeProduct(String product) {

		WebElement item = cartItems.stream().filter(i -> i.findElement(productNameLocator).getText().equals(product))
				.findFirst().orElseThrow(() -> new RuntimeException("Product not found: " + product));
		item.findElement(deleteItemLocator).click();
		waitUntilInvisible(item);
		// to check if removed product is still present

		return cartItems.stream().noneMatch(i -> i.findElement(productNameLocator).getText().equals(product));

	}

	public void viewCart() {
		handleGoogleVignetteAd(()-> driver.navigate().back());
		WebElement viewCartLink = addToCartModal.findElement(cartLink);
		waitUntilClickable(viewCartLink);
		viewCartLink.click();
		waitUntilVisible(cartTable);
		handleGoogleVignetteAd(() -> driver.navigate().back());
	}

	public CheckoutPage goToCheckout() {
		try {
		waitUntilClickable(proceedToCheckout);
		proceedToCheckout.click();
		handleGoogleVignetteAd(() -> driver.navigate().back());

		}
		catch(WebDriverException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",
					proceedToCheckout);
		}
		return new CheckoutPage(driver);
	}

}
