package seleniumFramework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static Properties prop;
	
	public static String getProperty(String key) throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);
		
		return prop.getProperty(key);
	}
}
