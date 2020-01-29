<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>Ajout d'un Épisode</title>
  </head>
  <body>
  	<section id="topheader">
	<header>
		<div><img src="includes/images/logo.png" alt="logo" /></div>
		<div><p id="titre">MyNetflix</p></div>
	</header>
	<nav>
		<ul>
			<li><a href="index.html">Accueil</a></li>
			<li><a href="liste.html">Liste</a></li>
			<li><a href="fiche.html">Fiche</a></li>
		</ul>
	</nav>
</section>
<section id="content">
	<form action="" method="post">
		<header>Fiche d'ajout d'un Épisode</header>
		<div>
			<label for="numeroEpisode">Numéro d'épisode: </label>
			<input id="numeroEpisode" name="numeroEpisode" type="number" min="1" step="1" value="<c:out value="${param['numeroEpisode']}" />"> 
		</div>
		<div>
			<label for="titreEpisode">Titre épisode :</label>
			<input type="text" name="titreEpisode" id="titreEpisode" placeholder="Saisir le titre de l'épisode" value="<c:out value="${param['titreEpisode']}"/>">
		</div>
		<div>
			<label for="titreEpisodeOriginal">Titre épisode Original:</label>
			<input type="text" name="titreEpisodeOriginal" id="titreEpisodeOriginal" placeholder="Saisir le titre Original de l'épisode" value="<c:out value="${param['titreEpisodeOriginal']}"/>">
		</div>
		<div>
			<label for="dureeEpisode">Durée Épisode en Minutes :</label>
			<input id="dureeEpisode" name="dureeEpisode" type="number" step="1" value="<c:out value="${param['dureeEpisode']}" />"> 
		</div>
		<div>
			<label for="resume">Résumé :</label>
			<input type="text" name="resume" id="resume" placeholder="Saisir le résumé de la saison" value="<c:out value="${param['resume']}"/>">
		</div>
		<div>
			<label for="dateRealisation">Date de réalisation :</label>
			<input type="date" name="dateRealisation" id="dateRealisation" placeholder="Saisir la date de réalisation" value="<c:out value="${param['dateRealisation']}"/>">
		</div>
		<div>
			<label for="datePremiereDiffusion">Date 1ère diffusion :</label>
			<input type="date" name="datePremiereDiffusion" id="datePremiereDiffusion" placeholder="Saisir la date de première diffusion" value="<c:out value="${param['datePremiereDiffusion']}"/>">
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
		<div id="actions">
			<button type="submit">Valider</button>
			<button type="reset">Annuler</button>
		</div>
	</form>
</section>  
  </body>
</html>