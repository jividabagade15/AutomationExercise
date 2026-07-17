package seleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumFramework.base.BaseTest;
import seleniumFramework.dataProviders.TestDataProvider;
import seleniumFramework.pages.LoginPage;
import seleniumFramework.pages.NavigationBar;
import seleniumFramework.utilities.ConfigReader;

public class LoginTest extends BaseTest {
	private LoginPage loginPage;

	private NavigationBar navbar;
	
	/**
	 * Setup method executed before each login test.
	 * Navigates user to login page.
	 */
	@BeforeMethod(alwaysRun=true)
	public void setupLoginTest() {
		navbar = new NavigationBar(driver);
		
		loginPage = navbar.goToLoginSignup();
		
	}

	/**
	 * Verify user can login successfully using valid credentials.
	 */
	@Test(dataProvider="getData",dataProviderClass=TestDataProvider.class,groups={"regression","smoke","sanity"})
	public void validLogin(HashMap<String,String> input){
		
		loginPage.loginUser(input.get("email"),input.get("password"));
		String loggedInUser =loginPage.getLoggedInUserName();
		// validation
		Assert.assertEquals(loggedInUser,"Allie","Logged-in username does not match.");

	}

	/**
	 * Verify user receives error message with invalid credentials.
	 * @throws IOException 
	 */	
	@Test(groups={"regression","sanity"})
	public void invalidLogin() throws IOException {
	
		loginPage.loginUser(ConfigReader.getProperty("InvalidEmail"),ConfigReader.getProperty("InvalidPassword"));
		// get error message for invalid data
		String errorMessage = loginPage.getErrorMessage();

		// validation
		Assert.assertEquals(errorMessage, "Your email or password is incorrect!");

	}
}
