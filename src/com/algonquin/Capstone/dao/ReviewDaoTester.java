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
	
	Review testReview = new Review();
	ReviewDao reviewDao = new ReviewDao();
	User testUser = new User();
	Business testBusiness = new Business();
	
	
	
	/**
	 * Tests inserting a new review into the database. 
	 */
	@Test
	void testInsertReview() {
		
	
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content");
		testReview.setAtmosphereRating(1);
		testReview.setFoodRating(2);
		testReview.setServiceRating(3);
		testReview.setPriceRating(4); 
		testReview.post();
		
		assertEquals(1, reviewDao.createReview(testReview));  
		
	}
	
	/**
	 * Test that 5 reviews are being returned 
	 */
	@Test
	void testSelectTop5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();
		
		// Insert 5 reviews for the business 
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content 1");
		testReview.setAtmosphereRating(1);
		testReview.setFoodRating(1);
		testReview.setServiceRating(1);
		testReview.setPriceRating(1); 
		testReview.post();
		reviewDao.createReview(testReview);
		
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content 2");
		testReview.setAtmosphereRating(2);
		testReview.setFoodRating(2);
		testReview.setServiceRating(2);
		testReview.setPriceRating(2); 
		testReview.post();
		reviewDao.createReview(testReview);
		
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content 3");
		testReview.setAtmosphereRating(3);
		testReview.setFoodRating(3);
		testReview.setServiceRating(3);
		testReview.setPriceRating(3); 
		testReview.post();
		reviewDao.createReview(testReview);
		
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content 4");
		testReview.setAtmosphereRating(4);
		testReview.setFoodRating(4);
		testReview.setServiceRating(4);
		testReview.setPriceRating(4); 
		testReview.post();
		reviewDao.createReview(testReview);
		
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content 5");
		testReview.setAtmosphereRating(5);
		testReview.setFoodRating(5);
		testReview.setServiceRating(5);
		testReview.setPriceRating(5); 
		testReview.post();
		reviewDao.createReview(testReview);
		
		
		try {
			reviewList = reviewDao.readNumReviews(2 , 5);
			for (Review review : reviewList) {
				//review.printReviewToConsole();
				
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
		
		Review testReadReview = new Review();

		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test read review content");
		testReview.setAtmosphereRating(5);
		testReview.setFoodRating(5);
		testReview.setServiceRating(5);
		testReview.setPriceRating(5); 
		testReview.post();
		
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
		

		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("Test Review Content delete");
		testReview.setAtmosphereRating(5);
		testReview.setFoodRating(5);
		testReview.setServiceRating(5);
		testReview.setPriceRating(5); 
		testReview.post();
		reviewDao.createReview(testReview);
		int deleteID = reviewDao.getlastReviewID();
		
		Review testDeleteReview = new Review();
		try {
			testDeleteReview = reviewDao.readReview(deleteID);
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
		
		testReview.setAuthorID(1);
		testReview.setBusinessID(2);
		testReview.setContent("update useful Content ");
		testReview.setAtmosphereRating(5);
		testReview.setFoodRating(5);
		testReview.setServiceRating(5);
		testReview.setPriceRating(5); 
		testReview.post();
		reviewDao.createReview(testReview);
		int updateID = reviewDao.getlastReviewID();
		
		Review testUpdateReview = new Review();
		try {
			testUpdateReview = reviewDao.readReview(updateID);
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
