function getListeSeries(table){
	var xmlHttpSeries = getAjaxRequestObject();
	xmlHttpSeries.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			var text= this.responseText;
			var lesSeries = JSON.parse(text);
			lesSeries.sort(function(a, b){
				if (a.nom < b.nom){
					return -1;
				}else if (a.nom > b.nom){
					return 1;
				}else{
					return 0;
				}
			});

			for(var i=0;i<lesSeries.length;i++){
				var row = table.insertRow(i);
				var cellnom = row.insertCell(0);
				var cellannee = row.insertCell(1);
				var cellSaisons = row.insertCell(2);
				cellnom.innerText = lesSeries[i].nom;
				cellannee.innerText = lesSeries[i].anneeparution;
				getNbSaisons(lesSeries[i].id, cellSaisons);
				row.setAttribute("tag", lesSeries[i].id);
				cellnom.style.textAlign = "left";
				cellnom.style.paddingLeft = "10px";

				row.onclick = function(){
					getSaisons(this.getAttribute("tag"));
				}
			}
		}
	};
	xmlHttpSeries.open("GET", "http://localhost/apinetflix/api.php?data=series");
	xmlHttpSeries.send();
}

function getSaisons(id){
	var xmlHttpSaisons = getAjaxRequestObject();
	xmlHttpSaisons.onreadystatechange = function(){

		if (this.readyState === 4 && this.status === 200){
			try{
				
				if (document.contains(document.getElementById("tablesaisons"))){
					document.getElementById("tablesaisons").remove();
				}
				if (document.contains(document.getElementById("tableepisodes"))){
					document.getElementById("tableepisodes").remove();
				}
				
				console.log(JSON.parse(this.responseText));
				var text = this.responseText;
				var lesSaisons = JSON.parse(text);
				
				var tableSaisons = document.createElement("table");
				tableSaisons.setAttribute("id", "tablesaisons");
				var header = tableSaisons.createTHead();
				var tbody = tableSaisons.appendChild(document.createElement("tbody"))
				var hrow = header.insertRow(0);
				var thnom = hrow.insertCell(0);
				var thannee = hrow.insertCell(1);
				var thnumero = hrow.insertCell(2);
				thnom.outerHTML = "<th>Nom Série</th>";
				thannee.outerHTML = "<th>Année</th>";
				thnumero.outerHTML = "<th>Numéro</th>";

				for(var i=0; i < lesSaisons.length; i++){
					var row = tbody.insertRow(i);
					var cellnom = row.insertCell(0);
					var cellannee = row.insertCell(1);
					var cellnumero = row.insertCell(2);
					
					cellnom.innerText = lesSaisons[i].nomSerie;
					cellannee.innerText = lesSaisons[i].annee_diffusion;
					cellnumero.innerText = lesSaisons[i].numero;
					row.setAttribute("tag", lesSaisons[i].id);
					cellnom.style.textAlign = "left";
					cellnom.style.paddingLeft = "10px";
					
					row.onclick = function(){
						getEpisodes(this.getAttribute("tag"));
					}
				}
				
				document.body.appendChild(tableSaisons);
				
			}
			catch (e){
				console.log("Pas de saisons");
				var tables = document.getElementById("tables");
				var errortext = document.createElement("p");
				errortext.innerText = "Pas de saisons";
				errortext.setAttribute("id", "tableepisodes");
				errortext.style.color = "red";
				tables.appendChild(errortext);
			}
		}
	};
	xmlHttpSaisons.open("GET", "http://localhost/apinetflix/api.php?data=saisons&idserie=" + id);
	xmlHttpSaisons.send();
}

function getEpisodes(id){
	var xmlHttpEpisodes = getAjaxRequestObject();
	xmlHttpEpisodes.onreadystatechange = function(){

		if (this.readyState === 4 && this.status === 200){
			try{
				
				if (document.contains(document.getElementById("tableepisodes"))){
					document.getElementById("tableepisodes").remove();
				}
				
				console.log(JSON.parse(this.responseText));
				var text = this.responseText;
				var lesEpisodes = JSON.parse(text);
				
				var tableEpisodes = document.createElement("table");
				tableEpisodes.setAttribute("id", "tableepisodes");
				var header = tableEpisodes.createTHead();
				var tbody = tableEpisodes.appendChild(document.createElement("tbody"))
				var hrow = header.insertRow(0);
				var thnumsaison = hrow.insertCell(0);
				var thnumero = hrow.insertCell(1);
				var thtitre = hrow.insertCell(2);
				var thtitreoriginal = hrow.insertCell(3);
				thnumsaison.outerHTML = "<th>Numéro Saison</th>";
				thnumero.outerHTML = "<th>Numéro</th>";
				thtitre.outerHTML = "<th>Titre</th>";
				thtitreoriginal.outerHTML = "<th>Titre Original</th>";

				for(var i=0; i < lesEpisodes.length; i++){
					var row = tbody.insertRow(i);
					var cellnumsaison = row.insertCell(0);
					var cellnumero = row.insertCell(1);
					var celltitre = row.insertCell(2);
					var celltitreoriginal = row.insertCell(3);
					
					cellnumsaison.innerText = lesEpisodes[i].Saison;
					cellnumero.innerText = lesEpisodes[i].numero;
					celltitre.innerText = lesEpisodes[i].titre;
					celltitreoriginal.innerText = lesEpisodes[i].titreoriginal;
					row.setAttribute("tag", lesEpisodes[i].id);
				}
				
				document.body.appendChild(tableEpisodes);
				
			}
			catch (e){
				console.log("Pas d'épisodes");
				var tables = document.getElementById("tables");
				var errortext = document.createElement("p");
				errortext.innerText = "Pas d'épisodes";
				errortext.setAttribute("id", "tableepisodes");
				errortext.style.color = "red";
				tables.appendChild(errortext);
			}
		}
	};
	xmlHttpEpisodes.open("GET", "http://localhost/apinetflix/api.php?data=episodes&idsaison=" + id);
	xmlHttpEpisodes.send();
}

function getNbSaisons(id, cellule){
	var nbSaisons = 0;
	var xmlHttpSaisons = getAjaxRequestObject();
	xmlHttpSaisons.onreadystatechange = function(){
		if (this.readyState === 4 && this.status === 200){
			try{
				nbSaisons = JSON.parse(this.responseText).length;
			}catch (e){
				nbSaisons = 0;
			}
			cellule.innerText = nbSaisons;
		}
	};
	xmlHttpSaisons.open("GET", "http://localhost/apinetflix/api.php?data=saisons&idserie=" + id);
	xmlHttpSaisons.send();
}