/**
 * 
 */
package com.algonquin.Capstone.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Rating Class
 */
class RatingTester {

	Rating testRating = new Rating();
	
	
	/**
	 * Test default rating of 0
	 */
	@Test
	void testDefaultRating() {
		
		assertEquals(0, testRating.getRating());
	}

	/**
	 * Test setting the rating below the minimum
	 * Source for testing for exception 
	 * https://www.digitalocean.com/community/tutorials/junit-assert-exception-expected
	 */
	@Test
	void testSetRatingBelowMin() {

		assertThrows(IllegalArgumentException.class, () ->testRating.setRating( -1));
		
	}
	
	/**
	 * Test setting the rating above the maximum
	 */
	@Test
	void testSetRatingAboveMax() {

		assertThrows(IllegalArgumentException.class, () ->testRating.setRating( 6));
		
	}
	
	/**
	 * Test setting the rating to middle range accepted value
	 */
	@Test
	void testSetRatingMid() {
		testRating.setRating( 3);
		assertEquals(3, testRating.getRating());
	}
	
	/**
	 * Test setting the rating to min range accepted value
	 */
	@Test
	void testSetRatingMin() {
		testRating.setRating( 0);
		assertEquals(0, testRating.getRating());
	}
	
	/**
	 * Test setting the rating to max range accepted value
	 */
	@Test
	void testSetRatingMax() {
		testRating.setRating( 5);
		assertEquals(5, testRating.getRating());
	}

}
