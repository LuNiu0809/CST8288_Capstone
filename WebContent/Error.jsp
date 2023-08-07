<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.lang.String"%>   
   <%@page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" href="style.css" >
</head>

<body>
<!-- Common Header to all pages -->
<%@include file="header.jsp"%>
<p>
<%
String message = "Error";
if(session.getAttribute("errorMessage") != null){
	 message = session.getAttribute("errorMessage").toString();
	 out.print(message);
} else {
	out.print("An Error Occurred, Please try again later.");
}

%>


</p>

</body>
</html>