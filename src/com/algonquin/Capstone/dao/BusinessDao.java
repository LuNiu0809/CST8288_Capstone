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
import com.algonquin.Capstone.service.BusinessServiceInterface;




/**
 * Manages interactions to the Business Table in the database 
 */
public class BusinessDao implements BusinessServiceInterface{
	
	private BusinessRatingSort ratingSortSQL;


	@Override
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


	@Override
	public synchronized ArrayList<Business> readNumBusiness(int numBusiness, EnumRatingSort ratingSort ) throws SQLException{

		ResultSet rs = null;
		
		
		// Get sorting sql string from business rating sort object
		ratingSortSQL = new BusinessRatingSort();
		String sql = ratingSortSQL.getRatingSortSQL(ratingSort);
		
		try (
				// Create DB Connection
				Connection connection = DBConnection.getConnectionToDatabase();	
				
				// Create select statement 				
				PreparedStatement readBusiness = connection.prepareStatement(sql);

				) {
			
			readBusiness.setInt(1, numBusiness);	

			rs = readBusiness.executeQuery();
			ArrayList<Business> businessList = new ArrayList<>();
			while (rs.next()) {				
				Business business =  getBusinessFromResultSet(rs);
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
	

	@Override
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

			while (rs.next()) {				
				return getBusinessFromResultSet(rs);
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
	

	@Override
	public synchronized int updateRatings(Business business) {
		
    	try (
    			// Create DB Connection
    			Connection connection = DBConnection.getConnectionToDatabase();	
    			// Create update statement 
    			PreparedStatement updateBusiness = connection.prepareStatement(
    					"UPDATE business "
    					+ "set PriceRating=?, OverallRating=? "
    					+ "WHERE ID=?");
    			){
    		updateBusiness.setInt(1, business.getPriceRating());
    		updateBusiness.setInt(2, business.getOverallRating());
    		updateBusiness.setInt(3, business.getId());
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
	
	/**
	 * Returns a Business object from a provided result set read from the database. 
	 * @param rs the result set
	 * @return The business object.
	 * @throws SQLException
	 */
	private synchronized Business getBusinessFromResultSet(ResultSet rs) throws SQLException {
		
		if (rs != null) {
			Business business = new Business();

			int id = rs.getInt("id");
			String name = rs.getString("Name");
			String address = rs.getString("Address");
			String description = rs.getString("Description");
			String phoneNumber = rs.getString("PhoneNumber");
			String email = rs.getString("Email");
			int overallRating = rs.getInt("OverallRating");			
			int priceRating = rs.getInt("PriceRating");
			String foodType = rs.getString("FoodType");
			String hoursOfOperation = rs.getString("HoursOfOperation");

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
			
			return business;
			
		} else {
			return null;
		}
				
		
	}




}
