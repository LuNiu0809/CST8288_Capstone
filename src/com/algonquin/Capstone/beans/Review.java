package com.algonquin.Capstone.beans;


import java.sql.Timestamp;
import java.util.Date;



/**
 * @author wbr
 * Describes a review of a restaurant created by a user. 
 * 
 *  
 */

public class Review {
	
	private int id;	
	private int authorID;
	private int businessID;
	private String content; 
	private Rating priceRating;
	private Rating overallRating;
	private Rating foodRating;
	private Rating serviceRating;
	private Rating atmosphereRating;	
	private Date creationDate;
	private int usefulCount;
	private final int MIN_USEFUL_COUNT = 0;
	
	/**
	 * Constructs a new review with default values. 
	 */
	private Review(Builder builder) {

		id = builder.id;
		authorID = builder.authorID;
		businessID = builder.businessID;
		content = builder.content;
		priceRating = builder.priceRating;
		overallRating = builder.overallRating;
		foodRating = builder.foodRating;
		serviceRating = builder.serviceRating;
		atmosphereRating = builder.atmosphereRating;
		creationDate = builder.creationDate;
		usefulCount = builder.usefulCount;
	}
	
//	/**
//	 * sets the ID for this review, used when reading a review for the database. 
//	 * @param id the id. 
//	 */
//	public void setID(int id) {
//		this.id = id;
//	}
	
	/**
	 * gets the id of this review. 
	 * @return the id. 
	 */
	public int getID() {
		return id;
	}
	
//	/**
//	 * Sets the author ID for this review
//	 * @param author the ID of the author of this review
//	 */
//	public void setAuthorID(int authorID) {
//		this.authorID = authorID;
//	}
	
	/**
	 * Gets the author ID of this review
	 * @return the author ID
	 */
	public int getAuthorID() {
		return authorID;
	}
	
//	/**
//	 * Sets the business ID that is being reviewed. 
//	 * @param business the business ID
//	 */
//	public void setBusinessID(int businessID) {
//		this.businessID = businessID;
//	}
	
	/**
	 * Gets the business ID that is being reviewed. 
	 * @return the business ID
	 */
	public int getBusinessID() {
		return businessID;
	}
	
//	/**
//	 * Sets the content for this review
//	 * @param content
//	 */
//	public void setContent(String content) {
//		this.content = content;
//	}
	
	/**
	 * Gets the content of the review. 
	 * @return the content of the review. 
	 */
	public String getContent() {
		return content;
	}
	
//	/**
//	 * Sets the price rating for this review.
//	 * @param rating the rating. 
//	 */
//	public void setPriceRating (int rating) {
//		
//		priceRating.setRating(rating);
//	}
	
	/**
	 * Gets the price rating of this review. 
	 * @return the price rating. 
	 */
	public int getPriceRating() {
		return priceRating.getRating();
	}
	
//	/**
//	 * Sets the food rating for this review.
//	 * @param rating the rating. 
//	 */
//	public void setFoodRating (int rating) {
//		
//		foodRating.setRating(rating);
//		calculateOverallRating();
//	}
	
	/**
	 * Gets the food rating of this review
	 * @return the food rating. 
	 */
	public int getFoodRating() {
		return foodRating.getRating();
	}
	
//	/**
//	 * Sets the service rating for this review.
//	 * @param rating the service rating. 
//	 */
//	public void setServiceRating (int rating) {
//		
//		serviceRating.setRating(rating);
//		calculateOverallRating();
//	}
	
	/**
	 * gets the service rating for this review.
	 * @return the service rating
	 */
	public int getServiceRating() {
		return serviceRating.getRating();
	}
	
//	/**
//	 * Sets the atmosphere rating for this review.
//	 * @param rating the rating. 
//	 */
//	public void setAtmosphereRating (int rating) {
//		
//		atmosphereRating.setRating(rating);
//		calculateOverallRating();
//	}
	
	/**
	 * gets the atmosphere rating for this review. 
	 * @return the atmosphere rating. 
	 */
	public int getAtmosphereRating() {
		return atmosphereRating.getRating();
	}
	

	
	/**
	 * gets the overall rating of the review
	 * @return the overall rating
	 */
	public int getOverallRating() {
		return overallRating.getRating();
	}
	
//	/**
//	 * Sets the overall rating of this review, used when reading from the database
//	 * @param rating the rating
//	 */
//	public void setOverallRating(int rating) {
//		overallRating.setRating(rating);
//	}
	

	/**
	 * gets the count of users who found the review helpful
	 * @return the the count of users who found the review helpful
	 */
	public int getUsefulCount() {
		return usefulCount;
	}
	
	/**
	 * Increases the count of users who found the review helpful by one. 
	 */
	public void increaseUsefulCount() {
		usefulCount += 1;
	}
	
	/**
	 * Decreases the count of users who found the review helpful by one. 
	 */
	public void decreaseUsefulCount() {
		usefulCount -= 1;
		if (usefulCount < MIN_USEFUL_COUNT ) {
			usefulCount = MIN_USEFUL_COUNT;
		} 
	}
	
//	/**
//	 * sets the helpfulcount for this review, used when reading from the database. 
//	 * @param count the count
//	 */
//	public void setUsefulCount(int count) {
//		usefulCount = count;
//	}

	

	
//	/**
//	 * Sets the creation date for this review, used when reading from the database. 
//	 * @param date the date
//	 */
//	public void setCreationDate(Date date) {
//		creationDate = date;
//	}
	
	/**
	 * gets the creation date for this post. 
	 * @return the creation date 
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Prints this reviews data to the console. 
	 */
	public void printReviewToConsole() {
		System.out.println("Printing Review ID " + id);
		System.out.println("Author ID " + authorID);
		System.out.println("Business ID " + businessID);
		System.out.println("Content " + content);
		System.out.println("Price rating " + priceRating.getRating());
		System.out.println("Overall Rating " + overallRating.getRating());
		System.out.println("food Rating " + foodRating.getRating());
		System.out.println("serive Rating " + serviceRating.getRating());
		System.out.println("atmostphere Rating " + atmosphereRating.getRating());
		System.out.println("Creation Date " + creationDate);
		System.out.println("Helpful Count " + usefulCount);
		System.out.println();
	}
	
	
    /**
     * Builder for the Review class, ensures the Review is properly created. 
     */
    public static class Builder {
    	
    	private int id; 	
    	private int authorID;
    	private int businessID;
    	private String content;   	
    	private Rating priceRating;
    	private Rating overallRating;
    	private Rating foodRating;
    	private Rating serviceRating;
    	private Rating atmosphereRating; 	
    	private Date creationDate;
    	private int usefulCount;
    	
    	/**
    	 * Constructs a builder object. 
    	 */
    	public Builder() {
    		priceRating = new Rating();
    		overallRating = new Rating();
    		foodRating = new Rating();
    		serviceRating = new Rating();
    		atmosphereRating = new Rating();		
    	}
    	
   	
    	/**
    	 * Sets the author ID for the review
    	 * @param authorID the author id
    	 * @return the builder object
    	 */
    	public Builder setAuthorID(int authorID) {
    		this.authorID = authorID;
    		return this;
    	}
    	
    	/**
    	 * Sets the business ID for the review
    	 * @param businessID the business review
    	 * @return the builder object
    	 */
    	public Builder setBusinessID(int businessID) {
    		this.businessID = businessID;
    		return this;
    	}
    	
    	/**
    	 * Sets the content for the review
    	 * @param content the content 
    	 * @return the builder object 
    	 */
    	public Builder setContent(String content) {
    		this.content = content;
    		return this;
    	}
    	
    	/**
    	 * Sets the price rating for the review
    	 * @param rating the price rating 
    	 * @return the builder object
    	 */
    	public Builder setPriceRating(int rating) {
    		this.priceRating.setRating(rating);
    		return this;
    	}
    	
    	/**
    	 * Sets the food rating for the review
    	 * @param rating the food rating 
    	 * @return the builder object
    	 */
    	public Builder setFoodRating(int rating) {
    		this.foodRating.setRating(rating);
    		return this;
    	}
    	
    	/**
    	 * Sets the service rating for the review
    	 * @param rating the service rating 
    	 * @return the builder object. 
    	 */
    	public Builder setServiceRating(int rating) {
    		this.serviceRating.setRating(rating);
    		return this;
    	}
    	    	
    	/**
    	 * Sets the atmosphere rating for the review. 
    	 * @param rating the atmosphere rating 
    	 * @return the builder object. 
    	 */
       	public Builder setAtmosphereRating(int rating) {
    		this.atmosphereRating.setRating(rating);
    		return this;
    	}
       	
    	/**
    	 * Calculates the overall rating for the review based on food, service and atmosphere. 
    	 * 
    	 */
    	private void calculateOverallRating() {
    		int foodRatingValue = foodRating.getRating();
    		int serviceRatingValue = serviceRating.getRating();
    		int atmosphereRatingValue = atmosphereRating.getRating();
    		
    		int overallRatingValue = (foodRatingValue + serviceRatingValue + atmosphereRatingValue) / 3;
    		overallRating.setRating(overallRatingValue);
    	}
    	
    	/**
    	 * generates the creation date for this review. 
    	 */
    	private void generateCreationDate() {
    		
    		// Timestamp source https://mkyong.com/java/how-to-get-current-timestamps-in-java/
    		Date tsDate = new Date();
    		this.creationDate = new Timestamp(tsDate.getTime());	
    	}
       	
    	/**
    	 * Creates a new review, generates the overall rating and creation date, lets the database assign the ID when inserted. 
    	 * Throws an exception if one of more parameters are not set. 
    	 * @return the new Review object, ready to insert into the database. 
    	 * @throws IllegalStateException the exception thrown if one or more parameters are not set. 
    	 */
       	public Review createNewReview() throws IllegalStateException{
       		calculateOverallRating();
       		generateCreationDate();
       		usefulCount = 0;
       		validate();
       		return new Review(this);
       	}
       	
       	/**
       	 * Creates a new review object for a review already existing in the database. Requires the parameters read from the database
       	 * that are generated when a new review is created for the first time. 
       	 * Throws an exception if one of more parameters are not set. 
       	 * @param id the review iD
       	 * @param overallRating the overall rating of the review
       	 * @param creationDate the creation date of the review
       	 * @param usefulCount the useful count of the review
       	 * @return the review object
       	 * @throws IllegalStateException the exception thrown if one or more parameters are not set. 
       	 */
       	public Review createExistingReview(int id, int overallRating, Date creationDate, int usefulCount) throws IllegalStateException{
       		this.id = id;
       		this.overallRating.setRating(overallRating);
       		this.creationDate = creationDate;
       		this.usefulCount = usefulCount;
       		validate();
       		return new Review(this);
       		
       	}
       	
       	
    	/**
    	 * validates that all required information is provided to create a new Review. 
    	 * @throws IllegalStateException
    	 */
    	private void validate() throws IllegalStateException {
    		StringBuilder sb = new StringBuilder();
    		if(authorID == 0) {
    			sb.append("authorID must be assigned. ");
    		}  		
    		if(businessID == 0) {
    			sb.append("businessID must be assigned. ");
    		}
    		if(content == null) {
    			sb.append("content must not be null. ");
    		}
    		if(overallRating.getRating() == 0) {
    			sb.append("overallRating must be assigned. ");
    		}
    		if(priceRating.getRating() == 0) {
    			sb.append("priceRating must be assigned. ");
    		}
       		if(foodRating.getRating() == 0) {
    			sb.append("foodRating must be assigned. ");
    		}
       		if(serviceRating.getRating() == 0) {
    			sb.append("serviceRating must be assigned. ");
    		}
       		if(atmosphereRating.getRating() == 0) {
    			sb.append("atmosphereRating must be assigned. ");
    		}
       		if(creationDate == null) {
    			sb.append("creationDate must be not be null. ");
    		}
    		if (sb.length() > 0) {
    			throw new IllegalStateException(sb.toString());
    		}	   		
    	}   
    
    	
    	
    	
    }
	
	
	
	
	
	
	
	
	

}
