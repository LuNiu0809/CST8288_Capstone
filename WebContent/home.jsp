<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css" >
  <title>Home Page</title>
<!--   <style>
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
  </style> -->
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
      <a href="businessList.jsp" class="button">Explore Restaurants!</a>
      <a href="login.jsp" class="button">Login?</a>
    </p>
  </div>
</body>
</html>