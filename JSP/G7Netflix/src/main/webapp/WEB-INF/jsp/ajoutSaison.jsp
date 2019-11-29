<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>Ajout d'une Saison</title>
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
		<header>Fiche d'ajout/modification d'une saison</header>
		<h3><c:out value="Id saison : ${saison.id}"/></h3>
		<div>
		<input type='hidden' name="idSerie" value='<c:out value="${serie.id}"/>'/>
		<input type='hidden' name="idSaison" value='<c:out value="${saison.id}"/>'/>
		  <%-- <select name="nomSerie">
              <option value="0">--</option>
              <c:forEach items="${nomsSeries}" var="varnomSerie">
                  <option ${param['nomSerie'] == varnomSerie ? "selected" : ""}><c:out value="${varnomSerie.nom}"/></option>
              </c:forEach>
          </select> --%>
       	</div>
       	<div>
			<label for="numeroSaison">Numéro de Saison: </label>
			<input id="numeroSaison" name="numeroSaison" type="number" min="1" step="1" value="<c:out value="${saison.numero}" />"> 
		</div>
		<div>
			<label for="anneeDiffusion">Année de diffusion : </label>
			<input id="anneeDiffusion" name="anneeDiffusion" type="number" min="1900" step="1" value="<c:out value="${saison.anneeDiffusion}" />"> 
		</div>
		<div>
			<label for="resume">Résumé :</label>
			<input type="text" name="resume" id="resume" placeholder="Saisir le résumé de la saison" value="<c:out value="${saison.resume}"/>">
		</div>
		<div>
		  <select name="statutSaison">
              <option value="0">--</option>
              <c:forEach items="${statutSaisons}" var="varStatutSaison">
                  <option ${saison.statut.id == varStatutSaison.id ? "selected" : ""}><c:out value="${varStatutSaison}"/></option>
              </c:forEach>
          </select>
       	</div>
		<div>
		<div id="actions">
			<button type="submit">Valider</button>
			<button type="reset">Annuler</button>
		</div>
	</form>
</section>  
  </body>
</html>