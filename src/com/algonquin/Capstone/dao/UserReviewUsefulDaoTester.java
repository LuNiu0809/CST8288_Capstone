package com.algonquin.Capstone.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserReviewUsefulDaoTester {
	
	UserReviewUsefulDao userReviewUseful  = new UserReviewUsefulDao();

	@Test
	void testAddNewRecord() {	
		assertEquals(1, userReviewUseful.addUserReviewHelpfulRecord(1, 1));
	}
	
	@Test
	void testDeleteRecord() {
		userReviewUseful.addUserReviewHelpfulRecord(2, 2);
		assertNotEquals(0, userReviewUseful.deleteUserReviewHelpfulRecord(2, 2));		
	}
	
	@Test
	void testCheckValidRecord() {
		userReviewUseful.addUserReviewHelpfulRecord(3, 3);
		
		try {
			assertEquals(true, userReviewUseful.checkUserReviewHelpfulRecord(3, 3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testCheckInvalidRecord() {
	
		try {
			assertEquals(false, userReviewUseful.checkUserReviewHelpfulRecord(4, 4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void cleanup() {
		userReviewUseful.deleteUserReviewHelpfulRecord(1, 1);
		userReviewUseful.deleteUserReviewHelpfulRecord(2, 2);
		userReviewUseful.deleteUserReviewHelpfulRecord(3, 3);
	}
	
	
	
	

}
