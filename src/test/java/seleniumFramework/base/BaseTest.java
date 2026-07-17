package seleniumFramework.base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import seleniumFramework.pages.CartPage;
import seleniumFramework.pages.LoginPage;
import seleniumFramework.utilities.ConfigReader;

/**
 * Base test class responsible for browser initialization, application launch,
 * and test cleanup.
 *
 * All test classes should extend this class.
 */
public class BaseTest {

	public WebDriver driver;

	/**
	 * Initializes the browser and launches the application before each test method.
	 *
	 * @throws IOException if config.properties cannot be loaded
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {

		String browser = System.getProperty("browserName") != null ? System.getProperty("browserName")
				: ConfigReader.getProperty("browser");

		driver = DriverFactory.initDriver(browser);
		driver.get(ConfigReader.getProperty("url"));

	}

	/**
	 * Performs login using the supplied credentials.
	 *
	 * @param email    User email address
	 * @param password User password
	 */
	public void login(String email, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(email, password);
	}

	/**
	 * Adds the specified product to the shopping cart and opens the Cart page.
	 *
	 * @param item Product name
	 */
	public void addToCart(String item) {
		CartPage cartPage = new CartPage(driver);
		cartPage.addProductToCart(item);

		cartPage.viewCart();

	}

	/**
	 * Closes the browser and releases the WebDriver instance.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
