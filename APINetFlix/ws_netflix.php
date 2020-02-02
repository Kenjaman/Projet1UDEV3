<?php
	 /**
	 * Auteur : Michel GILLET
	 * Date : 14/11/2019
	 * Version 1.0
	 *
	 * Fichier d'accès et de description des ressources disponibles sur les Web Services
	 */

	header('Content-Type: text/html; charset=utf-8');
	header('Access-Control-Allow-Origin: *');

?>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>MyNetflix Web Services Index</title>
		<meta name="description" content="Web Services">
		<meta name="author" content="Michel GILLET">
		<meta name="viewport" content="width=device-width; initial-scale=1.0">
		<style type="text/css">
			html{
				font-family: Verdana, Tahoma, sans-serif;
				font-size: 0.8em;
			}

			section.syntax{
				padding-left: 20px;
			}

			section.syntax p{
				padding: 0;
				margin: 0;
			}
		</style>
	</head>

	<body>
		<div>
			<header>
				<h1>Web Services MyNetflix</h1>
			</header>
			<div>
				<div>
					<header><strong>Récupération d'une liste de données</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data=</b> : series | saisons | personnes | episodes | fonctions
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=<i>valeur</i></code></p>
					</section>
					<button onclick="getSeries()">getSeries()</button>
					<button onclick="getSaisons()">getSaisons()</button>
					<button onclick="getEpisodes()">getEpisodes()</button>
					<button onclick="getPersonnes()">getPersonnes()</button>
					<button onclick="getFonctions()">getFonctions()</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des saisons d'une série</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = saisons<br />
							<b>idserie</b> : id de la série demandée
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=saisons&idserie=<i>valeur</i></code></p>
					</section>
					<input type="number" id="idseriesaison" value="2"/><button onclick="getSaisons(idseriesaison)">Envoyer</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des épisodes d'une saison</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = episodes<br />
							<b>idsaison</b> : id de la saison demandée
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=episodes&idsaison=<i>valeur</i></code></p>
					</section>
					<input type="number" id="idsaisonepisode" value="1"/><button onclick="getEpisodes(idsaisonepisode)">Envoyer</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des personnes</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = personnes
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=personnes</code></p>
					</section>
					<button onclick="getPersonnes()">Envoyer</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des acteurs</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = personnes<br />
							<b>idfonction</b> = id de la fonction Acteurs
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=personnes&idfonction=<i>valeur</i></code></p>
					</section>
					<button onclick="getActeurs()">Envoyer</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des réalisateurs</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = personnes<br />
							<b>idfonction</b> = id de la fonction Réalisateurs
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=personnes&idfonction=<i>valeur</i></code></p>
					</section>
					<button onclick="getRealisateurs()">Envoyer</button>
				</div>
				<hr />
				<div>
					<header><strong>Récupération des acteurs d'un épisode</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = personnes<br />
							<b>fonction</b> = acteurs<br />
							<b>idepisode</b> : id de l'épisode recherché
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=personnes&idfonction=<i>valeur</i>&idepisode=<i>valeur</i></code></p>
					</section>
					<input type="number" id="idepisodeacteurs" value="14"/><button onclick="getActeursEpisode(idepisodeacteurs)">Envoyer</button>
				</div>
                <hr />
				<div>
					<header><strong>Récupération des acteurs d'une série</strong></header>
					<section class="syntax">
						<p>Fichier : <b>api.php</b></p>
						<p>Params :
							<b>data</b> = personnes<br />
							<b>fonction</b> = acteurs<br />
							<b>idserie</b> : id de la série recherchée
						</p>
						<p>Return : JSON</p>
						<p>Exemple : <code>api.php?data=personnes&idfonction=<i>valeur</i>&idserie=<i>valeur</i></code></p>
					</section>
					<input type="number" id="idserieacteurs" value="1"/><button onclick="getActeursSerie(idserieacteurs)">Envoyer</button>
				</div>
			</div>
			<footer>
				<p>
					&copy; Copyright  by Michel GILLET
				</p>
			</footer>
		</div>
	</body>

	<script type="text/javascript">
		function getSeries(){
			window.open('api.php?data=series');
		}

	function getFonctions(){
			window.open('api.php?data=fonctions');
		}

		function getSaisons(idserie = null){
			if (idserie == null || idserie.value.trim() === ''){
				window.open('api.php?data=saisons');
			}else{
				window.open('api.php?data=saisons&idserie=' + idserie.value);
			}
		}

		function getEpisodes(idsaison = null){
			if (idsaison == null || idsaison.value.trim() === ''){
				window.open('api.php?data=episodes');
			}else{
				window.open('api.php?data=episodes&idsaison=' + idsaison.value);
			}
		}

		function getPersonnes(){
			window.open('api.php?data=personnes');
		}

        function getActeurs(){
            window.open('api.php?data=personnes&idfonction=2');
		}

		function getRealisateurs(){
            window.open('api.php?data=personnes&idfonction=4');
		}

		function getActeursEpisode(idepisode){
			window.open('api.php?data=personnes&idfonction=2&idepisode=' + idepisode.value);
		}
		function getActeursSerie(idserie){
			window.open('api.php?data=personnes&idfonction=2&idserie=' + idserie.value);
		}
	</script>
</html>
