<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
		<form:form modelAttribute="adresse" action="validerAdresse.htm" method="POST">
		<table>
		<tr>	
		<td><form:label path="rue">rue</form:label></td>
		<td><form:input path="rue"/></td>
		<td><form:errors path="rue"/></td>
		</tr>
		<tr>
		<td>code postal :</td>
		<td><form:input path="codePostal"/></td>
		<td><form:errors path="codePostal"/></td>
		</tr>
		<tr>
		<td>ville :</td>
		<td><form:input path="ville"/></td>
		<td><form:errors path="ville"/></td>		
		</tr>
		<tr>
		<td>pays :</td>
		<td><form:input path="pays"/></td>
		<td><form:errors path="pays"/></td>		
		</tr>		
		<tr>
		<td><form:button name="enregistrer" value="enregistrer">Enregistrer</form:button></td>
		</tr>
		</table>
	</form:form>
	



</body>
</html>