package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumFramework.base.BaseTest;
import seleniumFramework.pages.NavigationBar;
import seleniumFramework.pages.ProductPage;
import seleniumFramework.base.Retry;
public class ProductTest extends BaseTest {

	private ProductPage productPage;
	private NavigationBar navbar;
	String searchItem = "Stylish";
	String product = "Blue Top";

	/**
	 * Setup method executed before every product test.
	 * Navigates user to Products page.
	 */
	@BeforeMethod(alwaysRun=true)
	public void setupProductPage() {
		navbar = new NavigationBar(driver);
		productPage = navbar.goToProducts();
		
	}

	/**
	 * Verify products list and product names are displayed.
	 */
	@Test(groups={"regression","smoke","sanity"})
	public void shouldDisplayProductList() {
		Assert.assertTrue(productPage.isProductsListDisplayed(), "Product List should be displayed.");
		Assert.assertTrue(productPage.isProductNameDisplayed(), "Product name should be displayed.");
	}

	/**
	 * Verify user can search product and matching results are displayed.
	 */
	@Test(groups={"regression","sanity"})
	public void shouldDisplayMatchingSearchResults() {
		productPage.searchProduct(searchItem);
		Assert.assertTrue(productPage.areMatchingProductsDisplayed(searchItem),
				"Search results should contain only matching products.");
	}

	/**
	 * Verify product details page is displayed for selected product.
	 */
	@Test(retryAnalyzer=Retry.class,groups={"regression","smoke","sanity"})
	public void shouldDisplayProductDetails() {
		productPage.clickOnProduct(product);
		Assert.assertTrue(productPage.isProductDetailsDisplayed(product),
				"Product details page should display the selected product.");
	}

}
