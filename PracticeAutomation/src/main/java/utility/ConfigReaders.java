package utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReaders {
public static final String configFilepath = GlobalVariable.basepath+"//config.properties";
	
	public static String getProperty(String ConfigKey) {
		Properties prop = new Properties();
		InputStream input = null;
		String ConfigValue = null;
		try {
			input = new FileInputStream(configFilepath);
			prop.load(input);
			ConfigValue = prop.getProperty(ConfigKey);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				input.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ConfigValue;
	}
}
