package com.algonquin.Capstone.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.Capstone.beans.User;
import com.algonquin.Capstone.service.UserService;

//@WebServlet("/")


@WebServlet(name = "userServlet", urlPatterns = {"/login", "/register", "/logout"} )
public class UserServelt extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private HttpSession session;

	public UserServelt() {
		// TODO Auto-generated constructor stub
		userService = new UserService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();

		if (requestURI.endsWith("/register")) {
			// Handle user registration
			register(request, response);
		} else if (requestURI.contains("/login")) {
			// Handle user login
			login(request, response);
		} else if (requestURI.endsWith("/logout")) {
			// Handle user logout
			// logout(request, response);
		} else {
			// Invalid URL
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean authenticated = userService.authenticate(username, password);

		if (authenticated) {

			// Create a new session
			session = request.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("authenticated", true);

			RequestDispatcher dispatcher = request.getRequestDispatcher("businessList.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("login.jsp?error=1"); // Redirect back to login page with error flag
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Extract user information from request parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		// Create a User object
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		// Call the registration service method
		UserService userService = new UserService();
		boolean registrationSuccess = userService.registerUser(user);

		if (registrationSuccess) {
			// Registration successful, redirect to a success page or login page
			response.sendRedirect("login.jsp");
		} else {
			// Registration failed, redirect back to the registration page with an error
			// message
			request.setAttribute("errorMessage", "Registration failed. Please try again.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
			dispatcher.forward(request, response);
		}

	}

}
