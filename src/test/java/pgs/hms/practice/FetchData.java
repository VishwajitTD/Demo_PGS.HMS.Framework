package pgs.hms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchData {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        String query = "SELECT * FROM project";

        try {
            // Register the MySQL driver
            Driver driverRef = new Driver();
            DriverManager.registerDriver(driverRef);

            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");

            // Create a statement for executing SQL queries
            Statement statement = conn.createStatement();

            // Execute the query and retrieve the results
            ResultSet result = statement.executeQuery(query);

            // Iterate over the result set and print the values
            while (result.next()) {
                String value = result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " "
                        + result.getString(4) + " " + result.getString(5) + " " + result.getInt(6) + " ";
                System.out.println(value);
            }

        } finally {
            // Close the database connection
            if (conn != null) {
                conn.close();
            }
        }
    }
}
