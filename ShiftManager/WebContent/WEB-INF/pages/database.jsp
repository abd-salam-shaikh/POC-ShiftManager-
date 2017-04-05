<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Database</title>
</head>
<body>
	<%
	    Map<String, List<Map<String, Object>>> map;
	    map = (Map<String, List<Map<String, Object>>>) request.getAttribute("data");
	%>
</body>
</html>