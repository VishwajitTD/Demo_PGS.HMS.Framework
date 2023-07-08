package practiseProgram;import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJason {

    public static void main(String[] args) {
        try {
            // Create a JSONParser object to parse the JSON file
            JSONParser parser = new JSONParser();

            // Parse the JSON file and store the parsed data in an object
            Object obj = parser.parse(new FileReader("C:\\Users\\User\\Desktop\\data\\appCommonData.json"));

            // Downcast the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Retrieve the value associated with the "browser" key and print it
            Object browserValue = jsonObject.get("browser");
            System.out.println("Browser: " + browserValue);
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        } catch (ParseException e) {
            // Handle parse exception
            e.printStackTrace();
        }
    }

}
