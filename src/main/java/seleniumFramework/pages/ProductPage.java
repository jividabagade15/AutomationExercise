package seleniumFramework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.base.BasePage;

/**
 * Page Object representing the Products page.
 *
 * Provides methods to search products, verify product listings, and navigate to
 * the product details page.
 */
public class ProductPage extends BasePage {

	NavigationBar navbar;

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		navbar = new NavigationBar(driver);
	}

	@FindBy(css = ".product-image-wrapper")
	private List<WebElement> productCards;

	@FindBy(css = "[placeholder='Search Product']")
	private WebElement searchBar;

	@FindBy(id = "submit_search")
	private WebElement searchButton;

	@FindBy(className = "product-image-wrapper")
	private List<WebElement> searchedProducts;

	@FindBy(css = ".productinfo p")
	private WebElement productName;

	@FindBy(xpath = "//div[@class='product-information']/h2")
	private WebElement productHeader;

	@FindBy(xpath = "//h2[text()='Searched Products']")
	private WebElement searchedElement;

	@FindBy(css = ".col-sm-9")
	private WebElement productDetailsSection;

	private By productNameLocator = By.cssSelector(".single-products p");

	/**
	 * Verifies that products are displayed on the Products page.
	 *
	 * @return true if at least one product is displayed
	 */
	public boolean isProductsListDisplayed() {
		handleGoogleVignetteAd(() -> navbar.goToProducts());
		return productCards.size() > 0;
	}

	// returns true if product name is displayed on products page
	public boolean isProductNameDisplayed() {
		return productName.isDisplayed();
	}

	/**
	 * Searches for the specified product.
	 *
	 * @param name Product name to search
	 */
	public void searchProduct(String name) {
		handleGoogleVignetteAd(() -> navbar.goToProducts());
		waitUntilVisible(searchBar);
		searchBar.click();
		searchBar.sendKeys(name);
		searchButton.click();

	}

	/**
	 * Verifies that all displayed search results contain the searched product name.
	 *
	 * @param name Product name
	 * @return true if all displayed products match
	 */
	public boolean areMatchingProductsDisplayed(String name) {
		waitUntilVisible(searchedElement);
		return searchedProducts.stream()
				.allMatch(product -> product.findElement(productNameLocator).getText().contains(name));

	}

	/**
	 * Opens the details page for the specified product.
	 *
	 * @param prodName Product name
	 */
	public void clickOnProduct(String prodName) {

		handleGoogleVignetteAd(() -> navbar.goToProducts());
		scrollToElement(productCards.get(0));
		handleGoogleVignetteAd(() -> navbar.goToProducts());
		WebElement selectedProduct = productCards.stream()
				.filter(product -> product.findElement(productNameLocator).getText().equals(prodName)).findFirst()
				.orElseThrow(() -> new RuntimeException("Product not found: " + prodName));
		WebElement viewProduct = selectedProduct.findElement(By.cssSelector(".choose a"));
		waitUntilClickable(viewProduct);
		viewProduct.click();
	}

	/**
	 * Verifies that the correct product details page is displayed.
	 *
	 * @param prodName Expected product name
	 * @return true if product details match
	 */
	public boolean isProductDetailsDisplayed(String prodName) {
		waitUntilVisible(productDetailsSection);
		return productHeader.getText().equals(prodName);
	}

}
