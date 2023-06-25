/**
 * 
 */
package com.algonquin.Capstone.beans;

/**
 * Describes a rating
 */
public class Rating {
	
	private int ratingValue = 0;
	private final int MIN_RATING = 0;
	private final int MAX_RATING = 5;
	
	/**
	 * Sets the new rating value, checks to make sure the value is within the accepted range. 
	 * @param rating The new rating value
	 * @throws IllegalArgumentException Exception thrown if the given value is outside the accepted range. 
	 */
	public void setRating(int rating) throws IllegalArgumentException{
		
		if (rating < MIN_RATING) {
			throw new IllegalArgumentException("Rating value of: " + rating + " is less than the minimum value of: " + MIN_RATING);
		} else if (rating > MAX_RATING) {
			throw new IllegalArgumentException("Rating value of: " + rating + " is great than the maximum value of: " + MAX_RATING);
		} else {
			ratingValue = rating;
		}
		
	}
	
	/**
	 * Gets the rating value
	 * @return the rating value.
	 */
	public int getRating() {
		return ratingValue;
	}

}
