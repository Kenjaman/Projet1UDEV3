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
	<h1>Episodes de la Saison ${saison.numero} de ${serie.nom}</h1>
	<div id="infoSerie">
		<h2>Infos sur la saison</h2>
		<ul>

			<li>Série : <c:out value="${serie.nom}" /></li>
			<li>Numéro : <c:out value="${saison.numero}" /></li>
			<li>Résumé : <c:out value="${saison.resume}" /></li>
			<li>Année de diffusion : <c:out value="${saison.anneediffusion}" /></li>
			<li>Statut : <c:out value="${saison.statut.libelle}" /></li>
		</ul>
	</div>
	<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
	<table>
		<c:forEach items="${liste}" var="item">
			<tr>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}'><c:out
							value="${item.numero}" /></a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=modifier'>Modifier</a></td>
				<td><a
					href='./<c:out value="${entiteeTraiter}"/>?id=${item.id}&action=supprimer'>Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>