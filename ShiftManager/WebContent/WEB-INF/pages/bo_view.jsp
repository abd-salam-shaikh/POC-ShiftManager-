<%@page import="java.util.Map"%>
<%@page import="core.bean.Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Object o = request.getAttribute(request.getParameter("object"));
    if (o == null) {
        o = session.getAttribute(request.getParameter("object"));
    }
    Bean b = new Bean(o.getClass());
    Map<String, Object> values = b.getValues(o);
    pageContext.setAttribute("values", values);
    pageContext.setAttribute("names", values.keySet());
%>
<ul class='object'>
	<c:forEach items="${names}" var="name">
		<li class='field'><span class='label'><c:out
					value="${name}" /></span> <span class='value'><c:out
					value="${values[name]}" /></span></li>
	</c:forEach>
</ul>