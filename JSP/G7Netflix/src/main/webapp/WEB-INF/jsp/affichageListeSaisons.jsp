<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage</title>
</head>
<body>
	<div id="logo">
		<img alt="logo" src="<c:url value='/images/KenFlixIcon.svg'/>" />
	</div>
	<h1>Saisons de ${serie.nom}</h1>
	<div id="infoSerie">
		<h2>Infos sur la série</h2>
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
					href='./<c:out value="${entiteeTraiter}"/>?idSaison=${item.id}'><c:out
							value="${item.numero}" /></a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idSaison=${item.id}&action=modifier'>Modifier</a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?idSaison=${item.id}&action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>