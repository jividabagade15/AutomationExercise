package seleniumFramework.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base page containing reusable Selenium utility methods shared across all page
 * classes.
 */
public class BasePage {

	protected WebDriver driver;
	WebDriverWait wait;

	/**
	 * Initializes the WebDriver and explicit wait used by all page objects.
	 *
	 * @param driver WebDriver instance for the current test
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void waitUntilVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Executes the supplied action when a Google Vignette advertisement is
	 * displayed.
	 *
	 * @param action Action to perform after the vignette is detected
	 */
	public void handleGoogleVignetteAd(Runnable action) {
		if (driver.getCurrentUrl().contains("google_vignette")) {
			action.run();
		}
	}

	/**
	 * Scrolls the page until the specified element is centered in the viewport.
	 *
	 * @param element WebElement to scroll into view
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	}

}
