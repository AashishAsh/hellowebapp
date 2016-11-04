<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
	h3 {
		color:gray;
	}
	
	div {
		font-size: 12px;
		color:blue;
	}
	
	p {
		font-size:14px;
		font-weight:bold;
		color:red;
	}
	
</style>

<script>

	console.log("Hello!");

</script>

</head>
<body>
<h3>Welcome!</h3>

<%!
	/* JSP Declaration Tag */
	String name = "James";
%>

<%
	// JSP Scriptlet
	out.println("Welcome from java code...");

	/*
		"<%= represents an expression tag"
	*/
%>

<div>Dummy text...</div>

<%
	// out is an Implicit object of JSP
	out.println(name);
%>

<p>
	This is a paragraph for <%= name %>...!!
</p>

</body>
</html>