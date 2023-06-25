/**
 * 
 */
package com.algonquin.Capstone.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Review;

/**
 * Manages interactions to the Review Table in the database 
 */
public class ReviewDao {
	
	
	/**
	 * Creates a new review in the database, Returns the number of rows added to the database. 
	 * If the number of rows = 0 then the creation failed. 
	 * @param review the review to be added 
	 * @return the number of rows added to the database. 
	 */
	public synchronized int createReview(Review review) {
		
		try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create insert statement
    			PreparedStatement newReview = connection.prepareStatement(
    					"INSERT INTO review "
    					+"(user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount)" 
    					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");						
				
    			){		
			newReview.setInt(1, review.getAuthorID());
			newReview.setInt(2, review.getBusinessID());
			
			// Source for converting to sql date 
			// https://stackoverflow.com/questions/16645724/how-to-insert-date-into-mysql-database-table-in-java
			newReview.setDate(3, new java.sql.Date(review.getCreationDate().getTime()));
			newReview.setString(4, review.getContent());
			newReview.setInt(5, review.getPriceRating().getRating());
			newReview.setInt(6, review.getOverallRating());
			newReview.setInt(7, review.getFoodRating().getRating());
			newReview.setInt(8, review.getServiceRating().getRating());
			newReview.setInt(9, review.getAtmosphereRating().getRating());
			newReview.setInt(10, review.getUsefulCount());
			
	
			return newReview.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}  
		
	}
	
	/**
	 * Reads the top 5 reviews for a business.
	 * @param businessID the id of the business to get reviews for
	 * @param numReviews the number of reviews to return for the business
	 * @return the top 5 reviews for the requested business
	 * @throws SQLException 
	 */
	public synchronized ArrayList<Review> readNumReviews(int businessID, int numReviews) throws SQLException{
		
		ResultSet rs = null;

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				
				// Create select statement 
				PreparedStatement readReview = connection.prepareStatement(
						"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
								+ " FROM review "
								+ " WHERE business_ID = ?" 
								+ " LIMIT ?;"		
						);
				) {
					
			readReview.setInt(1, businessID);
			readReview.setInt(2, numReviews);
			
			rs = readReview.executeQuery();
			
			ArrayList<Review> reviewList = new ArrayList<>();
			int id = 0;
			int user_ID = 0;
			int business_ID = 0;
			Date date = null;
			String content = "";
			int priceRating = 0;
			int overallRating = 0;
			int foodRating = 0;
			int serviceRating = 0;
			int atmosphereRating = 0;
			int usefulCount = 0;

			while (rs.next()) {

				id = rs.getInt("id");
				user_ID = rs.getInt("user_ID");
				business_ID = rs.getInt("business_ID");			 			
				date = rs.getDate("Date");
				content = rs.getString("Content");
				priceRating = rs.getInt("PriceRating");
				overallRating = rs.getInt("OverallRating");
				foodRating = rs.getInt("FoodRating");
				serviceRating = rs.getInt("ServiceRating");
				atmosphereRating = rs.getInt("AtmosphereRating");
				usefulCount = rs.getInt("UsefulCount"); 

				Review review = new Review();

				review.setID(id);
				review.setAuthorID(user_ID);
				review.setBusinessID(business_ID);
				review.setCreationDate(date);				
				review.setContent(content);
				review.setPriceRating(priceRating); 
				review.setOverallRating(overallRating);
				review.setFoodRating(foodRating);
				review.setServiceRating(serviceRating);
				review.setAtmosphereRating(atmosphereRating);
				review.setUsefulCount(usefulCount);


				reviewList.add(review);

			}		
			return reviewList;					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			rs.close();
		}
		
		return null;
	}
	
	/**
	 * Reads a specific review based on its ID
	 * @param reviewID the id of the review to be read
	 * @return the review read from the database. 
	 * @throws SQLException
	 */
	public synchronized Review readReview(int reviewID) throws SQLException{
		
		ResultSet rs = null;

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				
				// Create select statement 
				PreparedStatement readReview = connection.prepareStatement(
						"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
								+ " FROM review "
								+ " WHERE id = ?" 	
						);
				) {
				
			readReview.setInt(1, reviewID);
			
			rs = readReview.executeQuery();
			
			Review review = new Review();

			int id = 0;
			int user_ID = 0;
			int business_ID = 0;
			Date date = null;
			String content = "";
			int priceRating = 0;
			int overallRating = 0;
			int foodRating = 0;
			int serviceRating = 0;
			int atmosphereRating = 0;
			int usefulCount = 0;

			while (rs.next()) {

				id = rs.getInt("id");
				user_ID = rs.getInt("user_ID");
				business_ID = rs.getInt("business_ID");			 			
				date = rs.getDate("Date");
				content = rs.getString("Content");
				priceRating = rs.getInt("PriceRating");
				overallRating = rs.getInt("OverallRating");
				foodRating = rs.getInt("FoodRating");
				serviceRating = rs.getInt("ServiceRating");
				atmosphereRating = rs.getInt("AtmosphereRating");
				usefulCount = rs.getInt("UsefulCount"); 

				

				review.setID(id);
				review.setAuthorID(user_ID);
				review.setBusinessID(business_ID);
				review.setCreationDate(date);				
				review.setContent(content);
				review.setPriceRating(priceRating); 
				review.setOverallRating(overallRating);
				review.setFoodRating(foodRating);
				review.setServiceRating(serviceRating);
				review.setAtmosphereRating(atmosphereRating);
				review.setUsefulCount(usefulCount);


			}		
			return review;					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			rs.close();
		}
		
		return null;
	}
	
	/**
	 * Gets the id of the last review inserted into the database. used for testing
	 * @return the last review added (max id)
	 */
	public synchronized int getlastReviewID(){
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create select statement 
    			PreparedStatement selectLastReviewID = connection.prepareStatement(
    					"SELECT MAX(ID) FROM review");
    			
    			ResultSet rs = selectLastReviewID.executeQuery()
    			){
    		
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			
			return id;
		
		} catch (SQLException e) {		
			e.printStackTrace();
			return 0;		
		} 	
	}
	
	/**
	 * Updates the useful count of a review
	 * @param reviewID the review to be updated
	 * @param newUsefulCount the new count
	 * @return returns the number of rows updated, or 0 if the update failed. 
	 */
	public synchronized int updateUsefulCount(int reviewID, int newUsefulCount) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create update statement 
    			PreparedStatement updateReview = connection.prepareStatement(
    					"UPDATE review "
    					+ "set usefulCount=? "
    					+ "WHERE ID=?");
    			){
    		updateReview.setInt(1, newUsefulCount);
    		updateReview.setInt(2, reviewID);
			return updateReview.executeUpdate();		
			
		} catch (SQLException e) {		
			e.printStackTrace();	
			return 0;
		} 
		
	}
	
	
	
	/**
	 *  Deletes the selected review based on the provided ID. 
	 * @param reviewID the ID of the review to delete. 
	 * @return returns the number of rows deleted or 0 if the delete was not successful. 
	 */
	public synchronized int deleteReview(int reviewID) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create delete statement 
    			PreparedStatement deleteReview = connection.prepareStatement(
    					"DELETE FROM review "
    					+ "WHERE id=?");
    			){
    		deleteReview.setInt(1, reviewID);			
			return deleteReview.executeUpdate();
		
		} catch (SQLException e) {		
			e.printStackTrace();
			return 0;		
		} 
		
	}
	

}
