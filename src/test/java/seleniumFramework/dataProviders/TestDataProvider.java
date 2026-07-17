package seleniumFramework.dataProviders;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

	/**
	 * Reads test data from JSON file and provides it to TestNG test methods.
	 *
	 * @return Object[][] containing test data
	 * @throws IOException if file reading fails
	 */
	@DataProvider
	public Object[][] getData() throws IOException {
		DataReader reader = new DataReader();
		List<HashMap<String, String>> data = reader.getJsonData(new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\seleniumFramework\\data\\loginData.json"));

		Object[][] loginData = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			loginData[i][0] = data.get(i);
		}
		return loginData;

	}

	/**
	 * Reads payment card test data from JSON file and provides it to TestNG test
	 * methods.
	 *
	 * @return Object[][] containing payment card data
	 * @throws IOException if file reading fails
	 */
	@DataProvider
	public Object[][] getCardData() throws IOException {
		DataReader reader = new DataReader();
		List<HashMap<String, String>> data = reader.getJsonData(new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\seleniumFramework\\data\\paymentData.json"));

		Object[][] cardData = new Object[data.size()][1];

		for (int i = 0; i < data.size(); i++) {
			cardData[i][0] = data.get(i);
		}
		return cardData;
	}

}
