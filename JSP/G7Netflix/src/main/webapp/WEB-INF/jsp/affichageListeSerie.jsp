<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage Général</title>
</head>
<body>
<%@ include file="header.jsp"%>

	<div class="container">
		<div class ="row">
			<div class="col-lg-2">
			</div>
			<div class="col-lg-8">
				<div class='content'>
					<br><h2 id="titreAffichSerie">LES SERIES</h2><br>
					<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a><br>
					<br><table>
						<c:forEach items="${liste}" var="item">
							<tr>
								<td class='name'><a
									href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}'><c:out
											value="${item.nom}" /></a></td>
								<td><a
									href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=modifier'>Modifier</a></td>
								<td><a
									href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=supprimer'>Delete</a></td>
							</tr>
						</c:forEach>
					</table>
					<div class="liens">
						<br><button onclick="history.go(-1)">Retour</button>
			
					</div>
				</div>
			</div>
			<div class="col-lg-2">
			</div>		
		</div>
	</div>
	
<%-- 	<div class='content'>
		<br><h2>LES SERIES</h2><br>
		<a href='./<c:out value="${entiteeTraiter}"/>?action=ajouter'>Ajouter</a>
		<table>
			<c:forEach items="${liste}" var="item">
				<tr>
					<td class='name'><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}'><c:out
								value="${item.nom}" /></a></td>
					<td><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=modifier'>Modifier</a></td>
					<td><a
						href='./<c:out value="${entiteeTraiter}"/>?idserie=${item.id}&action=supprimer'>Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="liens">
			<button onclick="history.go(-1)">Retour</button>

		</div>
	</div> --%>
</body>
</html>