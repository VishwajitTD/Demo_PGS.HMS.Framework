package practiseProgram;

import pgh.hms.generic.genericUtility.FileUtility;
import pgh.hms.generic.genericUtility.JavaUtility;

public class CLassCrossCheck {

	public static void main(String[] args) {
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();

		// get the filePath
		String envtFilePath = fLib.getFilePathFromPropertiesFile("projectConfigDataFilePath");

		//read the common data
		String browser = fLib.getDataFromProperties(envtFilePath, "browser");
		String url = fLib.getDataFromProperties(envtFilePath, "url");
		String adminUsernameP = fLib.getDataFromProperties(envtFilePath, "adminUsernameP");
		String adminPasswordP = fLib.getDataFromProperties(envtFilePath, "adminPasswordP");
		int rNumber = jLib.getRandomNumber();
		System.out.println(browser);
		System.out.println(url);
		System.out.println(adminUsernameP);
		System.out.println(adminPasswordP);
		System.out.println(rNumber);
	}

}
