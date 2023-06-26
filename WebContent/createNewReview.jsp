<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.lang.String"%>
    <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Review Creation Status</title>
</head>

<body>
<a href="home.jsp" class="btn">Home</a>
<%
Business business = new Business(); 
BusinessDao businessDao = new BusinessDao();
int businessId = Integer.valueOf(request.getParameter("businessId"));
business = businessDao.readBusiness(businessId);

Review review = new Review();
ReviewDao reviewDao = new ReviewDao();

int foodRating = Integer.valueOf(request.getParameter("foodRating"));
int serviceRating = Integer.valueOf(request.getParameter("serviceRating"));
int atmosphereRating = Integer.valueOf(request.getParameter("atmosphereRating"));
int priceRating = Integer.valueOf(request.getParameter("priceRating"));
String content = request.getParameter("content");

int userId = 2; // temp until user code is inplace. 

review.setAuthorID(userId);
review.setBusinessID(businessId);

review.setFoodRating(foodRating);
review.setServiceRating(serviceRating);
review.setAtmosphereRating(atmosphereRating);
review.setPriceRating(priceRating);
review.setContent(content);
review.generateCreationDate();

int createStatus = reviewDao.createReview(review);


if (createStatus > 0){
	out.print("Review successfully created!");
} else {
	out.print("Error Creating Review!");
}

%>

</body>
</html>