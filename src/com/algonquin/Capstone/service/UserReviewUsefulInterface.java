package com.algonquin.Capstone.service;

import java.sql.SQLException;


public interface UserReviewUsefulInterface {
	
	/**
	 * Adds a record of a user finding a review helpful. 
	 * @param userID The user
	 * @param reviewID The Review
	 * @return the number of rows added to the database.
	 */
	public int addUserReviewHelpfulRecord(int userID, int reviewID);
	
	/**
	 * Removes a record of a user finding a review helpful
	 * @param userID the user
	 * @param reviewID the review
	 * @return the number of rows deleted from the database.
	 */
	public int deleteUserReviewHelpfulRecord(int userID, int reviewID);
	
	/**
	 * Checks if a user has already found a review helpful or not, returns true if they have, false if not. 
	 * @param userID the user
	 * @param reviewID the review
	 * @return  true if the user has already found the review helpful, false if not. 
	 */
	public boolean checkUserReviewHelpfulRecord(int userID, int reviewID) throws SQLException;	
	
	
}
