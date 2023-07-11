package pgh.hms.generic.genericUtility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;
/**
 * 
 * @author jeet
 *
 */
public class DataBaseUtility {
	Driver driver;
	Connection connection;
	ResultSet result;
	FileUtility fLib = new FileUtility();
	String url;
	String username;
	String password;
	
    
	 public DataBaseUtility(){
			try {
				String filePath = fLib.getFilePathFromPropertiesFile("dataBaseConfigFilePath");
				url = fLib.getDataFromProperties(filePath, "url");
				username = fLib.getDataFromProperties(filePath, "username");
				password = fLib.getDataFromProperties(filePath, "password");
			} catch (Throwable e) {
				// TODO: handle exception
			}
	 }
	
	/**
	 * This method will perform the mysql database connection
	 * @throws SQLException 
	 */
	public void connectDB() {
		try {
			driver=new Driver();
			DriverManager.registerDriver(driver);
			connection=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method will close the mysql database
	 * @throws SQLException
	 */
	public void closeDB() {
		try {
		  connection.close();
		}catch (Exception e) {
		}


	}
	/**
	 * This method will execute the Query
	 * @param query
	 * @return
	 * @throws Throwable
	 */
	public ResultSet execyteQuery(String query) {	
			try {
				result = connection.createStatement().executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;		
	}
	
	/**
	 * This method will execute the query
	 * @param query
	 * @return
	 * @throws Throwable
	 */
	public int executeUpdate(String query) {
		
			int result = 0;
			try {
				result = connection.createStatement().executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return result;

	}
	/**
	 * This method will execute query based on query and it will verify the data. 
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public boolean executeQuerryAndVerify(String query,int cloumnIndex,String expectedData)  {
		boolean flag=false;
		try {
			result=connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()) {
				if(result.getString(cloumnIndex).equals(expectedData)) {
					flag=true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			System.out.println(expectedData+"==>Data is verified in the data base table");
			return flag;
		}else {
			System.out.println(expectedData+"==>data is not verified in the database");
			return flag;
		}
         
	}
}




