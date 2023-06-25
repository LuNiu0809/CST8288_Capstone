<%@page import="java.io.BufferedInputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.algonquin.Capstone.beans.*" %>
<%@page import="com.algonquin.Capstone.dao.*" %>
<%@page import="java.util.ArrayList" %>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Business Viewer</title>

<style>
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
     
</style>
</head>
<body>
<a href="home.jsp" class="btn">Home</a>
<h1>Check out these local Restaurants! (CST8288)</h1>
  <div class="Businesslist">
		<table>
			<%
			ArrayList<Business> businessList = new ArrayList<>();
			BusinessDao businessDao = new BusinessDao();
			businessList = businessDao.readNumBusiness(5);

			for (Business business : businessList) {
			%>
			<tr>
			<td>
			<%out.print(business.getName()); %>
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
			<td>
			</tr>
			<tr></tr>
			<tr>
			<td>
		

			<a href="businessReviews.jsp?id=<%out.print(business.getId());%>" class="btn">See Reviews</a>
			

			<td>
			
			</tr>
			<tr></tr>

			<%
			}
			%>

		</table>

	</div>
	

</body>
</html>