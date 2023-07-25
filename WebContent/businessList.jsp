<%@page import="java.io.BufferedInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.algonquin.Capstone.beans.*" %>
<%@page import="com.algonquin.Capstone.dao.*" %>
<%@page import="com.algonquin.Capstone.service.*"%>
<%@page import="java.util.ArrayList" %>


<html>
<head>
<meta charset="ISO-8859-1">

<title>Business Viewer</title>
<link rel="stylesheet" href="style.css" >

</head>
<body>
<!-- Common Header to all pages -->
<%@include file="header.jsp"%>

<%
int numRestaurants = 0;
String sortingString = "s1";
String searchString = "s2";
ArrayList<Business> businessList = new ArrayList<>();

// Get number of restuarants to view, if not set use default value. 
if(session.getAttribute("numRestaurants") != null){
	numRestaurants = Integer.valueOf(session.getAttribute("numRestaurants").toString());
} else { 
	numRestaurants = 5; 
}

// Get Search String
if(session.getAttribute("Search") != null){
	searchString = session.getAttribute("Search").toString();
} else {
	searchString = ""; 
}

//Get sorting, if not set use default value
if(session.getAttribute("sorting") != null){
	sortingString = session.getAttribute("sorting").toString();
} else {
	sortingString = "Rating High To Low";
}

// Get business List,
if(session.getAttribute("businessList") != null){	
	businessList = (ArrayList<Business>) session.getAttribute("businessList");
}

%>

<h1>Check out these local Restaurants! (CST8288)</h1>
<!-- Select the number of business to view -->
	<form action="GetBusinessList" method="post">
		<label for="numRestaurants">Select Number of Restaurants</label> <select
			name="numRestaurants" id="numRestaurants"
			onchange="this.form.submit()">
			<option selected="selected">
				<%out.print(numRestaurants);%>
			</option>
			<option value=5>5</option>
			<option value=10>10</option>
			<option value=25>25</option>
			<option value=50>50</option>

		</select>
		<!-- Select the sorting behaviour-->
		<label for="businessSorting">Sort By:</label> 
		<select name="sorting" id="sorting" onchange="this.form.submit()">
			<option selected="selected">
				<%out.print(sortingString);%>
			</option>
			<option value="Rating High To Low">Rating: High To Low</option>
			<option value="Rating: Low To High">Rating: Low To High</option>
			<option value="Price: High To Low">Price: High To Low</option>
			<option value="Price: Low To High">Price: Low To High</option>
		</select> 
		<label for="Search">Search:</label> 
		<input type="text"id="Search" name="Search" onchange="this.form.submit()" value=<%out.print(searchString);%> >
	</form>

	<div class="businessInfo">
		<table>
			<%for (Business business : businessList) {%>
			<tr>
				<td></td>
				<td>
					<%out.print(business.getName()); %>
				</td>
				<td><a
					href="businessReviews.jsp?businessId=<%out.print(business.getId());%>"
					class="button">See Reviews</a></td>
			</tr>
			<tr>
				<td><br> Overall Rating <%out.print(business.getOverallRating()); %>
					/ 5 <br> <%out.print(business.getFoodType() + " - Price Rating " +  business.getPriceRating() + " / 5");%>
					<br> <%out.print(business.getAddress());%> <br> <%out.print(business.getPhoneNumber());%>
					<br> <%out.print(business.getEmail());%></td>
				<td>Hours of Operation: <br> <%out.print(business.getHoursOfOperation());%>
				</td>
				<td>
					<%out.print(business.getDescription());%>
				</td>
			</tr>
			<tr></tr>
			<% } %>
		</table>
	</div>
</body>
</html>