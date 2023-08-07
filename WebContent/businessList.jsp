<%@page import="com.algonquin.Capstone.servlets.BusinessServlet"%>
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
if(session.getAttribute(BusinessServlet.NUMBER_BUSINESS_STRING) != null){
	numRestaurants = Integer.valueOf(session.getAttribute(BusinessServlet.NUMBER_BUSINESS_STRING).toString());
} else { 
	numRestaurants = 5; 
}

// Get Search String
if(session.getAttribute(BusinessServlet.BUSINESS_SEARCH_STRING) != null){
	searchString = session.getAttribute(BusinessServlet.BUSINESS_SEARCH_STRING).toString();
} else {
	searchString = ""; 
}

//Get sorting, if not set use default value
if(session.getAttribute(BusinessServlet.BUSINESS_SORTING_STRING) != null){
	sortingString = session.getAttribute(BusinessServlet.BUSINESS_SORTING_STRING).toString();
} else {
	sortingString = "Rating High To Low";
}

// Get business List,
if(session.getAttribute(BusinessServlet.BUSINESS_LIST_STRING) != null){	
	businessList = (ArrayList<Business>) session.getAttribute(BusinessServlet.BUSINESS_LIST_STRING);
}

%>

<h1>Check out these local Restaurants! (CST8288)</h1>
<!-- Select the number of business to view -->
	<form action="GetBusinessList" method="post">
		<label for=<%=BusinessServlet.NUMBER_BUSINESS_STRING%>>Select Number of Restaurants</label> 
		<select name=<%=BusinessServlet.NUMBER_BUSINESS_STRING%> 
			id=<%=BusinessServlet.NUMBER_BUSINESS_STRING%>
			onchange="this.form.submit()">
			<option selected="selected"><%=numRestaurants%></option>
			<option value=5>5</option>
			<option value=10>10</option>
			<option value=25>25</option>
			<option value=50>50</option>
		</select>
		<!-- Select the sorting behaviour-->
		<label for=<%=BusinessServlet.BUSINESS_SORTING_STRING%>>Sort By:</label> 
		<select name=<%=BusinessServlet.BUSINESS_SORTING_STRING%> id=<%=BusinessServlet.BUSINESS_SORTING_STRING%> onchange="this.form.submit()">
			<option selected="selected"><%=sortingString%></option>
			<option value="Rating High To Low">Rating: High To Low</option>
			<option value="Rating: Low To High">Rating: Low To High</option>
			<option value="Price: High To Low">Price: High To Low</option>
			<option value="Price: Low To High">Price: Low To High</option>
		</select> 
		<label for=<%=BusinessServlet.BUSINESS_SEARCH_STRING%>>Search:</label> 
		<input type="text"id=<%=BusinessServlet.BUSINESS_SEARCH_STRING%> name=<%=BusinessServlet.BUSINESS_SEARCH_STRING%>
		 onchange="this.form.submit()" value=<%=searchString%> >
	</form>

	<div class="businessInfo">
		<table>
			<%for (Business business : businessList) {%>
			<tr>
				<td></td>
				<td>
					<%=business.getName()%>
				</td>
				<td><a
					href="GetBusinessReviews?businessId=<%=business.getId()%>"
					class="button">See Reviews</a></td>
			</tr>
			<tr>
				<td><br> Overall Rating <%=business.getOverallRating()%>
					/ 5 <br> <%=business.getFoodType() + " - Price Rating " +  business.getPriceRating() + " / 5"%>
					<br> <%=business.getAddress()%> <br> <%=business.getPhoneNumber()%>
					<br> <%out.print(business.getEmail());%></td>
				<td>Hours of Operation: <br> <%=business.getHoursOfOperation()%>
				</td>
				<td>
					<%=business.getDescription()%>
				</td>
			</tr>
			<tr></tr>
			<% } %>
		</table>
	</div>
</body>
</html>