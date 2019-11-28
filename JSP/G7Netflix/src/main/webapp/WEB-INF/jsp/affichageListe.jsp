<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  	<meta charset="UTF-8">
    <title>Affichage Général/title>
  </head>
  <body>
  	<a href='./<c:out value="${entiteTraiter}"/>?action=ajouter'>Ajouter</a>
 	<table>
		<c:forEach items="${liste}" var="item">
			<tr>
				<td><a href='./<c:out value="${entiteTraiter}"/>?id=${item.id}'><c:out value="${item.nom}"/></a></td>
				<td><a href='./<c:out value="${entiteTraiter}"/>?action=modifier'>Modifier</a></td>
				<td><a href='./<c:out value="${entiteTraiter}"/>?action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table> 
  </body>
</html>