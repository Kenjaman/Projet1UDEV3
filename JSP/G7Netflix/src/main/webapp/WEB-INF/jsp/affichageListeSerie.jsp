<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage Général</title>
</head>
<body>
	<div id="logo">
		<img alt="logo" src="<c:url value='/images/KenFlixIcon.svg'/>" />
	</div>
	<h1>LES SERIES</h1>
	<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
	<table>
		<c:forEach items="${liste}" var="item">
			<tr>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}'><c:out value="${item.nom}" /></a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=modifier'>Modifier</a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>