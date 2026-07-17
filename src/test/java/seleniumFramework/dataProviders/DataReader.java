package seleniumFramework.dataProviders;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for reading test data from JSON files.
 */
public class DataReader {

	/**
	 * Reads test data from a JSON file.
	 *
	 * @param jsonFile JSON file containing test data
	 * @return List of test data as key-value pairs
	 * @throws IOException if the file cannot be read
	 */
	public List<HashMap<String, String>> getJsonData(File filePath) throws IOException {

		// read JSON file into string
		String content = FileUtils.readFileToString(filePath, StandardCharsets.UTF_8);

		// convert string into List<HashMap<String, String>>
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(content,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
