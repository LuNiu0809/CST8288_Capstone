/**
 * 
 */
package com.algonquin.Capstone.dao;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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




/**
 * Tests the BusinessDao class
 */
@TestInstance(Lifecycle.PER_CLASS) 
class BusinessDaoTester {
	
	Business testBusiness = new Business();
	BusinessDao testBusinessDao = new BusinessDao();

	/**
	 * Tests added a new business to the database. 
	 */
	@Test
	void testNewBusiness() {
		
		testBusiness.setAddress("test address");
		testBusiness.setDescription("test Description");
		testBusiness.setEmail("test email");
		testBusiness.setFoodType("Test Food type");
		testBusiness.setHoursOfOperation("Test hours of operation");
		testBusiness.setName("Test Name");
		testBusiness.setOverallRating(3);
		testBusiness.setPhoneNumber("Test Phone Number");
		testBusiness.setPriceRating(4);	
		
		assertNotEquals(0, testBusinessDao.createBusiness(testBusiness)); 
		
	}
	
	/**
	 * Test that the top 5 business are being returned 
	 */
	@Test
	void testSelectTop5Businesses() {
		
		ArrayList<Business> businessList = new ArrayList<>();
		
		// Insert 5 businesses 		
		testBusiness.setAddress("test address 1");
		testBusiness.setDescription("test Description 1");
		testBusiness.setEmail("test email 1");
		testBusiness.setFoodType("Test Food type 1");
		testBusiness.setHoursOfOperation("Test hours of operation 1");
		testBusiness.setName("Test Name 1");
		testBusiness.setOverallRating(1);
		testBusiness.setPhoneNumber("Test Phone Number 1");
		testBusiness.setPriceRating(1);	
		testBusinessDao.createBusiness(testBusiness);
		
		testBusiness.setAddress("test address 2");
		testBusiness.setDescription("test Description 2");
		testBusiness.setEmail("test email 2");
		testBusiness.setFoodType("Test Food type 2");
		testBusiness.setHoursOfOperation("Test hours of operation 2");
		testBusiness.setName("Test Name 2");
		testBusiness.setOverallRating(2);
		testBusiness.setPhoneNumber("Test Phone Number 2");
		testBusiness.setPriceRating(2);	
		testBusinessDao.createBusiness(testBusiness);
		
		testBusiness.setAddress("test address 3");
		testBusiness.setDescription("test Description 3");
		testBusiness.setEmail("test email 3");
		testBusiness.setFoodType("Test Food type 3");
		testBusiness.setHoursOfOperation("Test hours of operation 3");
		testBusiness.setName("Test Name 3");
		testBusiness.setOverallRating(3);
		testBusiness.setPhoneNumber("Test Phone Number 3");
		testBusiness.setPriceRating(3);	
		testBusinessDao.createBusiness(testBusiness);
		
		testBusiness.setAddress("test address 4");
		testBusiness.setDescription("test Description 4");
		testBusiness.setEmail("test email 4");
		testBusiness.setFoodType("Test Food type 4");
		testBusiness.setHoursOfOperation("Test hours of operation 4");
		testBusiness.setName("Test Name 4");
		testBusiness.setOverallRating(4);
		testBusiness.setPhoneNumber("Test Phone Number 4");
		testBusiness.setPriceRating(4);	
		testBusinessDao.createBusiness(testBusiness);
		
		testBusiness.setAddress("test address 5");
		testBusiness.setDescription("test Description 5");
		testBusiness.setEmail("test email 5");
		testBusiness.setFoodType("Test Food type 5");
		testBusiness.setHoursOfOperation("Test hours of operation 5");
		testBusiness.setName("Test Name 5");
		testBusiness.setOverallRating(5);
		testBusiness.setPhoneNumber("Test Phone Number 5");
		testBusiness.setPriceRating(5);	
		testBusinessDao.createBusiness(testBusiness);
	
		
		
		try {
			businessList = testBusinessDao.readNumBusiness(5, EnumRatingSort.OVERALL_RATING_HIGH_LOW);
			System.out.println("TOP 5 Businesses: ");
			
			for (Business business : businessList) {
				business.printBusinessToConsole();
				
			}
			System.out.println();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, businessList.size());
		// Check to make sure that the first rating is greater than or equal to the one after it.
		assertTrue(businessList.get(0).getOverallRating() >= businessList.get(1).getOverallRating());
		
	}
	
	/**
	 * Test that bottom 5 business are being returned 
	 */
	@Test
	void testSelectBottom5Businesses() {
		
		ArrayList<Business> businessList = new ArrayList<>();		
		try {
			businessList = testBusinessDao.readNumBusiness(5, EnumRatingSort.OVERALL_RATING_LOW_HIGH);
			System.out.println("Bottom 5 Businesses: ");
			for (Business business : businessList) {
				business.printBusinessToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, businessList.size());
		// Check to make sure that the first rating is less than or equal to the one after it.
		assertTrue(businessList.get(0).getOverallRating() <= businessList.get(1).getOverallRating());
		
	}
	
	/**
	 * Test that the most expensive 5 business are being returned 
	 */
	@Test
	void testSelectExpensive5Businesses() {
		
		ArrayList<Business> businessList = new ArrayList<>();		
		try {
			businessList = testBusinessDao.readNumBusiness(5, EnumRatingSort.PRICE_RATING_HIGH_LOW);
			System.out.println("Most Expensive 5 Businesses: ");
			for (Business business : businessList) {
				business.printBusinessToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, businessList.size());
		// Check to make sure that the price first rating is greater than or equal to the one after it.
		assertTrue(businessList.get(0).getPriceRating() >= businessList.get(1).getPriceRating());
		
	}
	
	/**
	 * Test that the most expensive 5 business are being returned 
	 */
	@Test
	void testSelectCheap5Businesses() {
		
		ArrayList<Business> businessList = new ArrayList<>();		
		try {
			businessList = testBusinessDao.readNumBusiness(5, EnumRatingSort.PRICE_RATING_LOW_HIGH);
			System.out.println("Least Expensive 5 Businesses: ");
			for (Business business : businessList) {
				business.printBusinessToConsole();		
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(5, businessList.size());
		// Check to make sure that the price first rating is less than or equal to the one after it.
		assertTrue(businessList.get(0).getPriceRating() <= businessList.get(1).getPriceRating());
		
	}
	
	/**
	 * Test that a business can be read by its id;
	 */
	@Test
	void testReadBusiness() {
		
		Business testReadBusiness = new Business();

		testBusiness.setAddress("test read address");
		testBusiness.setDescription("test read Description");
		testBusiness.setEmail("test read email");
		testBusiness.setFoodType("Test read Food type");
		testBusiness.setHoursOfOperation("Test read hours of operation");
		testBusiness.setName("Test read Name");
		testBusiness.setOverallRating(5);
		testBusiness.setPhoneNumber("Test read Phone Number");
		testBusiness.setPriceRating(5);	
		
		testBusinessDao.createBusiness(testBusiness);
	
		
		int businessID = testBusinessDao.getlastBusinessID();
		
		try {
			testReadBusiness = testBusinessDao.readBusiness(businessID);
			//testReadBusiness.printBusinessToConsole();;
			assertEquals(businessID, testReadBusiness.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Test that a review is being deleted 
	 */
	@Test
	void testdeleteBusiness() {
		

		Business testDeleteBusiness = new Business();

		testBusiness.setAddress("test delete address");
		testBusiness.setDescription("test delete Description");
		testBusiness.setEmail("test delete email");
		testBusiness.setFoodType("Test delete Food type");
		testBusiness.setHoursOfOperation("Test delete hours of operation");
		testBusiness.setName("Test delete Name");
		testBusiness.setOverallRating(5);
		testBusiness.setPhoneNumber("Test delete Phone Number");
		testBusiness.setPriceRating(5);	
		
		testBusinessDao.createBusiness(testBusiness);
		
		
		int deleteID = testBusinessDao.getlastBusinessID();
		
		
		try {
			testDeleteBusiness = testBusinessDao.readBusiness(deleteID);
			//testDeleteBusiness.printBusinessToConsole();			
			assertNotEquals(0, testBusinessDao.deleteBusiness(deleteID));		
			//testDeleteBusiness = testBusinessDao.readBusiness(deleteID);
			//testDeleteBusiness.printBusinessToConsole();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Test that a business updating its ratings.  
	 */
	@Test
	void testUpdateBusinessRatings() {
		

		testBusiness.setAddress("test update address");
		testBusiness.setDescription("test update Description");
		testBusiness.setEmail("test update email");
		testBusiness.setFoodType("Test update Food type");
		testBusiness.setHoursOfOperation("Test update hours of operation");
		testBusiness.setName("Test update Name");
		testBusiness.setOverallRating(5);
		testBusiness.setPhoneNumber("Test update Phone Number");
		testBusiness.setPriceRating(5);	
		
		testBusinessDao.createBusiness(testBusiness);
		
		int updateID = testBusinessDao.getlastBusinessID();
		
		
		try {
			
			Business testUpdateBusiness = new Business();
			testUpdateBusiness = testBusinessDao.readBusiness(updateID);
			//testUpdateBusiness.printBusinessToConsole();		
			
			testUpdateBusiness.setOverallRating(3);
			testUpdateBusiness.setPriceRating(3);
			
			assertNotEquals(0, testBusinessDao.updateRatings(testUpdateBusiness));		
			testUpdateBusiness = testBusinessDao.readBusiness(updateID);
			//testUpdateBusiness.printBusinessToConsole();
			
			
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
    			PreparedStatement deleteBusiness = connection.prepareStatement(
    					"DELETE FROM business "
    					+ "WHERE Description like ?");
    			){
    		deleteBusiness.setString(1, "Test%");
    		int deleted = deleteBusiness.executeUpdate();
    		System.out.println(deleted + " Records deleted");
	
		} catch (SQLException e) {		
			e.printStackTrace();		
		} 		
	}
	

}
