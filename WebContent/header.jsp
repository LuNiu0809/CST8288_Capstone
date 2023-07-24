<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
<a href="home.jsp" class="button">Home</a>
<a href="businessList.jsp" class="button">Explore Restaurants!</a>


<%
//Create a new session
session = request.getSession(true);
String username = "";
boolean authenticated = false;

if (session.getAttribute("username") != null){
	 username = session.getAttribute("username").toString();
	 authenticated = true;
} %>

<%
    if (authenticated) {
        // If the user is authenticated, display the logout link
    %>
        <a href="logout" class="button">Logout</a>
    <% } else { %>
        <a href="login.jsp" class="button">Login</a>
        <a href="signup.jsp" class="button">Register</a>
    <% } %>

<div class="currentUser">

<%

if (session.getAttribute("authenticated") != null){
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