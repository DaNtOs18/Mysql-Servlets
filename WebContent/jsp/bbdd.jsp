<%@page
	import="org.apache.jasper.tagplugins.jstl.core.ForEach, java.util.ArrayList, java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta las Bases de Datos</title>
</head>
<body>

	<h1>Lista de Bases de Datos</h1>

	<ul>
		
		<c:forEach items="${db}" var="elemento" >

    	<li>${elemento}</li>

		</c:forEach>

	</ul>

</body>
</html>