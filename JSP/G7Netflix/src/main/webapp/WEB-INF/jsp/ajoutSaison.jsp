<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>Ajout Saison</title>
    <style type="text/css">
    	form > div {
    		padding: .5em;
    	}
    	
    	div > label:first-child {
    		display: inline-block;
			min-width: 10em;
		}
    </style>  </head>
  <body>

	<form method="post" accept-charset="utf-8">
		<div>
			<label for="nom">Nom Serie : </label>
			<select id="statut" name="statut">
				<option value="0">-- Choisissez --</option>
				<c:forEach var="saison" items="${listSaison}">
					<option value="${saison.id}">
						<c:out value="${saison.nom}"/>
					</option>
				</c:forEach>
			</select> 
		</div>
		<div>
			<label for="table">Numéro de table : </label>
			<input id="table" name="table" type="text" value="<c:out value="${param['table']}" />"> 
		</div>
		<div>
			<button type="submit">Commander</button>
		</div>
	</form>

	<div>
	  	<a href="<c:url value="/"/>">Retour à l'accueil</a>  
	</div>
  
  </body>
</html>