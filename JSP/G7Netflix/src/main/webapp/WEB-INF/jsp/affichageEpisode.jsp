<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Infos �pisode</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section class="content">
		<h3>
			<c:out value="${episode.titre }" />
		</h3>
		<ul>
			<li>Episode num�ro <c:out value="${episode.numero}" /></li>
			<li>Titre original : <c:out value="${episode.titreOriginal }"  default="Non renseign�" /></li>
			<li>Dur�e : <c:out value="${episode.duree}" /> minutes </li>
			<li>Ann�e de r�alisation : <c:out value="${episode.dateRealisation}" default="Non renseign�" /></li>
			<li>Ann�e de parution : <c:out value="${episode.datePremiereDiffusion}"  default="Non renseign�" /></li>
			<li>R�sum� : <c:out value="${episode.resume}"  default="Non renseign�" /></li>
			<li>Statut : <c:out value="${episode.statut.libelle}" /></li>
			<li>Public : <c:out value="${episode.publics.libelle}" /></li>
		</ul>

	<button onclick="history.go(-1)">Retour</button>
	</section>