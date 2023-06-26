<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Sign Up Page</title>
  <link rel="stylesheet" href="style.css" >
<!-- <style>
     body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }
    .details {
        border: 1px solid black;
        padding: 10px;
        width: 800px;
        background-color: #FFFFFF;
    }

  
    }
</style> -->
</head>
<body>
<!-- Common Header to all pages -->
<%@include file="header.jsp"%>

<h1>New Member Signup! (CST8288)</h1>
 <div class="details">
    <h1>User Registration</h1>
    
    <form action="register" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>
        <div>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>
        <div>
            <input type="submit" value="Sign Up">
        </div>
    </form>
</div>
</body>
</html>