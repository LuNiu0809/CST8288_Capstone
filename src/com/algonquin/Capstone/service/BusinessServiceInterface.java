package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.dao.EnumRatingSort;
import com.algonquin.Capstone.dao.business.BusinessReadBehaviour;

public interface BusinessServiceInterface {
	
	/**
	 * Creates a new business in the database. 
	 * @param business the business to be added.
	 * @return the number of rows added to the database. 
	 */
	public int createBusiness(Business business);
	
	/**
	 * Search for number of businesses by their name or type of food or description, with a defined read behaviour. 
	 * @param numBusiness The number of businesses
	 * @param keyword The keyword to search for (business name or type of food).
	 * @param readBehaviour the desired read behaviour for this action. 
	 * @return A list of businesses that match the search criteria.
	 * @throws SQLException If there's an error while accessing the database.
	 */
	public ArrayList<Business> readNumBusiness(int numBusiness, String keyword, BusinessReadBehaviour readBehaviour) throws SQLException;
	
	
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
