/**
 * 
 */
package com.algonquin.Capstone.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.beans.User;

/**
 * Tests the ReviewDao Class 
 */
class ReviewDaoTester {
	
	Review testReview = new Review.Builder()			
	.setAuthorID(1)
	.setBusinessID(1)								
	.setContent("test Content")
	.setPriceRating(1)				
	.setFoodRating(1)
	.setServiceRating(1)
	.setAtmosphereRating(1)
	.createNewReview();
	ReviewDao reviewDao = new ReviewDao();
	User testUser = new User();
	Business testBusiness = new Business();
	
	
	
	/**
	 * Tests inserting a new review into the database. 
	 */
	@Test
	void testInsertReview() {
		
		Review testInsertReview = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(2)								
				.setContent("test Review Content")
				.setPriceRating(4)				
				.setFoodRating(2)
				.setServiceRating(3)
				.setAtmosphereRating(1)
				.createNewReview();
		
		assertEquals(1, reviewDao.createReview(testInsertReview));  
		
	}
	
	/**
	 * Test that 5 reviews are being returned 
	 */
	@Test
	void testSelectTop5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();
		
		// Insert 5 reviews for the business 
		Review testReview1 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content 1")
		.setAtmosphereRating(1)
		.setFoodRating(1)
		.setServiceRating(1)
		.setPriceRating(1)
		.createNewReview();
		reviewDao.createReview(testReview1);
		
		Review testReview2 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content 2")
		.setAtmosphereRating(2)
		.setFoodRating(2)
		.setServiceRating(2)
		.setPriceRating(2)
		.createNewReview();
		reviewDao.createReview(testReview2);
		
		Review testReview3 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content 3")
		.setAtmosphereRating(3)
		.setFoodRating(3)
		.setServiceRating(3)
		.setPriceRating(3)
		.createNewReview();	
		reviewDao.createReview(testReview3);
		
		Review testReview4 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content 4")
		.setAtmosphereRating(4)
		.setFoodRating(4)
		.setServiceRating(4)
		.setPriceRating(4)
		.createNewReview();
		reviewDao.createReview(testReview4);
		
		Review testReview5 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content 5")
		.setAtmosphereRating(5)
		.setFoodRating(5)
		.setServiceRating(5)
		.setPriceRating(5)
		.createNewReview();
		reviewDao.createReview(testReview5);
		
		
		try {
			reviewList = reviewDao.readNumReviews(2 , 5);
			for (Review review : reviewList) {
				review.printReviewToConsole();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		
	}
	
	/**
	 * Test that a review can be read by its id;
	 */
	@Test
	void testReadReview() {
		
		Review testReadReview = new Review.Builder()
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test read review content")
		.setAtmosphereRating(5)
		.setFoodRating(5)
		.setServiceRating(5)
		.setPriceRating(5)
		.createNewReview();
		
		reviewDao.createReview(testReview);
		
		int reviewID = reviewDao.getlastReviewID();
		
		try {
			testReadReview = reviewDao.readReview(reviewID);
			//testReadReview.printReviewToConsole();
			assertEquals(reviewID, testReadReview.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * Test that a review is being deleted 
	 */
	@Test
	void testdeleteReviews() {
		
		Review testDeleteReview1 = new Review.Builder()			
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("Test Review Content delete")
		.setAtmosphereRating(5)
		.setFoodRating(5)
		.setServiceRating(5)
		.setPriceRating(5)
		.createNewReview();
		reviewDao.createReview(testDeleteReview1);
		int deleteID = reviewDao.getlastReviewID();
		
		try {
			Review testDeleteReview = reviewDao.readReview(deleteID);
			//testDeleteReview.printReviewToConsole();			
			assertNotEquals(0, reviewDao.deleteReview(deleteID));		
			//testDeleteReview = reviewDao.readReview(deleteID);
			//testDeleteReview.printReviewToConsole();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@Test
	void testUpdateUsefulCOunt() {
		Review testUpdateReview1 = new Review.Builder()	
		.setAuthorID(1)
		.setBusinessID(2)
		.setContent("update useful Content ")
		.setAtmosphereRating(5)
		.setFoodRating(5)
		.setServiceRating(5)
		.setPriceRating(5) 
		.createNewReview();
		reviewDao.createReview(testUpdateReview1);
		int updateID = reviewDao.getlastReviewID();
		
		try {
			Review testUpdateReview = reviewDao.readReview(updateID);
			//testUpdateReview.printReviewToConsole();
			assertEquals(0, testUpdateReview.getUsefulCount());
			
			assertNotEquals(0, reviewDao.updateUsefulCount(updateID, 1));		
			testUpdateReview = reviewDao.readReview(updateID);
			assertEquals(1, testUpdateReview.getUsefulCount());
			
			//testUpdateReview.printReviewToConsole();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
