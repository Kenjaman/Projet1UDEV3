<?php
header('Access-Control-Allow-Origin: *');
require_once ('includes/dbConn.php');
if (!empty($_REQUEST)) {
	$data = isset($_REQUEST['data'])?$_REQUEST['data']:0;

	$SQLQuery = '';

	switch ($data){
		case 'series':{
			$SQLQuery = "SELECT * FROM serie";
			break;
		}
		case 'episodes':{
			$SQLQuery = 'SELECT serie.nom as Serie, saison.numero as Saison, episode.*  ';
			$SQLQuery .= 'FROM episode INNER JOIN saison ON saison.id = episode.idsaison ';
			$SQLQuery .= 'INNER JOIN serie ON saison.idserie = serie.id ';
			if (isset($_REQUEST['idsaison']) AND intval($_REQUEST['idsaison']) != 0){
				$SQLQuery .= 'WHERE saison.id = :idsaison';
				$SQLStmt = $bdd->prepare($SQLQuery);
				$SQLStmt->bindValue(':idsaison', intval($_REQUEST['idsaison']));
			}
			break;
		}
		case 'saisons':{
			$SQLQuery = 'SELECT serie.id as idserie, serie.nom as nomSerie, saison.* ';
			$SQLQuery .= 'FROM saison INNER JOIN serie ON serie.id = saison.idserie ';
			if (isset($_REQUEST['idserie']) AND intval($_REQUEST['idserie']) != 0){
				$SQLQuery .= 'WHERE serie.id = :idserie ';
				$SQLStmt = $bdd->prepare($SQLQuery);
				$SQLStmt->bindValue(':idserie', intval($_REQUEST['idserie']));
			}
			break;
		}
		case 'personnes':{
			$SQLQuery = 'SELECT * ';
			$SQLQuery .= 'FROM personne ';
			if (isset($_REQUEST['idfonction']) AND intval($_REQUEST['idfonction']) != 0 AND !isset($_REQUEST['idepisode']) AND !isset($_REQUEST['idserie'])){
				$SQLQuery .= 'INNER JOIN pers_fonction ON personne.id = pers_fonction.idpersonne ';
				$SQLQuery .= 'WHERE pers_fonction.idfonction = :idfonction';
				$SQLStmt = $bdd->prepare($SQLQuery);
				$SQLStmt->bindValue(':idfonction', intval($_REQUEST['idfonction']));
			}elseif (isset($_REQUEST['idfonction']) AND intval($_REQUEST['idfonction']) != 0 AND isset($_REQUEST['idepisode']) AND intval($_REQUEST['idepisode']) != 0 AND !isset($_REQUEST['idserie'])){
				$SQLQuery = 'SELECT DISTINCT personne.*, participer.role_alias as Alias ';
				$SQLQuery .= 'FROM personne INNER JOIN participer ON personne.id = participer.idpersonne ';
				$SQLQuery .= 'WHERE participer.idepisode = :idepisode ';
				$SQLQuery .= 'AND participer.idfonction = :idfonction';
				$SQLStmt = $bdd->prepare($SQLQuery);
				$SQLStmt->bindValue(':idepisode', intval($_REQUEST['idepisode']));
				$SQLStmt->bindValue(':idfonction', intval($_REQUEST['idfonction']));
			}elseif (isset($_REQUEST['idfonction']) AND intval($_REQUEST['idfonction']) != 0 AND isset($_REQUEST['idserie']) AND intval($_REQUEST['idserie']) != 0 AND !isset($_REQUEST['idepisode'])){
				$SQLQuery = 'SELECT DISTINCT personne.*, participer.role_alias as Alias ';
				$SQLQuery .= 'FROM personne INNER JOIN participer ON personne.id = participer.idpersonne ';
				$SQLQuery .= 'INNER JOIN episode ON participer.idepisode = episode.id ';
				$SQLQuery .= 'INNER JOIN saison ON episode.idsaison = saison.id ';
				$SQLQuery .= 'WHERE saison.idserie = :idserie ';
				$SQLQuery .= 'AND participer.idfonction = :idfonction';
				$SQLStmt = $bdd->prepare($SQLQuery);
				$SQLStmt->bindValue(':idserie', intval($_REQUEST['idserie']));
				$SQLStmt->bindValue(':idfonction', intval($_REQUEST['idfonction']));
			}
			break;
		}
		case 'fonctions':{
			$SQLQuery = 'SELECT * FROM fonction';
			break;
		}
	}
	if (!isset($SQLStmt)){
		$SQLStmt = $bdd->prepare($SQLQuery);
	}
	$SQLStmt->execute();
	if ($SQLStmt->rowCount() == 0){
		header('Content-Type: text/html; charset=utf-8');
		print('Aucun enregistrement ne correspond à la demande');
	}else{
		header('Content-Type: application/json; charset=utf-8');
		print(json_encode($SQLStmt->fetchAll(PDO::FETCH_ASSOC)));
	}
} else {
	header('Content-Type: text/html; charset=utf-8');
	print("Erreur de paramètre, attendu : idfamille");
}
?>
