<%@page import="com.algonquin.Capstone.servlets.ReviewServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.algonquin.Capstone.beans.*"%>
<%@page import="com.algonquin.Capstone.dao.*"%>
<%@page import="com.algonquin.Capstone.service.*"%>
<%@page import="java.util.ArrayList"%>
<%@page errorPage= "Error.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Reviews viewer</title>
<link rel="stylesheet" href="style.css" >

</head>
<body>

<%
Business business = new Business(); 
BusinessService businessService = new BusinessService();
int businessId = Integer.valueOf(request.getParameter("businessId"));

UserReviewUsefulService userReviewUsefulService = new UserReviewUsefulService();
ReviewService reviewService = new ReviewService();

business = businessService.readBusiness(businessId);

int numReviews;
String sortingString = "";
ArrayList<Review> reviewList = new ArrayList<>();

//Get number of restuarants to view, if not set use default value. 
if(session.getAttribute(ReviewServlet.NUMBER_REVIEWS_STRING) != null){
	numReviews = Integer.valueOf(session.getAttribute(ReviewServlet.NUMBER_REVIEWS_STRING).toString());
} else { numReviews = 5; 
}

//Get sorting, if not set use default value
if(session.getAttribute(ReviewServlet.REVIEW_SORTING_STRING) != null){
	sortingString = session.getAttribute(ReviewServlet.REVIEW_SORTING_STRING).toString();
} else { sortingString = "Newest";
}

// Get Review List
if(session.getAttribute(ReviewServlet.REVIEW_LIST_STRING) != null){
	reviewList = (ArrayList<Review>) session.getAttribute(ReviewServlet.REVIEW_LIST_STRING);
}


UserDao userDao = new UserDao();
int currentUserID = 0;
%>

<!-- Common Header to all pages -->
<%@include file="header.jsp"%>
<%
//Create a new session
session = request.getSession(true);
if (session.getAttribute("authenticated") != null){
	 authenticated = (boolean) session.getAttribute("authenticated");
}
if (session.getAttribute("username") != null){
	 username = session.getAttribute("username").toString();
	 User currentUser = userDao.getUserByUsername(username);
	 currentUserID = currentUser.getId();
}
%>

	<h1><%=business.getName()%></h1>
	<!-- Select the number of Reviews to view -->
	<form action = "GetBusinessReviews" method = "post">
	<label for=<%=ReviewServlet.NUMBER_REVIEWS_STRING%>>Select Number of Restaurants</label>
		<select name=<%=ReviewServlet.NUMBER_REVIEWS_STRING%> id=<%=ReviewServlet.NUMBER_REVIEWS_STRING%> onchange="this.form.submit()">
			<option selected ="selected"><%=numReviews%></option>
			<option value = 5>5</option>
			<option value = 10>10</option>
			<option value = 25>25</option>
			<option value = 50>50</option>			
		</select>
	<!-- Select the sorting behaviour-->
	
	<label for=<%=ReviewServlet.REVIEW_SORTING_STRING%>>Sort By:</label>
		<select name=<%=ReviewServlet.REVIEW_SORTING_STRING%> id=<%=ReviewServlet.REVIEW_SORTING_STRING%> onchange="this.form.submit()">
			<option selected ="selected"><%=sortingString%></option>
			<option value = "Rating High To Low"> Rating: High To Low</option>
			<option value = "Rating: Low To High">Rating: Low To High</option>
			<option value = "Newest">Newest</option>
			<option value = "Most Useful">Most Useful</option>			
		</select>
		<!-- Make sure the same business ID is passed to the page on refresh for sorting.-->
		<input type="hidden" name="businessId" value=<%=businessId%>><td>
	</form>	
	<div class="businessInfo">
	<table>
	<tr>
	<td></td>
	<td></td>
	<td>
		<% // only show button to create new review to authenticated users. 
		if (authenticated){ %>
			<a href="newReviewForm.jsp?businessId=<%=business.getId()%>" class="button">Review this Restaurant!</a>	
		<%}%>	
	</td>
	</tr>	
		<tr>
			<td>
			<br> Overall Rating <%=business.getOverallRating()%> / 5 
			<br> <%=business.getFoodType() + " - Price Rating " +  business.getPriceRating() + " / 5"%>
			<br> <%=business.getAddress()%> <br> <%=business.getPhoneNumber()%>
			<br> <%=business.getEmail()%>
			</td>
			<td>Hours of Operation: <br> <%=business.getHoursOfOperation()%></td>
			<td><%=business.getDescription()%><td>
			</tr>
		<tr></tr>	
		<tr></tr>
	</table>
</div>
	<div class="businessInfo">
	<table>
		<tr>
		<td></td>
		<th>Reviews</th>
		</tr><% for (Review review : reviewList) { %>
		<tr>
		<td>
		<%
		User reviewAuther = reviewService.getReviewAuthor(review);
		out.print( "Author: " + reviewAuther.getUserName() + " Posted :" + review.getCreationDate());
		 %>
		<br> Overall: <%=review.getOverallRating() %>  / 5
		<br> Price: <%=review.getPriceRating()%>  / 5
		<br> Food:  <%=review.getFoodRating()%>  / 5
		<br> Service:  <%=review.getServiceRating()%>  / 5
		<br> Atmosphere:  <%=review.getAtmosphereRating()%>  / 5
		</td>
		<td>
		<%=review.getContent()%>
		</td>
		<td>
		<%out.print(review.getUsefulCount() + " Other users found this reivew helpful");
		// If the user is authenticated they can see the button to indicate if a review was helpful or not.
		if (authenticated){ 
			out.print("<br>Was this review Helpful?");	
			String buttonText = "Yes";
			boolean userHasFoundUseful = userReviewUsefulService.checkUserReviewHelpfulRecord(currentUserID, review.getID());
			if(userHasFoundUseful){
				buttonText = "No";
			}%>	
			<a href="UpdateUsefulCount?reviewId=<%=review.getID()%>&businessId=<%=business.getId()%>" class="button"><%=buttonText%></a>
		<%}%>		
		</td>			
		</tr>
		<tr></tr>
		<tr></tr>
		<% } %>
	</table>
</div>
</body>
</html>