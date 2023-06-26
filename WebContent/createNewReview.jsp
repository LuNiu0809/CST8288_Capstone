<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.lang.String"%>
    <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
	<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Review Creation Status</title>
<link rel="stylesheet" href="style.css" >
</head>

<body>
<!-- Common Header to all pages -->
<%@include file="header.jsp"%>
<%
Business business = new Business(); 
BusinessDao businessDao = new BusinessDao();
int businessId = Integer.valueOf(request.getParameter("businessId"));
business = businessDao.readBusiness(businessId);

Review review = new Review();
ReviewDao reviewDao = new ReviewDao();

//Create a new session
session = request.getSession(true);
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
int foodRating = Integer.valueOf(request.getParameter("foodRating"));
int serviceRating = Integer.valueOf(request.getParameter("serviceRating"));
int atmosphereRating = Integer.valueOf(request.getParameter("atmosphereRating"));
int priceRating = Integer.valueOf(request.getParameter("priceRating"));
String content = request.getParameter("content");


int userId = user.getId(); 

review.setAuthorID(userId);
review.setBusinessID(businessId);

review.setFoodRating(foodRating);
review.setServiceRating(serviceRating);
review.setAtmosphereRating(atmosphereRating);
review.setPriceRating(priceRating);
review.setContent(content);
review.generateCreationDate();

int createStatus = reviewDao.createReview(review);


if (createStatus > 0){
	
	// When review is added, calculate the new ratings for the business
	ArrayList <Review> reviewList = new ArrayList<>();
	reviewList = reviewDao.readAllReviews(businessId);
			
	business.calculateRatings(reviewList);
	
	// Update the business ratings in the database. 
	int newPriceRating = business.getPriceRating();
	int newOverallRating = business.getOverallRating();
	int businessUpdateStatus = businessDao.updateRatings(businessId, newPriceRating, newOverallRating);
	
	if (businessUpdateStatus > 0){
		RequestDispatcher rd = request.getRequestDispatcher("businessReviews.jsp?");
		rd.forward(request, response);
	}
		
	
} else {
	out.print("Error Creating Review!");
}

%>

</body>
</html>