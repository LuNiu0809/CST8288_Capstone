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
int numRestaurants;
if(request.getParameter("numRestaurants") != null){
	numRestaurants = Integer.valueOf(request.getParameter("numRestaurants"));
} else numRestaurants = 5; %>

<h1>Check out these local Restaurants! (CST8288)</h1>

<form action = "businessList.jsp" method = "post">
<label for="numRestaurants">Select Number of Restaurants</label>
	<select name="numRestaurants" id="numRestaurants" onchange="this.form.submit()">
		<option value = 5>5</option>
		<option value = 10>10</option>
		<option value = 25>25</option>
		<option value = 50>50</option>	
		<option selected ="selected"><%out.print(numRestaurants);%></option>
	</select>
</form>	
  <div class="businessInfo" >
		<table>
			<%
			ArrayList<Business> businessList = new ArrayList<>();
				BusinessService businessService = new BusinessService();
				businessList = businessService.readNumBusiness(numRestaurants, EnumRatingSort.OVERALL_RATING_HIGH_LOW);
				for (Business business : businessList) {				
			%>
			
			<tr>
			<td></td>
			<td>
			<%out.print(business.getName()); %>
			</td>
			<td>
			<a href="businessReviews.jsp?businessId=<%out.print(business.getId());%>" class="button">See Reviews</a>	
			</td>
			</tr>
			
			<tr>
			<td>
			<br> Overall Rating <%out.print(business.getOverallRating()); %>  / 5
			<br> <%out.print(business.getFoodType() + " - Price Rating " +  business.getPriceRating() + " / 5");%>		
			<br> <%out.print(business.getAddress());%>			
			<br> <%out.print(business.getPhoneNumber());%>			
			<br> <%out.print(business.getEmail());%>	
			</td>	
			<td>
			Hours of Operation:
			<br>
			<%out.print(business.getHoursOfOperation());%>	
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