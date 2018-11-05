<%@page
	import="org.apache.jasper.tagplugins.jstl.core.ForEach, java.util.ArrayList, java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contenido de la tabla</title>
</head>
<body>

	<h1>Contenido de la tabla</h1>

	<ul>

		<c:forEach items="${tables}" var="elemento">

			<li>${elemento}</li>

		</c:forEach>

	</ul>

</body>
</html>