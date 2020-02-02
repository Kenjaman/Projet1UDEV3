<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div class ="row">
			<div class="col-lg-3">
			</div>
			<div class="col-lg-6">
			<c:if test="${serie != null}">
				<div id="titreAffichageListeSaisons">
					<h2>Saisons de ${serie.nom}</h2>
				</div>
				<div id="infoSerie">
					<h3>Infos sur la série</h3>
					<ul class="list-group">
						<li>Nom : <c:out value="${serie.nom}" /></li>
						<li>Original : <c:out value="${serie.nomOriginal}"
								default="Non renseigné" /></li>
						<li>Année de parution : <c:out value="${serie.anneeParution}"
								default="Non renseigné" /></li>
						<li>Synopsys : <c:out value="${serie.synopsys}"
								default="Non renseigné" /></li>
						<li>Statut : <c:out value="${serie.statut.libelle}" /></li>
						<li>Pays d'origine : <c:out value="${serie.paysOrigine.nom}" /></li>
					</ul>
				</div>
				<a class='btn btn-primary' href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a><br>
			</c:if>
				<br><table>
					<c:forEach items="${liste}" var="item">
						<tr>
							<td><a
								href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}'><c:out
										value="${item.serie.nom} : Saison n° ${item.numero}" /></a></td>
							<td><a
								href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}&action=modifier'>Modifier</a></td>
							<td><a
								href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.serie.id}&idsaison=${item.id}&action=supprimer'>Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="liens">
					<br><button onclick="history.go(-1)">Retour</button>
				</div>
			</div>
			<div class="col-lg-3">
			</div>		
		</div>
	</div>
</body>
</html>