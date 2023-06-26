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
<title>Business Reviews viewer</title>
<link rel="stylesheet" href="style.css" >
<!-- <style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f2f2f2;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
}

h1 {
	text-align: center;
	margin-bottom: 30px;
}

.button {
	display: inline-block;
	background-color: #4CAF50;
	color: #fff;
	padding: 10px 20px;
	text-decoration: none;
	border-radius: 4px;
}
</style> -->
</head>
<body>

	<%
Business business = new Business(); 
BusinessDao businessDao = new BusinessDao();
int businessId = Integer.valueOf(request.getParameter("businessId"));


business = businessDao.readBusiness(businessId);

ArrayList<Review> reviewList = new ArrayList<>();
ReviewDao reviewDao = new ReviewDao();
reviewList = reviewDao.readNumReviews(businessId, 5);

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
	<table>
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
		<td>
		<% // only show button to create new review to authenticated users. 
		if (authenticated){ %>
			<a href="newReviewForm.jsp?businessId=<%out.print(business.getId());%>" class="btn">Review this Restaurant!</a>	
		<%}%>
		
		</td>
		</tr>
		
		<tr>
		<td>
		Reviews:
		</td>
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
		<a href="updateUsefulCount.jsp?reviewId=<%out.print(review.getID());%>&businessId=<%out.print(business.getId());%>" class="btn">Yes</a>
		</td>
			
		</tr>
		<tr></tr>

		<tr></tr>

		<% } %>


	</table>



</body>
</html>