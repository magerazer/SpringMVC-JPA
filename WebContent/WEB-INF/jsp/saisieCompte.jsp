<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>ma première JSP Spring !!!!!</h1>
	
	<form:form modelAttribute="compte" action="enregistrerCompte.htm" method="POST">
		<table>
		<tr>	
		<td><form:label path="nom">nom</form:label></td>
		<td><form:input path="nom"/></td>
		<td><form:errors path="nom"/></td>
		</tr>
		<tr>
		<td>prenom :</td>
		<td><form:input path="prenom"/></td>
		<td><form:errors path="prenom"/></td>
		</tr>
		<tr>
		<td>age :</td>
		<td><form:input path="age"/></td>
		<td><form:errors path="age"/></td>		
		</tr>
		<tr>
		<td>mail :</td>
		<td><form:input path="mail"/></td>
		<td><form:errors path="mail"/></td>		
		</tr>
		<tr>
		<td>annee de naissance :</td>
		<td><form:input path="anneeNaissance"/></td>
		<td><form:errors path="anneeNaissance"/></td>		
		</tr>
		<tr>
		<td><form:button name="enregistrer" value="enregistrer">Enregistrer</form:button></td>
		</tr>
		</table>
	</form:form>
	
	<a href="english.htm">english</a>
	<a href="french.htm">french</a>
<br>
<br>
	<a href="listeDesComptes.htm">liste des comptes</a>


</body>
</html>