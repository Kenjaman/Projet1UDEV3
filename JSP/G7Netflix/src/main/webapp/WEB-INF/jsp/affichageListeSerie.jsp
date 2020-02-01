<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage Général</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<div class='content'>
		<h2>LES SERIES</h2>
		<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
		<table>
			<c:forEach items="${liste}" var="item">
				<tr>
					<td class='name'><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}'><c:out
								value="${item.nom}" /></a></td>
					<td><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=modifier'>Modifier</a></td>
					<td><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=supprimer'>Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="liens">
			<button onclick="history.go(-1)">Retour</button>

		</div>
	</div>
</body>
</html>