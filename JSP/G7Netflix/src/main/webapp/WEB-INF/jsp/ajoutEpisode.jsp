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
		<header>Fiche d'ajout d'un Épisode</header>
		<div>
			<label for="numeroEpisode">NumÃ©ro d'Ã©pisode: </label>
			<input id="numeroEpisode" name="numeroEpisode" type="number" min="1" step="1" value="<c:out value="${param['numeroEpisode']}" />"> 
		</div>
		<div>
			<label for="titreEpisode">Titre Ã©pisode :</label>
			<input type="text" name="titreEpisode" id="titreEpisode" placeholder="Saisir le titre de l'Ã©pisode" value="<c:out value="${param['titreEpisode']}"/>">
		</div>
		<div>
			<label for="titreEpisodeOriginal">Titre Ã©pisode Original:</label>
			<input type="text" name="titreEpisodeOriginal" id="titreEpisodeOriginal" placeholder="Saisir le titre Original de l'Ã©pisode" value="<c:out value="${param['titreEpisodeOriginal']}"/>">
		</div>
		<div>
			<label for="dureeEpisode">DurÃ©e Ã‰pisode en Minutes :</label>
			<input id="dureeEpisode" name="dureeEpisode" type="number" step="1" value="<c:out value="${param['dureeEpisode']}" />"> 
		</div>
		<div>
			<label for="resume">Résumé :</label>
			<input type="text" name="resume" id="resume" placeholder="Saisir le résumé de la saison" value="<c:out value="${param['resume']}"/>">
		</div>
		<div>
			<label for="dateRealisation">Date de réalisation :</label>
			<input type="text" name="dateRealisation" id="dateRealisation" placeholder="Saisir la date de réalisation" value="<c:out value="${param['dateRealisation']}"/>">
		</div>
		<div>
			<label for="datePremiereDiffusion">Date 1ère diffusion :</label>
			<input type="text" name="datePremiereDiffusion" id="datePremiereDiffusion" placeholder="Saisir la date de première diffusion" value="<c:out value="${param['datePremiereDiffusion']}"/>">
		</div>
		<div>
		  <select name="public">
              <option value="0">--</option>
              <c:forEach items="${publics}" var="varPublic">
                  <option ${param['public'] == varPublic ? "selected" : ""}><c:out value="${varPublic.libelle}"/></option>
              </c:forEach>
          </select>
       	</div>
       	<div>
		  <select name="statut">
              <option value="0">--</option>
              <c:forEach items="${statuts}" var="varStatut">
                  <option ${param['statut'] == varStatut ? "selected" : ""}><c:out value="${varStatut.libelle}"/></option>
              </c:forEach>
          </select>
       	</div>
		<div>
		  <select name="saison">
              <option value="0">--</option>
              <c:forEach items="${saisons}" var="varSaison">
                  <option ${param['saison'] == varSaison ? "selected" : ""}><c:out value="${varSaison.libelle}"/></option>
              </c:forEach>
          </select>
       	</div>
		<%@ include file="footer.jsp"%>
