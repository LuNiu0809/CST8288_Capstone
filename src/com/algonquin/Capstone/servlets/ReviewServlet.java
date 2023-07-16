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
			updateUsefulCount(req, resp);
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
		
		Business business = new Business(); 
		BusinessService businessService = new BusinessService();
		int businessId = Integer.valueOf(req.getParameter("businessId"));

		try {
			business = businessService.readBusiness(businessId);
		} catch (SQLException e) {		
			e.printStackTrace();
			forwardToErrorPage(req, resp, "Error Reading Business From Database");
		}

		Review review = new Review();
		ReviewService reviewService = new ReviewService();

		//Create a new session
		session = req.getSession(true);
		String currentUsername = "";
		boolean authenticated = false;

		if (session.getAttribute("username") != null){
			currentUsername = session.getAttribute("username").toString();
		}
		if (session.getAttribute("authenticated") != null)	{
			authenticated = (boolean) session.getAttribute("authenticated");

		} 

		// Get user name from current session. 
		User user = new User();
		UserDao userDao = new UserDao();
		user = userDao.getUserByUsername(currentUsername);

		// get review information from post.
		int foodRating = Integer.valueOf(req.getParameter("foodRating"));
		int serviceRating = Integer.valueOf(req.getParameter("serviceRating"));
		int atmosphereRating = Integer.valueOf(req.getParameter("atmosphereRating"));
		int priceRating = Integer.valueOf(req.getParameter("priceRating"));
		String content = req.getParameter("content");


		int userId = user.getId(); 

		//Set review values
		review.setAuthorID(userId);
		review.setBusinessID(businessId);
		review.setFoodRating(foodRating);
		review.setServiceRating(serviceRating);
		review.setAtmosphereRating(atmosphereRating);
		review.setPriceRating(priceRating);
		review.setContent(content);
		review.generateCreationDate();

		// Create new review in Database. 
		int createStatus = reviewService.createReview(review);


		if (createStatus > 0){

			// When review is added, calculate the new ratings for the business
			ArrayList <Review> reviewList = new ArrayList<>();
			try {
				reviewList = reviewService.readAllReviews(businessId);
			} catch (SQLException e) {
				forwardToErrorPage(req, resp, "Error Creating new Review");
				e.printStackTrace();
			}

			business.calculateRatings(reviewList);

			// Update the business ratings in the database. 
			int newPriceRating = business.getPriceRating();
			int newOverallRating = business.getOverallRating();
			int businessUpdateStatus = businessService.updateRatings(businessId, newPriceRating, newOverallRating);

			if (businessUpdateStatus > 0){
				RequestDispatcher rd = req.getRequestDispatcher("businessReviews.jsp?");
				rd.forward(req, resp);
			}


		} else {
			forwardToErrorPage(req, resp, "Error Creating new Review");
		}
		
	}
	
	/**
	 * Updates the useful count for a review
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateUsefulCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Review review = new Review();
		ReviewService reviewService = new ReviewService();


		int reviewId = Integer.valueOf(req.getParameter("reviewId"));
		try {
			review = reviewService.readReview(reviewId);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		review.increaseUsefulCount();
		reviewService.updateUsefulCount(reviewId, review.getUsefulCount());

		RequestDispatcher rd = req.getRequestDispatcher("businessReviews.jsp?");
		rd.forward(req, resp);

	}
	
	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
		session = req.getSession(true);
		session.setAttribute("errorMessage", message);
		rd.forward(req, resp);
	}

}
