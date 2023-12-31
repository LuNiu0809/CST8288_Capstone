/**
 * 
 */
package com.algonquin.Capstone.service;

import java.sql.SQLException;
import java.util.ArrayList;




import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.beans.User;
import com.algonquin.Capstone.dao.UserDao;
import com.algonquin.Capstone.dao.review.ReviewDao;
import com.algonquin.Capstone.dao.review.ReviewReadBehaviour;

/**
 * Acts as a service between the Review Dao Class and the Web Interface. 
 */
public class ReviewService implements ReviewServiceInterface{
	
	private ReviewDao reviewDao;
	private UserDao userDao;
	
	public ReviewService() {
		reviewDao = new ReviewDao();
		
	}
	
	@Override
	public int createReview(Review review) {
		return reviewDao.createReview(review);
	
	}
	
	@Override
	public ArrayList<Review> readReviews(int businessID, int numReviews, ReviewReadBehaviour readBehaviour) throws SQLException{
		return reviewDao.readReviews(businessID, numReviews, readBehaviour);
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
	
	public User getReviewAuthor(Review review) {
		userDao = new UserDao();
		User reviewAuthor = new User();
		reviewAuthor = userDao.getUserById(review.getAuthorID());
		return reviewAuthor;
	}
	

		
		


}
