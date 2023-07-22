package com.algonquin.Capstone.dao;

/**
 * Returns SQL for a prepared statement to sort based on rating values.
 */


public interface RatingSortSQL extends SqlSortBehaviour  {
	
	/**
	 * Gets the String for an SQL prepared statement based on the requested sort type
	 * @param ratingSort the stort type
	 * @return the prepared statement string. 
	 */
	public String getRatingSortSQL(EnumRatingSort ratingSort);
	

}
