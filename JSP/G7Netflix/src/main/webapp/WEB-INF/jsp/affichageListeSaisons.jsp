<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage</title>
</head>
<body>
	<%@ include file="header.jsp"%>
		<h2>Saisons de ${serie.nom}</h2>
		<div id="infoSerie">
			<h3>Infos sur la série</h3>
			<ul>
				<li>Nom : <c:out value="${serie.nom}" /></li>
				<li>Original : <c:out value="${serie.nomOriginal}" /></li>
				<li>Année de parution : <c:out value="${serie.anneeParution}" /></li>
				<li>Synopsys : <c:out value="${serie.synopsys}" /></li>
				<li>Statut : <c:out value="${serie.statut.libelle}" /></li>
				<li>Pays d'origine : <c:out value="${serie.paysOrigine.nom}" /></li>
			</ul>
		</div>
	<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
	<table>
		<c:forEach items="${liste}" var="item">
			<tr>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}'><c:out
							value="${item.serie.nom} : Saison n° ${item.numero}" /></a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}&action=modifier'>Modifier</a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}&action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>