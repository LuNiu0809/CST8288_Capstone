package com.algonquin.Capstone.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * Servlet implementation class LogsServlet
 */
@WebServlet(description = "Loggy Logs", urlPatterns = { "/logs" })
public class AppServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppServlet() {
		super();

	}

}
