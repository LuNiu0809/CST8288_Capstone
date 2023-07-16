<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
	<%@page import="com.algonquin.Capstone.service.*"%>
<!DOCTYPE html>
<html>
<head>
  <title>New Review Creator</title>
  <link rel="stylesheet" href="style.css" >

</head>
<body>
<%
Business business = new Business(); 
BusinessService businessService = new BusinessService();
int businessId = Integer.valueOf(request.getParameter("businessId"));
business = businessService.readBusiness(businessId);


%>

<!-- Common Header to all pages -->
<%@include file="header.jsp"%>

<h1>Create a new review! for <%out.print(business.getName());%> (CST8288)</h1>


  <div class="review">
<!--  <form action = "createNewReview.jsp" method = "POST"> -->
	<form action = "CreateNewReview" method = "POST">
  
  
		<table>
				<tr>					
					<td><Label for=foodRating>Food Rating Out of 5: </Label></td>
					<td><input type="number" min="0" max="5"  title="foodRating" name="foodRating" required></td>			
				</tr>
				<tr>		
					<td><Label for=serviceRating>Service Rating Out of 5: </Label></td>
					<td><input type="number" min="0" max="5"  title="serviceRating" name="serviceRating" required></td>			
				</tr>
				<tr>		
					<td><Label for=atmosphereRating>Atmosphere Rating Out of 5: </Label></td>
					<td><input type="number" min="0" max="5"  title="atmosphereRating" name="atmosphereRating" required></td>			
				</tr>
				<tr>		
					<td><Label for=priceRating>Price Rating Out of 5: </Label></td>
					<td><input type="number" min="0" max="5"  title="priceRating" name="priceRating" required></td>			
				</tr>
				<tr>		
					<td><Label for=content>Your Review: </Label></td>
					<td><textarea maxlength="250" rows="4" cols="25" wrap="hard" id="content" name="content" required></textarea></td>		
				</tr>
				<tr>
				<!--// Source for code https://stackoverflow.com/questions/8021748/how-to-add-additional-parameters-on-a-form-submitting-it-with-get-method-->
    			<td><input type="hidden" name="businessId" value=<%out.print(business.getId());%>><td>
				</tr>
					
				<tr>
				<td><input type="submit" value="submit"></td>
				</tr>	
		</table>
		</form>
</div>
</body>
</html>

