/**
 * 
 */
package com.algonquin.Capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.algonquin.Capstone.service.UserReviewUsefulInterface;

/**
 * Manages interactions to the User_Review_Useful Table in the database
 */
public class UserReviewUsefulDao implements UserReviewUsefulInterface{

	@Override
	public synchronized int addUserReviewHelpfulRecord(int userID, int reviewID) {
		try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create insert statement
    			PreparedStatement newRecord = connection.prepareStatement(
    					"INSERT INTO user_review_useful "
    					+"(user_ID, review_ID) " 
    					+ "VALUES (?, ?)");										
    			){		
			newRecord.setInt(1, userID);
			newRecord.setInt(2, reviewID);			
			return newRecord.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}  		
	}

	
	
	@Override
	public synchronized int deleteUserReviewHelpfulRecord(int userID, int reviewID) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create delete statement 
    			PreparedStatement deleteRecord = connection.prepareStatement(
    					"DELETE FROM user_review_useful "
    					+ "WHERE user_ID=? AND review_ID=?");
    			){
    		deleteRecord.setInt(1, userID);
    		deleteRecord.setInt(2, reviewID);	
			return deleteRecord.executeUpdate();
		
		} catch (SQLException e) {		
			e.printStackTrace();
			return 0;		
		} 

	}

	@Override
	public synchronized boolean checkUserReviewHelpfulRecord(int userID, int reviewID) throws SQLException {		
		ResultSet rs = null;
		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();					
				// Create select statement 
				PreparedStatement readRecord = connection.prepareStatement(
						"SELECT user_ID, review_ID"						
								+ " FROM user_review_useful "
								+ " WHERE user_ID=? AND review_ID=? " 
								+ " LIMIT 1;"		
						);
				) {
					
			readRecord.setInt(1, userID);
			readRecord.setInt(2, reviewID);			
			rs = readRecord.executeQuery();
			while (rs.next()) {
				return true;			
			}					
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {			
			rs.close();
		}		
		return false;
	}

}
