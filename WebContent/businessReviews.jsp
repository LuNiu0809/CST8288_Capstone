<%@page import="com.algonquin.Capstone.dao.review.ReviewReadMostUseful"%>
<%@page import="com.algonquin.Capstone.dao.review.ReviewReadNewest"%>
<%@page import="com.algonquin.Capstone.dao.review.ReviewReadRatingLowHigh"%>
<%@page import="com.algonquin.Capstone.dao.review.ReviewReadRatingHighLow"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.algonquin.Capstone.beans.*"%>
<%@page import="com.algonquin.Capstone.dao.*"%>
<%@page import="com.algonquin.Capstone.service.*"%>
<%@page import="java.util.ArrayList"%>
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
EnumRatingSort ratingSort;
ArrayList<Review> reviewList = new ArrayList<>();

//Get number of restuarants to view, if not set use default value. 
if(request.getParameter("numReviews") != null){
	numReviews = Integer.valueOf(request.getParameter("numReviews"));
} else numReviews = 5; 

//Get sorting, if not set use default value
if(request.getParameter("sorting") != null){
	sortingString = request.getParameter("sorting");
	switch (sortingString){
	case "Rating High To Low" : reviewService.setReadBehaviour(new ReviewReadRatingHighLow()); break;
	case "Rating: Low To High" : reviewService.setReadBehaviour(new ReviewReadRatingLowHigh()); break;
	case "Newest" : reviewService.setReadBehaviour(new ReviewReadNewest()); break;
	case "Most Useful" : reviewService.setReadBehaviour(new ReviewReadMostUseful()); break;
	default : reviewService.setReadBehaviour(new ReviewReadNewest());
	}
} else {
	reviewService.setReadBehaviour(new ReviewReadNewest());
	sortingString = "Newest";
}


reviewList = reviewService.readReviews(businessId, numReviews);

UserDao userDao = new UserDao();
int currentUserID = 0;
%>

<!-- Common Header to all pages -->
<%@include file="header.jsp"%>
<%
//Create a new session
session = request.getSession(true);
Boolean authenticated = false;
if (session.getAttribute("authenticated") != null){
	 authenticated = (boolean) session.getAttribute("authenticated");
}
if (session.getAttribute("username") != null){
	 username = session.getAttribute("username").toString();
	 User currentUser = userDao.getUserByUsername(username);
	 currentUserID = currentUser.getId();
}
%>

	<h1>
		<%out.print(business.getName());%>
	</h1>
	<!-- Select the number of Reviews to view -->
	<form action = "businessReviews.jsp" method = "post">
	<label for="numReviews">Select Number of Restaurants</label>
		<select name="numReviews" id="numReviews" onchange="this.form.submit()">
			<option selected ="selected"><%out.print(numReviews);%></option>
			<option value = 5>5</option>
			<option value = 10>10</option>
			<option value = 25>25</option>
			<option value = 50>50</option>			
		</select>
	<!-- Select the sorting behaviour-->
	<label for="businessSorting">Sort By:</label>
		<select name="sorting" id="sorting" onchange="this.form.submit()">
			<option selected ="selected"><%out.print(sortingString);%></option>
			<option value = "Rating High To Low"> Rating: High To Low</option>
			<option value = "Rating: Low To High">Rating: Low To High</option>
			<option value = "Newest">Newest</option>
			<option value = "Most Useful">Most Useful</option>	
			
		</select>
		<!-- Make sure the same business ID is passed to the page on refresh for sorting.-->
		<input type="hidden" name="businessId" value=<%out.print(businessId);%>><td>
	</form>	
		
	
	
	<div class="businessInfo">
	<table>
	<tr>
	<td></td>
	<td></td>
	<td>
		<% // only show button to create new review to authenticated users. 
		if (authenticated){ %>
			<a href="newReviewForm.jsp?businessId=<%out.print(business.getId());%>" class="button">Review this Restaurant!</a>	
		<%}%>
		
	</td>
	</tr>	
		<tr>
			<td>
			<br> Overall Rating <%out.print(business.getOverallRating()); %> / 5 
			<br> <%out.print(business.getFoodType() + " - Price Rating " +  business.getPriceRating() + " / 5");%>
			<br> <%out.print(business.getAddress());%> <br> <%out.print(business.getPhoneNumber());%>
			<br> <%out.print(business.getEmail());%>
			</td>
			<td>Hours of Operation: <br> <%out.print(business.getHoursOfOperation());%>
			</td>
			<td>
				<%out.print(business.getDescription());%>	
			<td>
			</tr>
	
		<tr>
		<tr></tr>
		
		<tr>
		
		
		</tr>
	</table>
</div>
	<div class="businessInfo">
	<table>
		
		<tr>
		<td></td>
		<th>
		Reviews
		</th>
		</tr>	
		<% for (Review review : reviewList) { %>
		<tr>
		<td>
		
		<%
		User reviewAuthor = new User();
		reviewAuthor = userDao.getUserById(review.getAuthorID());
		out.print( "Author: " + reviewAuthor.getUserName() + " Posted :" + review.getCreationDate());
		 %>
		<br> Overall: <%out.print(review.getOverallRating()); %>  / 5
		<br> Price: <%out.print(review.getPriceRating()); %>  / 5
		<br> Food:  <%out.print(review.getFoodRating()); %>  / 5
		<br> Service:  <%out.print(review.getServiceRating()); %>  / 5
		<br> Atmosphere:  <%out.print(review.getAtmosphereRating()); %>  / 5
		</td>
		<td>
		<%out.print(review.getContent());%>
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
			<a href="UpdateUsefulCount?reviewId=<%out.print(review.getID());%>&businessId=<%out.print(business.getId());%>" class="button"><%out.print(buttonText);%></a>
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