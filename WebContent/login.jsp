<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Log In Page</title>
  <link rel="stylesheet" href="style.css" >
<!-- <style>
     body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }
    .login {
        border: 1px solid black;
        padding: 10px;
        width: 800px;
        background-color: #FFFFFF;
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

<h1>Member Login Page! (CST8288)</h1>
 <div class="login">
    
    <% String error = request.getParameter("error");
       if (error != null && error.equals("1")) { %>
       <p style="color: red;">Invalid username or password!</p>
    <% } %>
    <form action="login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit" >Login</button>
        </div>
    </form>
    
	<p>
     <!--   <a class="button">Submit</a>-->
      <a href="signup.jsp" class="button">New Member?</a>
    </p>
</div>
</body>
</html>