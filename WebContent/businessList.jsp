<%@page import="java.io.BufferedInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.algonquin.Capstone.beans.*" %>
<%@page import="com.algonquin.Capstone.dao.*" %>
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

<h1>Check out these local Restaurants! (CST8288)</h1>
  <div class="businessInfo" >
		<table>
			<%
			ArrayList<Business> businessList = new ArrayList<>();
				BusinessDao businessDao = new BusinessDao();
				businessList = businessDao.readNumBusiness(5);
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