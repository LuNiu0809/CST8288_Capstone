/**
 * 
 */
package com.algonquin.Capstone.beans;

/**
 * Describes a business
 */
public class Business {
	
	private int id; 
	
	private String name;
	private String description;
	private String address;
	private String phoneNumber;
	private String email;
	
	private Rating overallRating;
	private Rating priceRating;
	private String hoursOfOperation;
	
	/**
	 * Get the id of this business
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set the id of this business, used for reading from the database. 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get the name of this business
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the name of this business
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get the description of this business
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * set the description for this business
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * get the address of this business
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * set the address of this business
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * get the phone number of this business
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * set the phone number of this business
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * get the email of this business
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set the email of this business
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * get the overall rating of this business
	 * @return the overallRating
	 */
	public Rating getOverallRating() {
		return overallRating;
	}
	/**
	 * set the overall rating of this business
	 * @param overallRating the overallRating to set
	 */
	public void setOverallRating(Rating overallRating) {
		this.overallRating = overallRating;
	}
	/**
	 * get the price rating of this business
	 * @return the priceRating
	 */
	public Rating getPriceRating() {
		return priceRating;
	}
	/**
	 * set the price rating of this business
	 * @param priceRating the priceRating to set
	 */
	public void setPriceRating(Rating priceRating) {
		this.priceRating = priceRating;
	}
	/**
	 * get the hours of operation for this business
	 * @return the hoursOfOperation
	 */
	public String getHoursOfOperation() {
		return hoursOfOperation;
	}
	/**
	 * set the hours of operation for this business
	 * @param hoursOfOperation the hoursOfOperation to set
	 */
	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}
	

}
