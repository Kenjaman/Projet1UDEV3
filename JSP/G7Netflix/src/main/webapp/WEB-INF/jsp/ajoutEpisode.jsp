<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un �pisode</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<form action="" method="post">
					<br>
					<header>Fiche d'ajout d'un �pisode</header>
					<br>
					<div>
						<label for="numeroEpisode">Num�ro d'�pisode: </label> <input
							id="numeroEpisode" name="numeroEpisode" type="number"
							min="${dernierEpisode}" step="1"
							value="<c:out value="${dernierEpisode}" default="1"/>">
					</div>
					<div>
						<label for="titreEpisode">Titre �pisode :</label> <input
							type="text" name="titreEpisode" id="titreEpisode"
							placeholder="Saisir le titre de l'�pisode"
							value="<c:out value="${episode.titre }"/>">
					</div>
					<div>
						<label for="titreEpisodeOriginal">Titre �pisode Original:</label>
						<input type="text" name="titreEpisodeOriginal"
							id="titreEpisodeOriginal"
							placeholder="Saisir le titre Original de l'�pisode"
							value="<c:out value="${episode.titreOriginal }"/>">
					</div>
					<div>
						<label for="dureeEpisode">Dur�e �pisode en Minutes :</label> <input
							id="dureeEpisode" name="dureeEpisode" type="number" min="0"
							step="1" value="<c:out value="${episode.duree }" />">
					</div>
					<div>
						<label for="resume">R�sum� :</label> <input type="text"
							name="resume" id="resume"
							placeholder="Saisir le r�sum� de la saison"
							value="<c:out value="${episode.resume }"/>">
					</div>
					<div>
						<label for="dateRealisation">Date de r�alisation :</label> <input
							type="date" name="dateRealisation" id="dateRealisation"
							placeholder="Saisir la date de r�alisation"
							value="<c:out value="${episode.dateRealisation }" default="0"/>">
					</div>
					<div>
						<label for="datePremiereDiffusion">Date 1�re diffusion :</label> <input
							type="date" name="datePremiereDiffusion"
							id="datePremiereDiffusion"
							placeholder="Saisir la date de premi�re diffusion"
							value="<c:out value="${episode.datePremiereDiffusion }"/>">
					</div>
					<div>
						<label for="public">Public : <select name="public">
								<option value="0">--</option>
								<c:forEach items="${publics}" var="varPublic">
									<option ${episode.publics.id == varPublic.id ? "selected" : ""}
										value="<c:out value="${varPublic.id}"/>" >
										<c:out value="${varPublic.libelle}" />
									</option>
								</c:forEach>
						</select>
						</label>
					</div>
					<div>
						<label for='statut'>Statut <select name="statutEpisode">
								<option value="0">--</option>
								<c:forEach items="${statuts}" var="varStatut">
									<option ${episode.statut.id == varStatut.id ? "selected" : ""}
										value="<c:out
											value="${varStatut.id}"/>">
										<c:out value="${varStatut.libelle}" />
									</option>
								</c:forEach>
						</select>
						</label>
					</div>
					<div>
						<%-- <select name="saison">
		              <option value="0">--</option>
		              <c:forEach items="${saisons}" var="varSaison">
		                  <option ${param['saison'] == varSaison ? "selected" : ""}><c:out value="${varSaison.numero}"/></option>
		              </c:forEach>
		          </select> --%>
					</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>