package com.algonquin.Capstone.dao.business;

import com.algonquin.Capstone.dao.EnumRatingSort;
import com.algonquin.Capstone.dao.RatingSortSQL;

public class BusinessRatingSort implements RatingSortSQL{

	
	private String overallRatingHighLow	 = (
				"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
						+ " FROM business"
						+ " ORDER BY OverallRating DESC"
						+ " LIMIT ?;"		
				);


	
	private String overallRatingLowHigh = (
				"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
						+ " FROM business"
						+ " ORDER BY OverallRating ASC"
						+ " LIMIT ?;"		
				);

	
	private String priceRatingHighLow = (
				"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
						+ " FROM business"
						+ " ORDER BY PriceRating DESC"
						+ " LIMIT ?;"		
				);


	
	private String priceRatingLowHigh = (
				"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
						+ " FROM business"
						+ " ORDER BY PriceRating ASC"
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
