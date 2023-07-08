package pgh.hms.generic.genericUtility;

import java.io.FileInputStream;

import java.util.Properties;

/**
 * 
 * @author jeet
 *
 */

public class FileUtility {
	/**
	 * Used to get the file path from the ./config/filePath.properties file
	 * 
	 * @param key
	 * @return value
	 */
	public String getFilePathFromPropertiesFile(String key) {
		FileInputStream fis = null;
		Properties pObj=null;
		try {
			// Open the properties file
			fis = new FileInputStream("./config/filePath.properties");
		 pObj = new Properties();
		
			// Load the properties from the input stream
			pObj.load(fis);} 
		catch (Exception e) {
			e.printStackTrace();}
		// Get the value associated with the given key
		String value = pObj.getProperty(key);
		return value;
	}

	/**
	 * Used to get the value associated with a key from a properties file based on
	 * the given file path
	 * 
	 * @param filePath
	 * @param key
	 * @return value
	 */
	public String getDataFromProperties(String filePath, String key) {
		FileInputStream fis = null;
		Properties pObj=null;
		try {
			// Open the properties file
			fis = new FileInputStream(filePath);
		pObj = new Properties();
			// Load the properties from the input stream
			pObj.load(fis);}
			catch (Exception e) {
			e.printStackTrace();
		}
		// Get the value associated with the given key
		String value = pObj.getProperty(key);
		return value;
	}
}
