<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
	<%@page import="com.algonquin.Capstone.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
Review review = new Review();
ReviewService reviewService = new ReviewService();


int reviewId = Integer.valueOf(request.getParameter("reviewId"));
review = reviewService.readReview(reviewId);
review.increaseUsefulCount();
reviewService.updateUsefulCount(reviewId, review.getUsefulCount());

RequestDispatcher rd = request.getRequestDispatcher("businessReviews.jsp?");
rd.forward(request, response);

%>
</body>
</html>