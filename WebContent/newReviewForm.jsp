<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
<!DOCTYPE html>
<html>
<head>
  <title>New Review Creator</title>
<style>
     body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }
    .review {
        border: 1px solid black;
        padding: 10px;
        width: 800px;
        background-color: #FFFFFF;
    }

    .business_ID {
        border: 1px solid black;
        padding: 5px;
    }

    .date {
        border: 1px solid black;
        padding: 5px;
    }

    .FoodRating {
        border: 1px solid black;
        padding: 5px;
    }
    .ServiceRating {
        border: 1px solid black;
        padding: 5px;
    }

    .AtmosphereRating {
        border: 1px solid black;
        padding: 5px;
    }

    .PriceRating {
        border: 1px solid black;
        padding: 5px;
    }

    .Content {
        border: 1px solid black;
        padding: 5px;
    }
    .OverallRating {
        border: 1px solid black;
        padding: 5px;
    }

    .user_ID {
        border: 1px solid black;
        padding: 5px;
    }

    .UsefulCount {
        border: 1px solid black;
        padding: 5px;
    }

    .image {
        border: 1px solid black;
        padding: 5px;
    }
</style>
</head>
<body>
<%
Business business = new Business(); 
BusinessDao businessDao = new BusinessDao();
int businessId = Integer.valueOf(request.getParameter("businessId"));
business = businessDao.readBusiness(businessId);


%>

<a href="home.jsp" class="btn">Home</a>
<h1>Create a new review! for <%out.print(business.getName());%> (CST8288)</h1>


<a href="newReviewForm.jsp?id=<%out.print(business.getId());%>" class="btn">Review this Restaurant!</a>

  <div class="review">
  <form action = "createNewReview.jsp" method = "POST">
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

