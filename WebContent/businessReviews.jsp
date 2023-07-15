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


business = businessService.readBusiness(businessId);

ArrayList<Review> reviewList = new ArrayList<>();
ReviewService reviewService = new ReviewService();
reviewList = reviewService.readNumReviews(businessId, 5);

UserDao userDao = new UserDao();

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
%>

	<h1>
		<%out.print(business.getName());%>
	</h1>
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
		<%out.print(review.getUsefulCount() + " Other users found this reivew helpful <br> Was this review Helpful?");%>
		<a href="updateUsefulCount.jsp?reviewId=<%out.print(review.getID());%>&businessId=<%out.print(business.getId());%>" class="button">Yes</a>
		</td>
			
		</tr>
		<tr></tr>

		<tr></tr>

		<% } %>


	</table>
</div>


</body>
</html>