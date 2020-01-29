<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2>Episodes de la Saison ${saison.numero} de ${serie.nom}</h2>
	<div id="infoSerie">
		<h3>Infos sur la saison</h3>
		<ul>
			<li>Série : <c:out value="${serie.nom}" /></li>
			<li>Saison : <c:out value="${saison.numero}" /></li>
			<li>Résumé : <c:out value="${saison.resume}" /></li>
			<li>Année de diffusion : <c:out value="${saison.anneeDiffusion}" /></li>
			<li>Statut : <c:out value="${saison.statut.libelle}" /></li>
		</ul>
	</div>
	<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
	<table>
		<c:forEach items="${liste}" var="item">
			<tr>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idserie=${serie.id}&idsaison=${saison.id}&idepisode=${item.id}'><c:out
							value="${item.id}" /></a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=modifier'>Modifier</a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>