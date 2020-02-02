<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'une Saison</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<form action="" method="post">
					<br>
					<header id="headerAjoutModifSaison">Fiche
						d'ajout/modification d'une saison</header>
					<br>
					<h3 id="titreAjoutModifSaison">
						<c:out value="Serie : ${serie.nom}" />
					</h3>
					<div>
						<c:choose>
							<c:when test="${serie != null }">
								<input type="hidden" name="idSerie"
									value="<c:out value="${serie.id}"/>" />
								<input type="hidden" name="idSaison"
									value="<c:out value="${saison.id}"/>"/>
							</c:when>
							<c:otherwise>
								<label for="idSerie">Série : <select name="idSerie">
										<option value="0">--</option>
										<c:forEach items="${series}" var="varnomSerie">
											<option value="${varnomSerie.id }"
												${param['idSerie'] == varnomSerie.id ? "selected" : ""}><c:out
													value="${varnomSerie.nom}" /></option>
										</c:forEach>
								</select>
								</label>
							</c:otherwise>
						</c:choose>
					</div>
					<div>
						<label for="numeroSaison">Numéro de Saison: </label> <input
							id="numeroSaison" name="numeroSaison" type="number" min="1"
							step="1" value="<c:out value="${saison.numero}" default="1"  />">
					</div>
					<div>
						<label for="anneeDiffusion">Année de diffusion : </label> <input
							id="anneeDiffusion" name="anneeDiffusion" type="number"
							min="1900" step="1"
							value="<c:out value="${saison.anneeDiffusion}" default="2000" />">
					</div>
					<br>
					<div>
						<label for="resume">Résumé :
						<textarea rows='5' cols='50' name="resume" id="resume"
							placeholder="Saisir le résumé de la saison"><c:out
								value="${saison.resume}" /></textarea></label>
					</div>
					<div>
						<label for='statutSaison'>Statut de la saison <select
							name="statutSaison">
								<option value="0">--</option>
								<c:forEach items="${statuts}" var="varStatutSaison">
								<option value='${varStatutSaison.id}'
									${saison.statut.id == varStatutSaison.id ? "selected" : ""}>
									<c:out value='${varStatutSaison.libelle}' />
								</option>
								</c:forEach>
						</select>
						</label>
					</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>