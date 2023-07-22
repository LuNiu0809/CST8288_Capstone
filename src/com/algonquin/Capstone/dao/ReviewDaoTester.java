/**
 * 
 */
package com.algonquin.Capstone.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.beans.User;

/**
 * Tests the ReviewDao Class 
 */
@TestInstance(Lifecycle.PER_CLASS) 
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
	 * Test that the top 5 reviews are being returned 
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
			reviewList = reviewDao.readNumReviews(2 , 5, EnumRatingSort.OVERALL_RATING_HIGH_LOW);
			System.out.println("TOP 5 Reviews: ");
			for (Review review : reviewList) {
				review.printReviewToConsole();
				
			}
			System.out.println();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the first rating is less than or equal to the one after it.
		assertTrue(reviewList.get(0).getOverallRating() >= reviewList.get(1).getOverallRating());
		
	}
	
	/**
	 * Test that bottom 5 business are being returned 
	 */
	@Test
	void testSelectBottom5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
			reviewList = reviewDao.readNumReviews(2, 5, EnumRatingSort.OVERALL_RATING_LOW_HIGH);
			System.out.println("Bottom 5 Reviews: ");
			for (Review review  : reviewList) {
				review.printReviewToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the first rating is less than or equal to the one after it.
		assertTrue(reviewList.get(0).getOverallRating() <= reviewList.get(1).getOverallRating());
		
	}
	
	/**
	 * Test that the most expensive 5 business are being returned 
	 */
	@Test
	void testSelectExpensive5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
			reviewList = reviewDao.readNumReviews(2, 5, EnumRatingSort.PRICE_RATING_HIGH_LOW);
			System.out.println("Most Expensive 5 Reviews: ");
			for (Review review  : reviewList) {
				review.printReviewToConsole();	
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the price first rating is greater than or equal to the one after it.
		assertTrue(reviewList.get(0).getPriceRating() >= reviewList.get(1).getPriceRating());
		
	}
	
	/**
	 * Test that the most expensive 5 business are being returned 
	 */
	@Test
	void testSelectCheap5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
			reviewList = reviewDao.readNumReviews(2, 5, EnumRatingSort.PRICE_RATING_LOW_HIGH);
			System.out.println("Least Expensive 5 Reviews: ");
			for (Review review  : reviewList) {
				review.printReviewToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the price first rating is less than or equal to the one after it.
		assertTrue(reviewList.get(0).getPriceRating() <= reviewList.get(1).getPriceRating());
		
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
		.setContent("Test update useful Content ")
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
	
	@AfterAll
	void cleanUp() {		
		System.out.println("Running cleanup");
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create delete statement 
    			PreparedStatement deleteReview = connection.prepareStatement(
    					"DELETE FROM review "
    					+ "WHERE Content like ?");
    			){
    		deleteReview.setString(1, "Test%");
    		int deleted = deleteReview.executeUpdate();
    		System.out.println(deleted + " Records deleted");
	
		} catch (SQLException e) {		
			e.printStackTrace();		
		} 		
	}	


}
