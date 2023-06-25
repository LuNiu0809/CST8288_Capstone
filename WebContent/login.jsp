<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Log In Page</title>
<style>
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

  
    
</style>
</head>
<body>
<a href="home.jsp" class="btn">Home</a>
<h1>Member Login Page! (CST8288)</h1>
  <div class="login">
    <table>
			<tr>
				<td>User Name:</td>
				<td><input type="text" title="txtUName"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" title="txtPassword"></td>
			</tr>
		</table>
		<p>
      <a class="button">Submit</a>
      <a href="signup.jsp" class="button">New Member?</a>
    </p>
		</div>
</body>
</html>