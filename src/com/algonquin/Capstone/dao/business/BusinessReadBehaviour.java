package com.algonquin.Capstone.dao.business;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Defines the interface for searching businesses in the database. 
 */
public interface BusinessReadBehaviour {
	
	/**
	 * Returns a prepared statement to execute the requested DB read behaviour, using a keyword to search for. 
	 * @param numBusiness the number of businesses to return. 
	 * @param keyword the keyword to search for in the business name, description and food type. 
	 * @return the prepared statement 
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(int numBusiness, String keyword) throws SQLException;

}
