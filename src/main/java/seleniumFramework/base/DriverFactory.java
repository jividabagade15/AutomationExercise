package seleniumFramework.base;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

	// ThreadLocal ensures each parallel test gets its own WebDriver instance.
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	/**
	 * Initializes a browser instance based on the browser name.
	 *
	 * @param browser Browser name (chrome, chrome-headless, edge, firefox, etc.)
	 * @return WebDriver instance for the current thread
	 */
	public static WebDriver initDriver(String browser) {
		String browserName=browser.toLowerCase();
		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");

			}
			driver.set(new ChromeDriver(options));
			driver.get().manage().window().setSize(new Dimension(1920, 1080));
		} else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");

			}
			driver.set(new EdgeDriver(options));
			driver.get().manage().window().setSize(new Dimension(1920, 1080));

		} else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");

			}
			driver.set(new FirefoxDriver(options));
			driver.get().manage().window().setSize(new Dimension(1920, 1080));

		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browserName);

		}

		if (!browserName.contains("headless")) {
			getDriver().manage().window().maximize();
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return getDriver();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	// Close browser and remove driver from ThreadLocal to prevent memory leaks.
	public static void quitDriver() {
		getDriver().quit();
		driver.remove();
	}
}
