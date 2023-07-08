package practiseProgram;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleDateClass {

    public static void main(String[] args) {
        // Get the current date and time
        Date currentDate = new Date();

        // Define the date format pattern as "yyyy-MM-dd" (year-month-day)
        String dateFormatPattern = "yyyy-MM-dd";

        // Create a SimpleDateFormat object to format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        // Format the current date to a string using the specified pattern
        String formattedDate = dateFormat.format(currentDate);

        // Print the formatted date
        System.out.println("Formatted Date: " + formattedDate);
    }

}
