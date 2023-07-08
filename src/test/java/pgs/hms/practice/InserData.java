package pgs.hms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InserData {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {
            // Register the MySQL driver
            Driver driverRef = new Driver();
            DriverManager.registerDriver(driverRef);

            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");

            // Create a statement for executing SQL queries
            Statement statement = conn.createStatement();

            // Define the SQL query for data insertion
            String query = "INSERT INTO project VALUES ('TY_Proj_HMS123', 'Jeet', '29/06/2023', 'TYSS', 'Completed', '3')";

            // Execute the query and get the number of affected rows
            int result = statement.executeUpdate(query);

            // Check if the data was successfully inserted
            if (result == 1) {
                System.out.println("Data is created");
            } else {
                System.out.println("Data is NOT created");
            }

        } finally {
            // Close the database connection
            if (conn != null) {
                conn.close();
            }
        }
    }
}
