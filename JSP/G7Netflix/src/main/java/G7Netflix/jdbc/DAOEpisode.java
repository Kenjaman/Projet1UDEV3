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
import G7Netflix.modele.Episode;
import G7Netflix.modele.Public;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Statut;

public class DAOEpisode {

	private DataSource dataSource;

	public DAOEpisode(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<Episode> getEpisodes(Saison saison) throws SQLException {
		List<Episode> episodes = new ArrayList<Episode>();
		String requeteGetEpisode = "SELECT e.id, e.numero, e.titre, e.titreoriginal, "
				+ "e.duree, e.resume, e.daterealisation, e.date_premiere_diffusion, "
				+ "p.id, p.libelle, p.limiteage, s.id, s.libelle, a.id, a.libelle, sai.id, sai.numero, sai.resume"
				+ "INNER JOIN public p ON e.idpublic = p.id"
				+ "INNER JOIN statut s ON e.idstatut = s.id"
				+ "INNER JOIN saison sai ON e.idsaison = " + saison.getId();
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

	public void addEpisode(Episode episode) throws SQLException {
		String requeteInsertionEpisode = "INSERT INTO episode"
				+ " (numero, titre, titreOriginal, duree, resume, dateRealisation, " + 
				"datePremiereDiffusion, idpublic, idstatut, idsaison) VALUES (?,?,?,?,?,?,?,?,?,?)";
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

	public void deleteEpisode(Episode episode) throws SQLException {
		String requeteDeleteEpisode = "DELETE FROM episode "
				+ "WHERE episode.id = " + episode.getId();
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteDeleteEpisode, 
				PreparedStatement.RETURN_GENERATED_KEYS)){
		stmt.executeUpdate(requeteDeleteEpisode);
		}
	}
	
	private int extractPrimaryKey(Connection connexion, Statement stmt) throws SQLException {
		try (ResultSet resultSet = stmt.getGeneratedKeys()) {
			if (!resultSet.next()) {
				connexion.rollback();
				throw new SQLException("Aucune série insérée !");
			}
			return resultSet.getInt(1);
		}
	}

}
