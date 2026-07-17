package seleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.base.BasePage;

/**
 * Page Object representing the common navigation bar available throughout the
 * Automation Exercise application.
 *
 * Provides navigation methods to different application pages.
 */
public class NavigationBar extends BasePage {

	public NavigationBar(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// homepage
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[1]/a")
	private WebElement homeLink;

	// products page
	By productsLink = By.xpath("//ul[@class='nav navbar-nav']/li[2]/a");

	// cart page
	@FindBy(xpath = "//ul[@class='nav navbar-nav']/li[3]/a")
	private WebElement cartLink;

	// login signup page
	@FindBy(linkText = "Signup / Login")
	private WebElement loginSignupLink;

	/**
	 * Opens the Home page.
	 */
	public void goToHomePage() {
		homeLink.click();

	}

	/**
	 * Opens the Products page.
	 *
	 * @return ProductPage object
	 */
	public ProductPage goToProducts() {
		handleGoogleVignetteAd(() -> driver.get("https://automationexercise.com/products"));
		waitUntilClickable(productsLink);
		driver.findElement(productsLink).click();
		return new ProductPage(driver);
	}

	/**
	 * Navigates to the Cart page.
	 *
	 * @return CartPage instance
	 */
	public CartPage goToCart() {
		waitUntilClickable(cartLink);
		cartLink.click();
		return new CartPage(driver);
	}

	/**
	 * Opens the Login / Signup page.
	 *
	 * @return LoginPage object
	 */
	public LoginPage goToLoginSignup() {
		waitUntilClickable(loginSignupLink);
		try {
			loginSignupLink.click();
			handleGoogleVignetteAd(() -> driver.navigate().back());
		} catch (ElementClickInterceptedException e) {
		    // Fallback to JavaScript click if normal click is intercepted.

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginSignupLink);
		}
		return new LoginPage(driver);
	}
}
