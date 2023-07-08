package pgs.hms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class VerifyProjectIsCreated {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        String project_Name = "TYSS";

        try {
            // Register the MySQL driver
            Driver driverRef = new Driver();
            DriverManager.registerDriver(driverRef);

            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");

            // Create a statement for executing SQL queries
            Statement statement = conn.createStatement();

            // Define the SQL query for fetching all projects
            String query = "SELECT * FROM project";

            // Execute the query and get the result set
            ResultSet result = statement.executeQuery(query);

            boolean flag = false;

            // Iterate over the result set and check if the project is created
            while (result.next()) {
                String value = result.getString(4);
                if (value.equalsIgnoreCase(project_Name)) {
                    System.out.println("Project is created");
                    flag = true;
                    break;
                }
            }

            // If the project is not found, display appropriate message
            if (!flag) {
                System.out.println("Project is NOT created");
            }

        } finally {
            // Close the database connection
            if (conn != null) {
                conn.close();
            }
        }
    }
}
