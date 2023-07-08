/**
 * 
 */
package com.algonquin.Capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wbr
 * Creates a connection to the DataBase
 * Source for code
 *  https://www.linkedin.com/learning/java-ee-servlets-and-javaserver-pages-jsp/search-setting-up-a-database-connection?resume=false&u=2199673
 */
public class DBConnection {
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/RestuarantReviews";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static DBConnection dbConnection;
    private static Connection connection;
    
    
    private DBConnection() {
    	
    }
    
    /**
     * Uses the singleton design pattern to return only one DBConnection instance. 
     * @return the DBConnection
     */
    public static synchronized DBConnection getDBConnection() {
    	if (dbConnection == null) {
    		dbConnection = new DBConnection();
    	}
    	return dbConnection;
    }

    /**
     * Creates and returns a connection the database
     * @return the connection. 
     * @throws ClassNotFoundException 
     */
    public static synchronized Connection getConnectionToDatabase()  {	
    	    	
       connection = null;
       try {		
    	    Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
		}catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

		}

		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		if (connection != null) {
			//System.out.println("Connection made to DB!");
		}
        

        return connection;
    }

}
