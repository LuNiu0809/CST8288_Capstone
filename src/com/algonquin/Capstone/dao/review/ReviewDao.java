/**
 * 
 */
package com.algonquin.Capstone.dao.review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.dao.DBConnection;
import com.algonquin.Capstone.service.ReviewServiceInterface;

/**
 * Manages interactions to the Review Table in the database 
 */
public class ReviewDao implements ReviewServiceInterface{
		
	
	@Override
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
			newReview.setTimestamp(3, new java.sql.Timestamp(review.getCreationDate().getTime()));
			newReview.setString(4, review.getContent());
			newReview.setInt(5, review.getPriceRating());
			newReview.setInt(6, review.getOverallRating());
			newReview.setInt(7, review.getFoodRating());
			newReview.setInt(8, review.getServiceRating());
			newReview.setInt(9, review.getAtmosphereRating());
			newReview.setInt(10, review.getUsefulCount());
			
	
			return newReview.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}  
		
	}
	
	
	@Override
	public synchronized ArrayList<Review> readReviews(int businessID, int numReviews, ReviewReadBehaviour readBehaviour) throws SQLException{

		ResultSet rs = null;
		try (
				PreparedStatement readReview = readBehaviour.getPreparedStatement(businessID, numReviews);
				){
			
			rs = readReview.executeQuery();
			

			ArrayList<Review> reviewList = new ArrayList<>();
			while (rs.next()) {
				Review review = getReviewFromResultSet(rs);
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
	

	@Override
	public synchronized ArrayList<Review> readAllReviews(int businessID) throws SQLException{
		
		ResultSet rs = null;

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				
				// Create select statement 
				PreparedStatement readReview = connection.prepareStatement(
						"SELECT id, user_ID, business_ID, Date, Content, PriceRating, OverallRating, FoodRating, ServiceRating, AtmosphereRating, UsefulCount"						
								+ " FROM review "
								+ " WHERE business_ID = ?" 		
						);
				) {
					
			readReview.setInt(1, businessID);			
			rs = readReview.executeQuery();			
			ArrayList<Review> reviewList = new ArrayList<>();
			while (rs.next()) {				
				Review review = getReviewFromResultSet(rs);
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
	

	@Override
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
			while (rs.next()) {				
				return getReviewFromResultSet(rs);
			}		

			
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
	

	@Override
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
	
	/**
	 * Returns a review object from a provided result set read from the database. 
	 * @param rs the result set read from the database. 
	 * @return the review object.
	 * @throws SQLException
	 */
	private synchronized Review getReviewFromResultSet(ResultSet rs) throws SQLException {
		
		if (rs != null) {
			int id = rs.getInt("id");
			int user_ID = rs.getInt("user_ID");
			int business_ID = rs.getInt("business_ID");			 			
			Date date = rs.getDate("Date");
			String content = rs.getString("Content");
			int priceRating = rs.getInt("PriceRating");
			int overallRating = rs.getInt("OverallRating");
			int foodRating = rs.getInt("FoodRating");
			int serviceRating = rs.getInt("ServiceRating");
			int atmosphereRating = rs.getInt("AtmosphereRating");
			int usefulCount = rs.getInt("UsefulCount"); 
		
			Review review = new Review.Builder()			
			.setAuthorID(user_ID)
			.setBusinessID(business_ID)								
			.setContent(content)
			.setPriceRating(priceRating)				
			.setFoodRating(foodRating)
			.setServiceRating(serviceRating)
			.setAtmosphereRating(atmosphereRating)
			.createExistingReview(id, overallRating, date, usefulCount);
			return review;
			
		} else {
			return null;
		}
		
			
		
	}


	

}
