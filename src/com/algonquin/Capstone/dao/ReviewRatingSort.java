/**
 * 
 */
package com.algonquin.Capstone.dao;

/**
 * 
 */
public class ReviewRatingSort implements RatingSortSQL{
	

	private String overallRatingHighLow	 = (
			"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
					+ " FROM review "
					+ " WHERE business_ID = ?" 
					+ " ORDER BY OverallRating DESC, Date DESC"
					+ " GROUP BY OverallRating"
					+ " LIMIT ?;"		
			);


	private String overallRatingLowHigh = (
			"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
					+ " FROM review "
					+ " WHERE business_ID = ?" 
					+ " ORDER BY OverallRating ASC, Date DESC"
					+ " GROUP BY OverallRating"
					+ " LIMIT ?;"		
			);


	private String priceRatingHighLow = (
			"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
					+ " FROM review "
					+ " WHERE business_ID = ?" 
					+ " ORDER BY PriceRating DESC, Date DESC"
					+ " GROUP BY PriceRating"
					+ " LIMIT ?;"		
			);


	private String priceRatingLowHigh = (
			"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
					+ " FROM review "
					+ " WHERE business_ID = ?" 
					+ " ORDER BY PriceRating ASC, Date DESC"
					+ " GROUP BY PriceRating"
					+ " LIMIT ?;"		
			);






	@Override
	public String getRatingSortSQL(EnumRatingSort ratingSort) {
		
		switch(ratingSort) {
		case OVERALL_RATING_HIGH_LOW : return overallRatingHighLow;
		case OVERALL_RATING_LOW_HIGH : return overallRatingLowHigh;
		case PRICE_RATING_HIGH_LOW : return priceRatingHighLow; 
		case PRICE_RATING_LOW_HIGH : return priceRatingLowHigh;
		default : return null;
		}
	}

}
