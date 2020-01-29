<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>Ajout d'un Épisode</title>
  </head>
  <body>
  	<%@ include file="header.jsp"%>
<section id="content">
	<form action="" method="post">
		<header>Fiche d'ajout d'un �pisode</header>
		<div>
			<label for="titreEpisode">Titre �pisode :</label>
			<input type="text" name="titreEpisode" id="titreEpisode" placeholder="Saisir le titre de l'�pisode" value="<c:out value="${param['titreEpisode']}"/>">
		</div>
		<div>
			<label for="numeroEpisode">Num�ro d'�pisode: </label>
			<input id="numeroEpisode" name="numeroEpisode" type="number" min="1" step="1" value="<c:out value="${param['numeroEpisode']}" />"> 
		</div>
		<div>
			<label for="titreEpisodeOriginal">Titre �pisode Original:</label>
			<input type="text" name="titreEpisodeOriginal" id="titreEpisodeOriginal" placeholder="Saisir le titre Original de l'�pisode" value="<c:out value="${param['titreEpisodeOriginal']}"/>">
		</div>
		<div>
			<label for="dureeEpisode">Dur�e �pisode :</label>
		</div>
		<div>
			<label for="dureeEpisodeHeure">Heures: </label>
			<input id="dureeEpisodeHeure" name="dureeEpisodeHeure" type="number" step="1" value="<c:out value="${param['dureeEpisodeHeure']}" />"> 
		</div>
		<div>
			<label for="dureeEpisodeMinute">Minutes: </label>
			<input id="dureeEpisodeMinute" name="dureeEpisodeMinute" type="number" step="1" value="<c:out value="${param['dureeEpisodeMinute']}" />"> 
		</div>
		<div>
			<label for="dureeEpisodeSeconde">Secondes: </label>
			<input id="dureeEpisodeSeconde" name="dureeEpisodeSeconde" type="number" step="1" value="<c:out value="${param['dureeEpisodeSeconde']}" />"> 
		</div>
		<div>
			<label for="resume">R�sum� :</label>
			<input type="text" name="resume" id="resume" placeholder="Saisir le r�sum� de la saison" value="<c:out value="${param['resume']}"/>">
		</div>
		<div>
			<label for="dateRealisation">Date de r�alisation :</label>
			<input type="text" name="dateRealisation" id="dateRealisation" placeholder="Saisir la date de r�alisation" value="<c:out value="${param['dateRealisation']}"/>">
		</div>
		<div>
			<label for="datePremiereDiffusion">Date 1�re diffusion :</label>
			<input type="text" name="datePremiereDiffusion" id="datePremiereDiffusion" placeholder="Saisir la date de premi�re diffusion" value="<c:out value="${param['datePremiereDiffusion']}"/>">
		</div>
		<div>
		  <select name="public">
              <option value="0">--</option>
              <c:forEach items="${publics}" var="varPublic">
                  <option ${param['"public"'] == varPublic ? "selected" : ""}><c:out value="${varPublic}"/></option>
              </c:forEach>
          </select>
       	</div>
       	<div>
		  <select name="statut">
              <option value="0">--</option>
              <c:forEach items="${statuts}" var="varStatut">
                  <option ${param['"statut"'] == varStatut ? "selected" : ""}><c:out value="${varStatut}"/></option>
              </c:forEach>
          </select>
       	</div>
		<div>
		  <select name="saison">
              <option value="0">--</option>
              <c:forEach items="${saisons}" var="varSaison">
                  <option ${param['saison'] == varSaison ? "selected" : ""}><c:out value="${varSaison}"/></option>
              </c:forEach>
          </select>
       	</div>
		<%@ include file="footer.jsp"%>
