package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.dao.EnumRatingSort;
import com.algonquin.Capstone.dao.review.ReviewReadBehaviour;

public interface ReviewServiceInterface {
	
	/**
	 * Creates a new review in the database, Returns the number of rows added to the database. 
	 * If the number of rows = 0 then the creation failed. 
	 * @param review the review to be added 
	 * @return the number of rows added to the database. 
	 */
	public int createReview(Review review);
	
	/**
	 * Reads reviews from the database, the read behaviour of the object defines how the results are sorted
	 * The newest reviews are returned by default unless the read behaviour is changed.
	 * @param businessID the id of the business to get reviews for
	 * @param numReviews the number of reviews to return for the business
	 * @return the number of reviews for the requested business
	 * @throws SQLException 
	 */
	public ArrayList<Review> readReviews(int businessID, int numReviews) throws SQLException;
	
	/**
	 * Reads all reviews for a business
	 * @param businessID the id of the business to read the reviews for 
	 * @return the reviews for that business
	 * @throws SQLException
	 */
	public ArrayList<Review> readAllReviews(int businessID) throws SQLException;
	
	/**
	 * Reads a specific review based on its ID
	 * @param reviewID the id of the review to be read
	 * @return the review read from the database. 
	 * @throws SQLException
	 */
	public Review readReview(int reviewID) throws SQLException;
	
	/**
	 * Updates the useful count of a review
	 * @param reviewID the review to be updated
	 * @param newUsefulCount the new count
	 * @return returns the number of rows updated, or 0 if the update failed. 
	 */
	public int updateUsefulCount(int reviewID, int newUsefulCount);
	
	/**
	 * Sets the read behaviour for how the list of reviews is returned by the readReviews method.
	 * @param readBehaviour the requested reading behaviour
	 */
	public void setReadBehaviour(ReviewReadBehaviour readBehaviour);
		
	
	


}
