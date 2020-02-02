package G7Netflix.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import G7Netflix.modele.Affectation;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Episode;
import G7Netflix.modele.Public;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

public class DAOEpisode {

	private DataSource dataSource;

	public DAOEpisode(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Episode> getEpisodes(Saison saison) throws SQLException, DonneesInvalidesException {
		List<Episode> episodes = new ArrayList<Episode>();
		String requeteGetEpisode = "SELECT e.id, e.numero, e.titre, e.titreoriginal, "
				+ "e.duree, e.resume, e.daterealisation, e.date_premiere_diffusion, "
				+ "p.id, p.libelle, p.limiteage, s.id, s.libelle, a.id, a.libelle, sai.id, sai.numero, sai.resume "
				+ "FROM episode e "
				+ "INNER JOIN public p ON e.idpublic = p.id "
				+ "INNER JOIN statut s ON e.idstatut = s.id "
				+ "INNER JOIN affectation a ON s.idaffectation = a.id "
				+ "INNER JOIN saison sai ON e.idsaison = sai.id "
				+ "WHERE idsaison = " + saison.getId();
		try (Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetEpisode)) {
			while (result.next()) {
				int id = result.getInt("e.id");
				int numero = result.getInt("e.numero");
				String titre = result.getString("e.titre");
				String titreOriginal = result.getString("e.titreoriginal");
				int duree = result.getInt("e.duree");
				String resume = result.getString("e.resume");
				Date dateRealisation = result.getDate("e.daterealisation");
				Date datePremiereDiffusion = result.getDate("e.date_premiere_diffusion");
				Public publics = new Public(result.getInt("p.id"), result.getString("p.libelle"), result.getInt("p.limiteage"));
				Statut statut = new Statut(result.getInt("s.id"), result.getString("s.libelle"),
						new Affectation(result.getInt("a.id"), result.getString("a.libelle")));
				episodes.add(new Episode(id, numero, titre, titreOriginal, duree, resume, dateRealisation,
						datePremiereDiffusion, publics, statut, saison));
			}
			return episodes;
		}

	}
	
	public Episode getEpisode(int idEpisode, Saison saison) throws SQLException, DonneesInvalidesException {
		String requeteGetEpisode = "SELECT e.id, e.numero, e.titre, e.titreoriginal, "
				+ "e.duree, e.resume, e.daterealisation, e.date_premiere_diffusion, "
				+ "p.id, p.libelle, p.limiteage, s.id, s.libelle, a.id, a.libelle, sai.id, sai.numero, sai.resume "
				+ "FROM episode e "
				+ "INNER JOIN public p ON e.idpublic = p.id "
				+ "INNER JOIN statut s ON e.idstatut = s.id "
				+ "INNER JOIN affectation a ON s.idaffectation = a.id "
				+ "INNER JOIN saison sai ON e.idsaison = sai.id "
				+ "WHERE e.id = " + idEpisode + " and sai.id = "+saison.getId();
		try (Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetEpisode)) {
			while (result.next()) {
				int id = result.getInt("e.id");
				int numero = result.getInt("e.numero");
				String titre = result.getString("e.titre");
				String titreOriginal = result.getString("e.titreoriginal");
				int duree = result.getInt("e.duree");
				String resume = result.getString("e.resume");
				Date dateRealisation = result.getDate("e.daterealisation");
				Date datePremiereDiffusion = result.getDate("e.date_premiere_diffusion");
				Public publics = new Public(result.getInt("p.id"), result.getString("p.libelle"), result.getInt("p.limiteage"));
				Statut statut = new Statut(result.getInt("s.id"), result.getString("s.libelle"),
						new Affectation(result.getInt("a.id"), result.getString("a.libelle")));
				Episode episode = new Episode(id, numero, titre, titreOriginal, duree, resume, dateRealisation,
						datePremiereDiffusion, publics, statut,saison);
				return episode;
			}
		}
		return null;
		
	}
	
	public void addEpisode(Episode episode) throws SQLException {
		String requeteInsertionEpisode = "INSERT INTO episode"
				+ " (numero, titre, titreOriginal, duree, resume, daterealisation, " + 
				"date_premiere_diffusion, idpublic, idstatut, idsaison) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteInsertionEpisode,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, episode.getNumero());
			stmt.setString(2, episode.getTitre());
			stmt.setString(3, episode.getTitreOriginal());
			stmt.setInt(4, episode.getDuree());
			stmt.setString(5, episode.getResume());
			stmt.setDate(6, episode.getDateRealisation());
			stmt.setDate(7, episode.getDatePremiereDiffusion());
			stmt.setInt(8, episode.getPublics().getId());
			stmt.setInt(9, episode.getStatut().getId());
			stmt.setInt(10, episode.getSaison().getId());
			stmt.executeUpdate(requeteInsertionEpisode);
			episode.setId(extractPrimaryKey(connexion, stmt));

		}
	}
	
	public void updateEpisode(Episode episode) throws SQLException{
		String requeteUpdateEpisode = "UPDATE episode SET "
				+"numero = "+episode.getNumero()+
				", titre = "+episode.getTitre()+
				", titreOriginal = "+episode.getTitreOriginal()+", " 
				+"duree = "+episode.getDuree()+
				", resume = "+episode.getResume()+
				", dateRealisation = "+episode.getDateRealisation()+","
				+"datePremiereDiffusion = "+episode.getDatePremiereDiffusion()+
				", idpublic = "+episode.getPublics().getId()+","
				+"idstatut ="+episode.getStatut().getId()+
				", idsaison = "+episode.getSaison().getId()+","
				+ " WHERE id ="+episode.getId();
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteUpdateEpisode)){
			if(stmt.executeUpdate()!=0) {
				connexion.commit();
				System.out.println(episode.getNumero() + "a bien été mise a jour");
			}else {
				connexion.rollback();
				System.out.println("ca n'a pas marcher");
			}
		}
	}

	public void deleteEpisode(Episode episode) throws SQLException {
		String requeteDeleteEpisode = "DELETE episode FROM episode "
				+ "WHERE episode.id = " + episode.getId();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteDeleteEpisode, 
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.executeUpdate(requeteDeleteEpisode);
		}
	}
	
	public void deleteEpisode(Saison saison) throws SQLException {
		String requeteDeleteEpisode = "DELETE episode FROM episode "
				+ "INNER JOIN saison ON episode.idsaison = saison.id "
				+ "WHERE episode.idsaison = " + saison.getId();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteDeleteEpisode, 
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.executeUpdate(requeteDeleteEpisode);
		}
	}
	
	private int extractPrimaryKey(Connection connexion, Statement stmt) throws SQLException {
		try (ResultSet resultSet = stmt.getGeneratedKeys()) {
			if (!resultSet.next()) {
				connexion.rollback();
				throw new SQLException("Aucune épisode insérée !");
			}
			return resultSet.getInt(1);
		}
	}

	public List<Episode> getAllEpisodes() throws SQLException, DonneesInvalidesException {
		List<Episode> episodes = new ArrayList<Episode>();
		// TODO Auto-generated method stubList<Saison> saisons = new ArrayList<Saison>();	
		String requeteGetSaison ="SELECT ep.id, ep.numero, ep.titre, sai.id, sai.numero, ser.id, ser.nom FROM episode ep "
				+ "INNER JOIN saison sai on sai.id = ep.idsaison "
				+ "INNER JOIN serie ser on sai.idserie = ser.id";
		try(Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSaison)){
			while(result.next()) {
				int idEpisode = result.getInt("ep.id");
				int numeroEp = result.getInt("ep.numero");
				String titreEp=result.getString("ep.titre");
				int idSaison = result.getInt("sai.id");
				int numeroSaison = result.getInt("sai.numero");
				int idSerie= result.getInt("ser.id");
				String nomSerie = result.getString("ser.nom");
				episodes.add(
						new Episode(idEpisode, numeroEp,titreEp,
								new Saison(idSaison,numeroSaison,
										new Serie(idSerie,nomSerie))));
			}
			return episodes;
		}		
	}

}
