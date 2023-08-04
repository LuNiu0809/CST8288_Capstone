/**
 * 
 */
package com.algonquin.Capstone.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Review Class 
 */
class ReviewTester {
	
	Review testReview = new Review.Builder()			
	.setAuthorID(1)
	.setBusinessID(1)								
	.setContent("test Content")
	.setPriceRating(1)				
	.setFoodRating(1)
	.setServiceRating(1)
	.setAtmosphereRating(1)
	.createNewReview();
			

	/**
	 * Test the increase helpful count function
	 */
	@Test
	void testIncreaseUsefulCount() {
		testReview.increaseUsefulCount();
		assertEquals(1, testReview.getUsefulCount());
	}
	
	/**
	 * Test the decrease helpful count function below the minimum (0)
	 */
	@Test
	void testdecreaseUsefulCount() {
		testReview.decreaseUsefulCount();
		assertEquals(0, testReview.getUsefulCount());
	}
	
	/**
	 * Test both the increase and decrease helpful count functions
	 */
	@Test
	void testIncreaseAndDecreaseUsefulCount() {
		testReview.increaseUsefulCount();
		testReview.increaseUsefulCount();
		testReview.increaseUsefulCount();
		testReview.decreaseUsefulCount();
		testReview.decreaseUsefulCount();
		testReview.increaseUsefulCount();
		assertEquals(2, testReview.getUsefulCount());
	}
	

	
	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating3() {

		Review testReview3 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(3)
				.setServiceRating(3)
				.setAtmosphereRating(3)
				.createNewReview();

		assertEquals(3, testReview3.getOverallRating());
	}

	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating1() {
		
		Review testReview0 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(1)
				.setServiceRating(1)
				.setAtmosphereRating(1)
				.createNewReview();
		
		assertEquals(1, testReview0.getOverallRating());
	}
	
	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating5() {
		
		Review testReview5 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(5)
				.setServiceRating(5)
				.setAtmosphereRating(5)
				.createNewReview();
		
		assertEquals(5, testReview5.getOverallRating());
	}
	
	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating4() {
		
		Review testReview4 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(5)
				.setServiceRating(4)
				.setAtmosphereRating(4)
				.createNewReview();
		
		assertEquals(4, testReview4.getOverallRating());
	}
	
	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating4_1() {
		
		Review testReview4_1 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(5)
				.setServiceRating(5)
				.setAtmosphereRating(4)
				.createNewReview();
		
		assertEquals(4, testReview4_1.getOverallRating());
	}
	
	/**
	 * Test the calculating the overall rating
	 */
	@Test
	void testCalculateOverallRating3_1() {
		
		Review testReview3_1 = new Review.Builder()			
				.setAuthorID(1)
				.setBusinessID(1)								
				.setContent("test Content")
				.setPriceRating(1)				
				.setFoodRating(5)
				.setServiceRating(3)
				.setAtmosphereRating(1)
				.createNewReview();
		
		assertEquals(3, testReview3_1.getOverallRating());
	}
	
	

	
	
	

}
