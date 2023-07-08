package pgs.hms.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class JsonFileReader {

	@Test
	
	public void jsonFileReader() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonP= new JSONParser();
		Object obj=jsonP.parse(new FileReader("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\JSONcommonData.json"));
		JSONObject map=(JSONObject)obj;
		Object url = map.get("url");
		System.out.println(url);
		
	}
}
