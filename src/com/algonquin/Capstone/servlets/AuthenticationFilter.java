package com.algonquin.Capstone.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/businessList.jsp", "/businessReviews.jsp", "/newReviewForm.jsp"})
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the session (create a new one if not exists)
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("authenticated") == null) {
            // If the user is not authenticated, redirect them to the login page
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        // User is authenticated, proceed with the request
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code (if needed)
    }

    public void destroy() {
        // Cleanup code (if needed)
    }
}

