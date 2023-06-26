<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Log In Page</title>
  <link rel="stylesheet" href="style.css" >

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