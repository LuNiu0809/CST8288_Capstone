/**
 * 
 */
package com.algonquin.Capstone.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.algonquin.Capstone.beans.Business;




/**
 * Manages interactions to the Business Table in the database 
 */
public class BusinessDao {

	/**
	 * Creates a new business in the database. 
	 * @param business the business to be added.
	 * @return the number of rows added to the database. 
	 */
	public synchronized int createBusiness(Business business) {

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				// Create insert statement
				PreparedStatement newBusiness = connection.prepareStatement(
						"INSERT INTO business "
								+"(Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation)" 
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");						
				){			
			newBusiness.setString(1, business.getName());
			newBusiness.setString(2, business.getAddress());
			newBusiness.setString(3, business.getDescription());
			newBusiness.setString(4, business.getPhoneNumber());
			newBusiness.setString(5, business.getEmail());
			newBusiness.setInt(6, business.getOverallRating());
			newBusiness.setInt(7, business.getPriceRating());
			newBusiness.setString(8, business.getFoodType());
			newBusiness.setString(9, business.getHoursOfOperation());

			return newBusiness.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}  

	}

	/**
	 * Returns the requested number of business
	 * @param numBusiness
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<Business> readNumBusiness(int numBusiness) throws SQLException{

		ResultSet rs = null;

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	

				// Create select statement 
				PreparedStatement readBusiness = connection.prepareStatement(
						"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
								+ " FROM business "
								+ " LIMIT ?;"		
						);
				) {

			readBusiness.setInt(1, numBusiness);

			rs = readBusiness.executeQuery();

			ArrayList<Business> businessList = new ArrayList<>();
			int id = 0;
			String name = "";
			String address = "";
			String description = "";
			String phoneNumber = "";
			String email = "";			
			int overallRating = 0;
			int priceRating = 0;
			String foodType = "";
			String hoursOfOperation = "";	

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("Name");
				address = rs.getString("Address");
				description = rs.getString("Description");
				phoneNumber = rs.getString("PhoneNumber");
				email = rs.getString("Email");
				overallRating = rs.getInt("OverallRating");			
				priceRating = rs.getInt("PriceRating");
				foodType = rs.getString("FoodType");
				hoursOfOperation = rs.getString("HoursOfOperation");


				Business business = new Business();

				business.setId(id);
				business.setName(name);
				business.setAddress(address);
				business.setDescription(description);
				business.setPhoneNumber(phoneNumber);
				business.setEmail(email);
				business.setOverallRating(overallRating);				
				business.setPriceRating(priceRating); 
				business.setFoodType(foodType);
				business.setHoursOfOperation(hoursOfOperation);

				businessList.add(business);

			}		
			return businessList;					

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			rs.close();
		}

		return null;
	}
	
	/**
	 * Reads a specific business based on its ID
	 * @param businessID the id of the business to be read
	 * @return the business read from the database. 
	 * @throws SQLException
	 */
	public synchronized Business readBusiness(int businessID) throws SQLException{
		
		ResultSet rs = null;

		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				
				// Create select statement 
				PreparedStatement readBusiness = connection.prepareStatement(
						"SELECT id, Name, Address, Description, PhoneNumber, Email, OverallRating, PriceRating, FoodType, HoursOfOperation" 						
								+ " FROM business "
								+ " WHERE id = ?" 
										
						);
				) {							
			readBusiness.setInt(1, businessID);
			rs = readBusiness.executeQuery();
			Business business = new Business();
			
			int id = 0;
			String name = "";
			String address = "";
			String description = "";
			String phoneNumber = "";
			String email = "";			
			int overallRating = 0;
			int priceRating = 0;
			String foodType = "";
			String hoursOfOperation = "";	

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("Name");
				address = rs.getString("Address");
				description = rs.getString("Description");
				phoneNumber = rs.getString("PhoneNumber");
				email = rs.getString("Email");
				overallRating = rs.getInt("OverallRating");			
				priceRating = rs.getInt("PriceRating");
				foodType = rs.getString("FoodType");
				hoursOfOperation = rs.getString("HoursOfOperation");

				business.setId(id);
				business.setName(name);
				business.setAddress(address);
				business.setDescription(description);
				business.setPhoneNumber(phoneNumber);
				business.setEmail(email);
				business.setOverallRating(overallRating);				
				business.setPriceRating(priceRating); 
				business.setFoodType(foodType);
				business.setHoursOfOperation(hoursOfOperation);


			}		
			return business;					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			rs.close();
		}
		
		return null;
	}
	
	/**
	 * Gets the id of the last business inserted into the database. used for testing
	 * @return the last business added (max id)
	 */
	public synchronized int getlastBusinessID(){
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create select statement 
    			PreparedStatement selectLastBusinessID = connection.prepareStatement(
    					"SELECT MAX(ID) FROM business");
    			
    			ResultSet rs = selectLastBusinessID.executeQuery()
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
	 * Updates the Price and Overall Ratings for a business
	 * @param businessID the business to be updated
	 * @param newPriceRating the new price rating 
	 * @param newOverallRating the new overall rating 
	 * @return returns the number of rows updated, or 0 if the update failed. 
	 */
	public synchronized int updateRatings(int businessID, int newPriceRating, int newOverallRating) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create update statement 
    			PreparedStatement updateBusiness = connection.prepareStatement(
    					"UPDATE business "
    					+ "set PriceRating=?, OverallRating=? "
    					+ "WHERE ID=?");
    			){
    		updateBusiness.setInt(1, newPriceRating);
    		updateBusiness.setInt(2, newOverallRating);
    		updateBusiness.setInt(3, businessID);
			return updateBusiness.executeUpdate();		
			
		} catch (SQLException e) {		
			e.printStackTrace();	
			return 0;
		} 
		
	}
	
	/**
	 *  Deletes the selected business based on the provided ID. 
	 * @param businessID the ID of the business to delete. 
	 * @return returns the number of rows deleted or 0 if the delete was not successful. 
	 */
	public synchronized int deleteBusiness(int businessID) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create delete statement 
    			PreparedStatement deleteBusiness = connection.prepareStatement(
    					"DELETE FROM business "
    					+ "WHERE id=?");
    			){
    		deleteBusiness.setInt(1, businessID);			
			return deleteBusiness.executeUpdate();
		
		} catch (SQLException e) {		
			e.printStackTrace();
			return 0;		
		} 
		
	}




}
