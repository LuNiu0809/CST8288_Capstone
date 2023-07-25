<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css" >
  <title>Home Page</title>

</head>
<body>
<!-- Common Header to all pages -->
<%@include file="header.jsp"%>

  <div class="container">
    <h1>Welcome to the Ottawa Restaurant Review Website! (CST8288)</h1>
    <p>Discover and share your favorite restaurants with the rest of Ottawa.</p>
    <p>Start by exploring reviews.</p>
    <p>Need to login? Just click bellow!</p>
    <p>
     <!--   <a href="reviewlist.jsp" class="button">Explore Reviews</a>-->
      <a href="GetBusinessList" class="button">Explore Restaurants!</a>
      <a href="login.jsp" class="button">Login?</a>
    </p>
  </div>
</body>
</html>