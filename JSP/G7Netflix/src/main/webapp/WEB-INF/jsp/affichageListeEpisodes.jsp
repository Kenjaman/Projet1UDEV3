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
		<div class="col-lg-2">
		</div>
		<div class="col-lg-8">
			<c:if test="${saison.numero != null }">
				<br><h2>Episodes de la Saison ${saison.numero} de ${serie.nom}</h2><br>
				<div id="infoSaison">
					<h3>Infos sur la saison</h3>
					<ul>
						<li>Série : <c:out value="${serie.nom}" /></li>
						<li>Saison : <c:out value="${saison.numero}" /></li>
						<li>Résumé : <c:out value="${saison.resume}"
								default="Non renseigné" /></li>
						<li>Année de diffusion : <c:out
								value="${saison.anneeDiffusion}" default="Non renseigné" /></li>
						<li>Statut : <c:out value="${saison.statut.libelle}"
								default="Non renseigné" /></li>
					</ul>
				</div>
			</c:if>
				<br><a	href='./<c:out value="${entiteeTraiter}"/>?
					idserie=<c:out value="${serie.id}" default="0"/>
					&idsaison=<c:out value="${saison.id}" default="0"/>&action=ajouter'>
					Ajouter
				</a><br>
				<br><table>
					<c:forEach items="${liste}" var="item">
						<tr>
							<td><a href='./<c:out value="${entiteeTraiter}"/>?idserie=${serie.id}&idsaison=${saison.id}&idepisode=${item.id}'>
							<c:out value="${item.saison.serie.nom}"/> - Saison n° <c:out value="${item.saison.numero }"/> - Episode <c:out value="${item.numero }"/> :
							<c:out value="${item.titre}" /></a></td>
							<td><a
								href='./<c:out value="${entiteeTraiter}"/>?idserie=${serie.id}&idsaison=${saison.id}&idepisode=${item.id}&action=modifier'>Modifier</a></td>
							<td><a
								href='./<c:out value="${entiteeTraiter}"/>?idserie=${serie.id}&idsaison=${saison.id}&idepisode=${item.id}&action=supprimer'>Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="liens">
					<br><button onclick="history.go(-1)">Retour</button>
				</div>
		</div>
		<div class="col-lg-2">
		</div>		
	</div>
</div>
</body>
</html>