package com.algonquin.Capstone.dao.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.algonquin.Capstone.dao.DBConnection;

/**
 * Searches for the most expensive businesses.
 */
public class BusinessReadPriceHighLow implements BusinessReadBehaviour{

	@Override
	public PreparedStatement getPreparedStatement(int numBusiness, String keyword) throws SQLException {
		// Create DB Connection
		Connection connection = DBConnection.getConnectionToDatabase();	
		// Create select statement 
		PreparedStatement readBusiness = connection.prepareStatement(
				"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
						+ " FROM business"
						+ " WHERE FoodType LIKE ? OR Description LIKE ? OR Name LIKE ?"
						+ " ORDER BY PriceRating DESC"
						+ " LIMIT ?;"			
				);
		readBusiness.setString(1, "%" + keyword + "%"); 
		readBusiness.setString(2, "%" + keyword + "%"); 
		readBusiness.setString(3, "%" + keyword + "%"); 
		readBusiness.setInt(4, numBusiness);	
	return readBusiness;
	}

}
