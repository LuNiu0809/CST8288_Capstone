/**
 * 
 */
package com.algonquin.Capstone.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.beans.Review;
import com.algonquin.Capstone.beans.User;
import com.algonquin.Capstone.dao.UserDao;
import com.algonquin.Capstone.dao.review.ReviewReadMostUseful;
import com.algonquin.Capstone.dao.review.ReviewReadNewest;
import com.algonquin.Capstone.dao.review.ReviewReadRatingHighLow;
import com.algonquin.Capstone.dao.review.ReviewReadRatingLowHigh;
import com.algonquin.Capstone.service.BusinessService;
import com.algonquin.Capstone.service.ReviewService;
import com.algonquin.Capstone.service.UserReviewUsefulService;

/**
 * 
 */
@WebServlet(name = "ReviewServlet", urlPatterns = {"/CreateNewReview", "/UpdateUsefulCount", "/GetBusinessReviews"} )
public class ReviewServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public static final String NUMBER_REVIEWS_STRING = "numReviews";
	public static final String REVIEW_SORTING_STRING = "reviewSorting";
	public static final String REVIEW_LIST_STRING = "reviewList";
	
	
	
	private HttpSession session;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();

		if (requestURI.endsWith("/CreateNewReview")) {
			// Create a new review. 
			createNewReview(req, resp);
			
		} else if (requestURI.contains("/GetBusinessReviews" )){ 
			
			getBusinessReviews(req, resp);	
			
		} else {
			// Invalid URL
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();
		
		 if (requestURI.contains("/UpdateUsefulCount")) {
			// Update Review Useful Count		
			try {
				updateUsefulCount(req, resp);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (requestURI.contains("/GetBusinessReviews" )){ 
			
			getBusinessReviews(req, resp);
			
		} else{

			// Invalid URL
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}
	
	/**
	 * Creates a new review for a business
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void createNewReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ReviewService reviewService = new ReviewService();

		// Set the review data from the Request. 
		Review review = setReviewData(req);
		
		try {
			// Create the new review in Database. 
			int createStatus = reviewService.createReview(review);
			
			// If the review was successfully added to the database, update the business's overall ratings. 
			if (createStatus > 0){
				
				BusinessService businessService = new BusinessService();
				Business business = businessService.readBusiness(review.getBusinessID());

				int businessUpdateStatus;
				
				businessUpdateStatus = businessService.updateRatings(business);
				// If the business was successfully updated in the database return to the business reivews page.
				if (businessUpdateStatus > 0){
//					RequestDispatcher rd = req.getRequestDispatcher("GetBusinessReviews");
//					rd.forward(req, resp);
					getBusinessReviews(req, resp);
				} else {
					throw new Exception();
				}
			} else {	
				throw new Exception();
			}

		} catch (Exception e) {
			forwardToErrorPage(req, resp, "Error Creating new Review");
		}


	}
	
	/**
	 * Updates the useful count for a review
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void updateUsefulCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		
		ReviewService reviewService = new ReviewService();
		
		UserReviewUsefulService userReviewUsefulService = new UserReviewUsefulService();
		
		// Get current user ID
		int userId = getCurrentUserID(req);
		int reviewId = Integer.valueOf(req.getParameter("reviewId"));
		
		try {
			Review review = reviewService.readReview(reviewId);
			// Check if the user has already found review helpful.
			if (userReviewUsefulService.checkUserReviewHelpfulRecord(userId, reviewId)) {
				review.decreaseUsefulCount();
				// Remove Record and Check to make sure the User Review Useful Table was updated. 
				if (userReviewUsefulService.deleteUserReviewHelpfulRecord(userId, reviewId) == 0) {
					forwardToErrorPage(req, resp, "Error Updating Review Useful Count");
				}
				
			} else {
				review.increaseUsefulCount();
				// Add Record and Check to make sure the User Review Useful Table was updated. 
				if (userReviewUsefulService.addUserReviewHelpfulRecord(userId, reviewId) == 0) {
					forwardToErrorPage(req, resp, "Error Updating Review Useful Count");
				}
			}
				
			// Check to make sure the review table was updated. 
			if (reviewService.updateUsefulCount(reviewId, review.getUsefulCount()) == 0) {
				forwardToErrorPage(req, resp, "Error Updating Review Useful Count");
			}
				
//			RequestDispatcher rd = req.getRequestDispatcher("GetBusinessReviews");
//			rd.forward(req, resp);
			getBusinessReviews(req, resp);
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			forwardToErrorPage(req, resp, "Error Updating useful count");
		}

		

	}
	
	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
		//Create a new session
		session = req.getSession(true);
		
		RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
		session = req.getSession(true);
		session.setAttribute("errorMessage", message);
		rd.forward(req, resp);
	}
	
	/**
	 * Gets the current User ID, returns 0 if current username is null. 
	 * @param req
	 * @return Current user ID or 0 if current username is null.
	 */
	private int getCurrentUserID(HttpServletRequest req) {

		//Create a new session
		session = req.getSession(true);
		String currentUsername = "";
		
		if (session.getAttribute("username") != null){
			// Get user name from current session. 
			currentUsername = session.getAttribute("username").toString();
				
			User user = new User();
			UserDao userDao = new UserDao();
			user = userDao.getUserByUsername(currentUsername);
			return user.getId();
		} else {
			return 0;
		}

	}
	
	private Review setReviewData(HttpServletRequest req) {		
		
		// get review information from post.
		int foodRating = Integer.valueOf(req.getParameter("foodRating"));
		int serviceRating = Integer.valueOf(req.getParameter("serviceRating"));
		int atmosphereRating = Integer.valueOf(req.getParameter("atmosphereRating"));
		int priceRating = Integer.valueOf(req.getParameter("priceRating"));
		String content = req.getParameter("content");
		
		// Get current user ID
		int userId = getCurrentUserID(req);
		
		// Get business ID
		int businessId = Integer.valueOf(req.getParameter("businessId"));
		
		Review review = new Review.Builder()	
		.setAuthorID(userId)
		.setBusinessID(businessId)
		.setFoodRating(foodRating)
		.setServiceRating(serviceRating)
		.setAtmosphereRating(atmosphereRating)
		.setPriceRating(priceRating)
		.setContent(content)
		.createNewReview();
		return review;
		
	}
	
	private void getBusinessReviews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//Create a new session
		session = request.getSession(true);

		int businessId = Integer.valueOf(request.getParameter("businessId"));
		ReviewService reviewService = new ReviewService();


		ArrayList<Review> reviewList = new ArrayList<>();

		//Get number of restuarants to view, if not set use default value. 
		int numReviews = getNumReviews(request, session);		
		session.setAttribute(NUMBER_REVIEWS_STRING, numReviews);

		//Get sorting, if not set use default value
		String sortingString = getSortingString(request, session);
		session.setAttribute(REVIEW_SORTING_STRING, sortingString);

		try {
			switch (sortingString){
			case "Rating High To Low" : reviewList = reviewService.readReviews(businessId, numReviews, new ReviewReadRatingHighLow()); break;
			case "Rating: Low To High" : reviewList = reviewService.readReviews(businessId, numReviews, new ReviewReadRatingLowHigh()); break;
			case "Newest" : reviewList = reviewService.readReviews(businessId, numReviews, new ReviewReadNewest()); break;
			case "Most Useful" : reviewList = reviewService.readReviews(businessId, numReviews, new ReviewReadMostUseful()); break;
			default : reviewList = reviewService.readReviews(businessId, numReviews, new ReviewReadNewest());
			}

		} catch (Exception e) {
			forwardToErrorPage(request, response, "Error reading reviews");
			e.printStackTrace();
		}

		session.setAttribute(REVIEW_LIST_STRING, reviewList);

		RequestDispatcher rd = request.getRequestDispatcher("businessReviews.jsp");
		rd.forward(request, response);

	}
	
	/**
	 * Gets the sorting string
	 * @param request
	 * @return The sorting string 
	 */
	private String getSortingString(HttpServletRequest request, HttpSession session) {
		String sortingString = "";

		if(request.getParameter(REVIEW_SORTING_STRING) != null){
			sortingString = (request.getParameter(REVIEW_SORTING_STRING).toString());
			
		} else if (session.getAttribute(REVIEW_SORTING_STRING) != null) {
			sortingString = session.getAttribute(REVIEW_SORTING_STRING).toString();
		} else {
			sortingString = "Rating High To Low";
		}
		
		return sortingString;
	}
	
	/**
	 * Gets the number of Reviews to return
	 * @param request
	 * @return the number of Reviews to return.
	 */
	private int getNumReviews(HttpServletRequest request, HttpSession session) {
		
		int numReviews;

		// Get number of restuarants to view, if not set use default value. 
		if(request.getParameter(NUMBER_REVIEWS_STRING) != null){
			numReviews = Integer.valueOf(request.getParameter(NUMBER_REVIEWS_STRING).toString());		
		} else if (session.getAttribute(NUMBER_REVIEWS_STRING) != null) {
			numReviews = Integer.valueOf(session.getAttribute(NUMBER_REVIEWS_STRING).toString());
		} else {
			numReviews = 5; 
		}	
		
		return numReviews;
		
	}
	


	


}
