package com.algonquin.Capstone.servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.Capstone.beans.Business;
import com.algonquin.Capstone.dao.business.BusinessReadPriceHighLow;
import com.algonquin.Capstone.dao.business.BusinessReadPriceLowHigh;
import com.algonquin.Capstone.dao.business.BusinessReadRatingHighLow;
import com.algonquin.Capstone.dao.business.BusinessReadRatingLowHigh;
import com.algonquin.Capstone.service.BusinessService;

@WebServlet(name = "BusinessServlet", urlPatterns = {"/GetBusinessList"} )
public class BusinessServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
				

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
		
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Business> businessList = new ArrayList<>();
		BusinessService businessService = new BusinessService();
		
		//Create a new session
		session = request.getSession(true);

		// Get number of restuarants to view, if not set use default value. 
		int numRestaurants = getNumBusiness(request, session);
		session.setAttribute("numRestaurants", numRestaurants);

		// Get Search String
		String searchString = getSearchString(request, session);
		session.setAttribute("Search", searchString);
		

		//Get sorting, if not set use default value
		String sortingString = getSortingString(request, session);
		session.setAttribute("sorting", sortingString);

		try {
			switch (sortingString){
			case "Rating High To Low" : 
				businessList = businessService.readNumBusiness(numRestaurants, searchString, new BusinessReadRatingHighLow()); break;
			case "Rating: Low To High" :
				businessList = businessService.readNumBusiness(numRestaurants, searchString, new BusinessReadRatingLowHigh()); break;
			case "Price: High To Low" : 
				businessList = businessService.readNumBusiness(numRestaurants, searchString, new BusinessReadPriceHighLow()); break;
			case "Price: Low To High" : 
				businessList = businessService.readNumBusiness(numRestaurants, searchString, new BusinessReadPriceLowHigh()); break;
			default : 
				businessList = businessService.readNumBusiness(numRestaurants, searchString, new BusinessReadRatingHighLow());
			}
		} catch (Exception e) {
			forwardToErrorPage(request, response, "Error reading businesses");
			e.printStackTrace();
		}	
		
		session.setAttribute("businessList", businessList);
		
		RequestDispatcher rd = request.getRequestDispatcher("businessList.jsp");
		rd.forward(request, response);
		
	}
	
	/**
	 * Gets the number of business to return
	 * @param request
	 * @return the number of business to return.
	 */
	private int getNumBusiness(HttpServletRequest request, HttpSession session) {
		
		int numRestaurants;

		// Get number of restuarants to view, if not set use default value. 
		if(request.getParameter("numRestaurants") != null){
			numRestaurants = Integer.valueOf(request.getParameter("numRestaurants").toString());		
		} else if (session.getAttribute("numRestaurants") != null) {
			numRestaurants = Integer.valueOf(session.getAttribute("numRestaurants").toString());
		} else {
			numRestaurants = 5; 
		}	
		
		return numRestaurants;
		
	}
	
	/**
	 * Gets the search string
	 * @param request
	 * @return the search string 
	 */
	private String getSearchString(HttpServletRequest request, HttpSession session) {
		String searchString = " ";
		
		if(request.getParameter("Search") != null){
			searchString = (request.getParameter("Search").toString());		
		} else if (session.getAttribute("Search") != null) {
			searchString = session.getAttribute("Search").toString();
		} else searchString = " "; 	
		
		return searchString;
		
	}
	
	/**
	 * Gets the sorting string
	 * @param request
	 * @return The sorting string 
	 */
	private String getSortingString(HttpServletRequest request, HttpSession session) {
		String sortingString = "";

		if(request.getParameter("sorting") != null){
			sortingString = (request.getParameter("sorting").toString());
			
		} else if (session.getAttribute("sorting") != null) {
			sortingString = session.getAttribute("sorting").toString();
		} else {
			sortingString = "Rating High To Low";
		}
		
		return sortingString;
	}
	
	
	private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
		session = req.getSession(true);
		session.setAttribute("errorMessage", message);
		rd.forward(req, resp);
	}
	
	
	

}
