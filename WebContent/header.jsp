<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
<a href="home.jsp" class="button">Home</a>
<a href="businessList.jsp" class="button">Explore Restaurants!</a>
<a href="login.jsp" class="button">Login</a>
<a href="signup.jsp" class="button">Register</a>


<%
//Create a new session
session = request.getSession(true);
String username = "";

if (session.getAttribute("username") != null){
	 username = session.getAttribute("username").toString();
} %>
<div class="currentUser">

<%

if (session.getAttribute("authenticated") != null){
	boolean authenticated = (boolean) session.getAttribute("authenticated");
	if (authenticated){
		out.print("Current User: " + username);
	} else {
		out.print("Current User: Guest");
	}
} else {
	out.print("Current User: Guest");
}
%>
</div>
</body>
</html>