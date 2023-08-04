/**
 * 
 */
package com.algonquin.Capstone.dao.review;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.beans.User;
import com.algonquin.Capstone.dao.DBConnection;

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
	@BeforeAll
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
//			reviewDao.setReadBehaviour(new ReviewReadRatingHighLow());
			reviewList = reviewDao.readReviews(2 , 5, new ReviewReadRatingHighLow());
			System.out.println("Top 5 Reviews: ");
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
	 * Test that bottom 5 reviews are being returned 
	 */
	@Test
	void testSelectBottom5Reviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
//			reviewDao.setReadBehaviour(new ReviewReadRatingLowHigh());
			reviewList = reviewDao.readReviews(2, 5, new ReviewReadRatingLowHigh());
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
	 * Test that the most newest reviews are being returned
	 * @throws InterruptedException 
	 */
	@Test
	void testSelect5NewestReviews() throws InterruptedException {
		
//		Thread.sleep(60000); 
		Review testReview= new Review.Builder()	
				.setAuthorID(1)
				.setBusinessID(2)
				.setContent("Test Review Content 6")
				.setAtmosphereRating(5)
				.setFoodRating(5)
				.setServiceRating(5)
				.setPriceRating(5)
				.createNewReview();
				reviewDao.createReview(testReview);
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
//			reviewDao.setReadBehaviour(new ReviewReadNewest());
			reviewList = reviewDao.readReviews(2, 5, new ReviewReadNewest());
			System.out.println("5 Newest Reviews: ");
			for (Review review  : reviewList) {
				review.printReviewToConsole();	
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the first rating is after the one after it.
		//assertTrue(reviewList.get(0).getCreationDate().after(reviewList.get(1).getCreationDate()));
		
	}
	
	/**
	 * Test that the most useful 5 reviews are being returned. 
	 */
	@Test
	void testSelect5MostUsefulReviews() {
		
		ArrayList<Review> reviewList = new ArrayList<>();		
		try {
//			reviewDao.setReadBehaviour(new ReviewReadMostUseful());
			reviewList = reviewDao.readReviews(2, 5, new ReviewReadMostUseful());
			System.out.println("5 Most Useful Reviews: ");
			for (Review review  : reviewList) {
				review.printReviewToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, reviewList.size());
		// Check to make sure that the useful count is greater than or equal to the one after it.
		assertTrue(reviewList.get(0).getUsefulCount() >= reviewList.get(1).getUsefulCount());
		
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
