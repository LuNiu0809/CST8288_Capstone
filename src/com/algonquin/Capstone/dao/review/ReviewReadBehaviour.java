package com.algonquin.Capstone.dao.review;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Defines the interface for searching the database for Business reviews. 
 */
public interface ReviewReadBehaviour {
	
	/**
	 * Returns a prepared statement to execute the requested DB read behaviour
	 * @param businessID the business read reviews for
	 * @param numReviews the number of reviews to return 
	 * @return the prepared statement 
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(int businessID, int numReviews) throws SQLException;

}
