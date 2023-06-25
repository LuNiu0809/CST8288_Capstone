<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Sign Up Page</title>
<style>
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
</style>
</head>
<body>
<a href="home.jsp" class="btn">Home</a>
<h1>New Member Signup! (CST8288)</h1>
  <div class="details">
    <table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" title="txtFirstName"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" title="txtLastName"></td>
			</tr>
			<tr>
				<td>Role?:</td>
				<td><input type="text" title="txtRole"></td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td><input type="text" title="txtUName"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" title="txtPassword"></td>
			</tr>
			<tr>
				<td>Re-enter Password:</td>
				<td><input type="text" title="txtPasswordCheck"></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit"></td>
				<tr>
		</table>
		</div>
</body>
</html>