<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Infos épisode</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section class="content">
		<h3>
			<c:out value="${episode.titre }" />
		</h3>
		<ul>
			<li>Episode numéro <c:out value="${episode.numero}" /></li>
			<li>Titre original : <c:out value="${episode.titreOriginal }"  default="Non renseigné" /></li>
			<li>Durée : <c:out value="${episode.duree}" /> minutes </li>
			<li>Année de réalisation : <c:out value="${episode.dateRealisation}" default="Non renseigné" /></li>
			<li>Année de parution : <c:out value="${episode.datePremiereDiffusion}"  default="Non renseigné" /></li>
			<li>Résumé : <c:out value="${episode.resume}"  default="Non renseigné" /></li>
			<li>Statut : <c:out value="${episode.statut.libelle}" /></li>
			<li>Public : <c:out value="${episode.publics.libelle}" /></li>
		</ul>

	<button onclick="history.go(-1)">Retour</button>
	</section>