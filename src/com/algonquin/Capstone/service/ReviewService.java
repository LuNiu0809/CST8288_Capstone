/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
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
		try {
			// Add new review to the database. 
			int createStatus = reviewDao.createReview(review);
			
			// If the review was successfully added to the database, update the business's overall ratings. 
			if (createStatus > 0){
				Business business = new Business();
				BusinessService businessService = new BusinessService();

				int businessUpdateStatus;
				
				businessUpdateStatus = businessService.updateRatings(business);
				if (businessUpdateStatus > 0){
					return businessUpdateStatus;
				} else {
					throw new Exception();
				}
			} else {	
				throw new Exception();
			}

		} catch (Exception e) {
			return 0;
		}
	
	}
	
	@Override
	public ArrayList<Review> readNumReviews(int businessID, int numReviews) throws SQLException{
		return reviewDao.readNumReviews(businessID, numReviews);
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
