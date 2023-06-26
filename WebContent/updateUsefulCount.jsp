<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="com.algonquin.Capstone.beans.*"%>
	<%@page import="com.algonquin.Capstone.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
Review review = new Review();
ReviewDao reviewDao = new ReviewDao();

int reviewId = Integer.valueOf(request.getParameter("reviewId"));
review = reviewDao.readReview(reviewId);
review.increaseUsefulCount();
reviewDao.updateUsefulCount(reviewId, review.getUsefulCount());

RequestDispatcher rd = request.getRequestDispatcher("businessReviews.jsp?");
rd.forward(request, response);

%>
</body>
</html>