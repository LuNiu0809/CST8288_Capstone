/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.dao.EnumRatingSort;
import com.algonquin.Capstone.dao.ReviewDao;

/**
 * Acts as a service between the Review Dao Class and the Web Interface. 
 */
public class ReviewService implements ReviewServiceInterface{
	
	private ReviewDao reviewDao;
	
	public ReviewService() {
		reviewDao = new ReviewDao();
	}
	
	@Override
	public int createReview(Review review) {
		return reviewDao.createReview(review);
	
	}
	
	@Override
	public ArrayList<Review> readNumReviews(int businessID, int numReviews, EnumRatingSort ratingSort) throws SQLException{
		return reviewDao.readNumReviews(businessID, numReviews, ratingSort);
	}
	
	@Override
	public Review readReview(int reviewID) throws SQLException {
		return reviewDao.readReview(reviewID);
	}
	
	@Override
	public int updateUsefulCount(int reviewID, int newUsefulCount) {
		return reviewDao.updateUsefulCount(reviewID, newUsefulCount);
	}
	
	@Override
	public ArrayList<Review> readAllReviews(int businessID) throws SQLException{
		return reviewDao.readAllReviews(businessID);
	}
	
	
		
		


}
