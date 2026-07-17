package seleniumFramework.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries failed test cases to handle intermittent failures such as network
 * latency or timing issues.
 */
public class Retry implements IRetryAnalyzer {
	int retryCount = 0;
	int maxRetryCount = 2;

	/**
	 * Determines whether a failed test should be retried.
	 *
	 * @param result Failed test result
	 * @return true if retry is required, otherwise false
	 */
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

}
