package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Business;

public interface BusinessServiceInterface {
	
	/**
	 * Creates a new business in the database. 
	 * @param business the business to be added.
	 * @return the number of rows added to the database. 
	 */
	public int createBusiness(Business business);
	
	/**
	 * Returns the requested number of business
	 * @param numBusiness
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Business> readNumBusiness(int numBusiness) throws SQLException;
	
	
	/**
	 * Reads a specific business based on its ID
	 * @param businessID the id of the business to be read
	 * @return the business read from the database. 
	 * @throws SQLException
	 */
	public Business readBusiness(int businessID) throws SQLException;
	
	
	/**
	 * Updates the Price and Overall Ratings only for a business
	 * @param business the business to be updated
	 * @return returns the number of rows updated, or 0 if the update failed. 
	 */
	public int updateRatings(Business business) throws SQLException;

}
