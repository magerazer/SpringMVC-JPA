<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Liste des comptes</h1>
	<table>
	<tr>
	<th>mail</th>
	<th>nom</th>
	<th>prenom</th>
	<th>annee de naissance</th>
	</tr>
	<c:forEach var="compte" items="${comptes}">
	<tr>
		<td>${compte.mail}</td><td> ${compte.nom }</td><td> ${compte.prenom }</td><td> ${compte.anneeNaissance }</td>
	
	</tr>	
	</c:forEach>
	</table>
</body>
</html>