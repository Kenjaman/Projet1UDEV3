<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Infos �pisode</title>
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
						<li class="list-group-item active">Episode num�ro <c:out value="${episode.numero}" /></li>
						<li class="list-group-item active">Titre original : <c:out value="${episode.titreOriginal }"  default="Non renseign�" /></li>
						<li class="list-group-item active">Dur�e : <c:out value="${episode.duree}" /> minutes </li>
						<li class="list-group-item active">Ann�e de r�alisation : <c:out value="${episode.dateRealisation}" default="Non renseign�" /></li>
						<li class="list-group-item active">Ann�e de parution : <c:out value="${episode.datePremiereDiffusion}"  default="Non renseign�" /></li>
						<li class="list-group-item active">R�sum� : <c:out value="${episode.resume}"  default="Non renseign�" /></li>
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
	</section> --%>