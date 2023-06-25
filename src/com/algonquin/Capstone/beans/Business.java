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
	private String foodType;
	private Rating overallRating;
	private Rating priceRating;
	private String hoursOfOperation;
	
	/**
	 * Constructs a new business with default values. 
	 */
	public Business() {
		overallRating = new Rating();
		priceRating = new Rating();
	}
	
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
	 * gets the food type for this business
	 * @return the food type. 
	 */
	public String getFoodType() {
		return foodType;
	}
	
	/**
	 * sets the food type for this business
	 * @param foodtype the food type. 
	 */
	public void setFoodType(String foodtype) {
		this.foodType = foodtype;
	}
	
	
	/**
	 * get the overall rating of this business
	 * @return the overallRating
	 */
	public int getOverallRating() {
		return overallRating.getRating();
	}
	/**
	 * set the overall rating of this business
	 * @param overallRating the overallRating to set
	 */
	public void setOverallRating(int overallRating) {
		this.overallRating.setRating(overallRating);
	}
	/**
	 * get the price rating of this business
	 * @return the priceRating
	 */
	public int getPriceRating() {
		return priceRating.getRating();
	}
	/**
	 * set the price rating of this business
	 * @param priceRating the priceRating to set
	 */
	public void setPriceRating(int priceRating) {
		this.priceRating.setRating(priceRating);
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
	
	/**
	 * prints the business info to the console, used for testing. 
	 */
	public void printBusinessToConsole() {
		System.out.println("Printing Business ID " + id );
		System.out.println("Name " + name);
		System.out.println("Description " + description);
		System.out.println("address " + address);
		System.out.println("phoneNumber " + phoneNumber);
		System.out.println("email " + email);
		System.out.println("foodType " + foodType);
		System.out.println("overallRating " + overallRating.getRating());
		System.out.println("priceRating " + priceRating.getRating());
		System.out.println("hours of operation " + hoursOfOperation);
		System.out.println();
	}
	

}
