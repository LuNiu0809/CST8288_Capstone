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
import com.algonquin.Capstone.service.BusinessService;
import com.algonquin.Capstone.service.ReviewService;
import com.algonquin.Capstone.service.UserReviewUsefulService;

/**
 * 
 */
@WebServlet(name = "ReviewServlet", urlPatterns = {"/CreateNewReview", "/UpdateUsefulCount"} )
public class ReviewServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestURI = req.getRequestURI();

		if (requestURI.endsWith("/CreateNewReview")) {
			// Create a new review. 
			createNewReview(req, resp);
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
		} else {
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
					RequestDispatcher rd = req.getRequestDispatcher("businessReviews.jsp?");
					rd.forward(req, resp);
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
		
		Review review = new Review();
		ReviewService reviewService = new ReviewService();
		
		UserReviewUsefulService userReviewUsefulService = new UserReviewUsefulService();
		
		// Get current user ID
		int userId = getCurrentUserID(req);
		int reviewId = Integer.valueOf(req.getParameter("reviewId"));
		
		try {
			review = reviewService.readReview(reviewId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

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
			
		RequestDispatcher rd = req.getRequestDispatcher("businessReviews.jsp?");
		rd.forward(req, resp);

	}
	
	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
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
		
		Review review = new Review();	
		
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
		
		//Set review values
		review.setAuthorID(userId);
		review.setBusinessID(businessId);
		review.setFoodRating(foodRating);
		review.setServiceRating(serviceRating);
		review.setAtmosphereRating(atmosphereRating);
		review.setPriceRating(priceRating);
		review.setContent(content);
		review.generateCreationDate();
		
		return review;
		
	}

}
