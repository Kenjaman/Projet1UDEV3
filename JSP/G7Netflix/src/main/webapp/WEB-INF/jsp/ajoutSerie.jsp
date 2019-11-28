<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>Ajout d'une Série</title>
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
		<header>Fiche d'ajout d'une série</header>
		<div>
			<label for="nom">Nom :</label>
			<input type="text" name="nom" id="nom" placeholder="Saisir le nom de la série" value="<c:out value="${serie.nom}"/>">
		</div>
		<div>
			<label for="nomOriginal">Nom Original :</label>
			<input type="text" name="nomOriginal" id="nomOriginal" placeholder="Saisir le nom Original de la série" value="<c:out value="${serie.nomOriginal}"/>">
		</div>
		<div>
			<label for="anneeParution">Année de Parution : </label>
			<input id="anneeParution" name="anneeParution" type="number" min="1900" step="1" value="<c:out value="${serie.anneeParution}" />"> 
		</div>
		<div>
			<label for="synopsys">Synopsys :</label>
			<textarea rows='5' cols='50' name="synopsys" id="synopsys" placeholder="Saisir le Synopsys" ><c:out value="${serie.synopsys}"/></textarea>
		</div>
		<div>
		<label for='statut'>Statut </label>
		  <select name="statut">
              <option value="0">--</option>
              <c:forEach items="${statuts}" var="varStatut">
                  <option value='${varStatut.id}' ${serie.statut.id == varStatut.id ? "selected" : ""}>
                  	<c:out value="${varStatut.libelle}"/>
                  </option>
              </c:forEach>
          </select>
       	</div>
		<div>
		<label for='paysOrigine'>Pays d'origine </label>
		  <select name="paysOrigine">
              <option value="0">--</option>
              <c:forEach items="${paysOrigines}" var="varPaysOrigine">
                  <option value='${varPaysOrigine.id}' ${serie.paysOrigine.id == varPaysOrigine.id ? "selected" : ""}><c:out value="${varPaysOrigine.nom}"/></option>
              </c:forEach>
          </select>
       	</div>
		<div id="actions">
			<button type="submit">Valider</button>
			<button type="reset">Annuler</button>
			<a href="./accueil"><button>Retour</button></a>
		</div>
	</form>
</section>  
  </body>
</html>