/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.dao.EnumRatingSort;
import com.algonquin.Capstone.dao.business.BusinessDao;

/**
 * 
 */
public class BusinessService implements BusinessServiceInterface{
	
	private BusinessDao businessDao;
	
	public BusinessService() {
		businessDao = new BusinessDao();
	}

	@Override
	public int createBusiness(Business business) {
		return businessDao.createBusiness(business);
	}

	@Override
	public ArrayList<Business> readNumBusiness(int numBusiness, EnumRatingSort ratingSort) throws SQLException {
		return businessDao.readNumBusiness(numBusiness, ratingSort);
	}

	@Override
	public Business readBusiness(int businessID) throws SQLException {
		return businessDao.readBusiness(businessID);
	}

	@Override
	public int updateRatings(Business business) throws SQLException{

		ReviewService reviewService = new ReviewService();

		// Read all the reviews for this business, then recalculate the ratings. 
		ArrayList <Review> reviewList = new ArrayList<>();
		int businessID = business.getId();
		reviewList = reviewService.readAllReviews(businessID);

		business.calculateRatings(reviewList);
		
		// Update the ratings in the database.
		return businessDao.updateRatings(business);
	}

}
