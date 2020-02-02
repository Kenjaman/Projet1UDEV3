<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Infos épisode</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="container">
			<div class ="row">
				<div class="col-lg-2">
				</div>
				<div class="col-lg-8">
					<div id="titreAffichageEpisode">
						<h3><c:out value="${episode.titre }" /></h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item active">Episode numéro <c:out value="${episode.numero}" /></li>
						<li class="list-group-item active">Titre original : <c:out value="${episode.titreOriginal }"  default="Non renseigné" /></li>
						<li class="list-group-item active">Durée : <c:out value="${episode.duree}" /> minutes </li>
						<li class="list-group-item active">Année de réalisation : <c:out value="${episode.dateRealisation}" default="Non renseigné" /></li>
						<li class="list-group-item active">Année de parution : <c:out value="${episode.datePremiereDiffusion}"  default="Non renseigné" /></li>
						<li class="list-group-item active">Résumé : <c:out value="${episode.resume}"  default="Non renseigné" /></li>
						<li class="list-group-item active">Statut : <c:out value="${episode.statut.libelle}" /></li>
						<li class="list-group-item active">Public : <c:out value="${episode.publics.libelle}" /></li>
					</ul>
					<button onclick="history.go(-1)">Retour</button>
				</div>
				<div class="col-lg-2">
				</div>		
			</div>
		</div>
		
	<%--<section class="content">
		<h3>
			<c:out value="${episode.titre }" />
		</h3>
		<ul class="list-group">
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
	</section> --%>