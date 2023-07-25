package com.algonquin.Capstone.dao.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.algonquin.Capstone.dao.DBConnection;

/**
 * Searches for the most useful reviews for a business.
 */
public class ReviewReadMostUseful implements ReviewReadBehaviour{

	@Override
	public PreparedStatement getPreparedStatement(int businessID, int numReviews) throws SQLException {
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				// Create select statement 
				PreparedStatement readReview = connection.prepareStatement(
						"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
								+ " FROM review "
								+ " WHERE business_ID = ?" 
								+ " ORDER BY UsefulCount DESC"
								+ " LIMIT ?;"		
						);

			readReview.setInt(1, businessID);
			readReview.setInt(2, numReviews);
			return readReview;

	}

}
