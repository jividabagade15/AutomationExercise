package seleniumFramework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[data-qa='login-email']")
	private WebElement loginEmail;

	@FindBy(name = "password")
	private WebElement userPassword;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	@FindBy(css = "form[action='/login'] p")
	private WebElement invalidText;

	@FindBy(xpath = "//li/a/b")
	private WebElement userName;

	/**
	 * Logs in using the provided credentials.
	 *
	 * @param email    User email
	 * @param password User password
	 */
	public void loginUser(String email, String password) {
		waitUntilVisible(loginEmail);

		loginEmail.sendKeys(email);
		handleGoogleVignetteAd(() -> driver.navigate().back());
		if (!loginEmail.getAttribute("value").equals(email)) {
			loginEmail.sendKeys(Keys.CONTROL + "a");
			loginEmail.sendKeys(Keys.DELETE);
			loginEmail.sendKeys(email);
		}
		userPassword.sendKeys(password);
//		handleGoogleVignetteAd(()-> driver.get("https://automationexercise.com/login"));
		waitUntilClickable(loginButton);

		loginButton.click();

	}

	/**
	 * Returns the username displayed after successful login.
	 *
	 * @return Logged-in username
	 */
	public String getLoggedInUserName() {
		handleGoogleVignetteAd(() -> driver.get("https://automationexercise.com/"));
		waitUntilVisible(userName);
		return userName.getText();
	}

	/**
	 * Returns the error message displayed for an invalid login attempt.
	 *
	 * @return Login error message
	 */
	public String getErrorMessage() {
		waitUntilVisible(invalidText);
		return invalidText.getText();
	}

}
