package G7Netflix.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import G7Netflix.modele.Affectation;
import G7Netflix.modele.DonneesInvalidesException;
import G7Netflix.modele.Pays;
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

public class DAOSerie {

	private DataSource dataSource;

	public DAOSerie(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}

	public List<Serie> getSeries() throws SQLException, DonneesInvalidesException {
		List<Serie> series = new ArrayList<Serie>();    
		String requeteGetSerie ="SELECT ser.id, ser.nom, ser.nomoriginal, ser.anneeparution, ser.synopsys, "
				+ "s.id, s.libelle, a.id, a.libelle, p.id, p.nom, p.code FROM serie ser "
				+ "INNER JOIN statut s ON ser.idstatut = s.id "
				+ "INNER JOIN affectation a ON s.idaffectation = a.id "
				+ "INNER JOIN pays p ON ser.idpaysorigine = p.id";
		try(Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSerie)){
			while(result.next()) {
				int id = result.getInt("ser.id");
				String nom = result.getString("ser.nom");
				String nomOriginal = result.getString("ser.nomoriginal");
				int anneeParution = result.getInt("ser.anneeparution");
				String synopsys = result.getString("ser.synopsys");
				Statut statut = new Statut(result.getInt("s.id"), result.getString("s.libelle"),
						new Affectation(result.getInt("a.id"), result.getString("a.libelle")));
				Pays paysOrigine = new Pays(result.getInt("p.id"), result.getString("p.nom"), result.getString("p.code"));
				series.add(new Serie(id, nom, nomOriginal, anneeParution, synopsys, statut, paysOrigine));
			}
			return series;
		}

	}

	public Serie getSerie(Integer id) throws SQLException, DonneesInvalidesException {
		for(Serie serie : this.getSeries()) {
			if(serie.getId()==id)
				return serie;
		}
		return null;
	}

	public void addSerie(Serie serie) throws SQLException {
		String requeteInsertionSerie = "INSERT INTO serie"+ " (nom, nomoriginal, anneeparution, synopsys, idstatut, idpaysorigine) values(?,?,?,?,?,?)";
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteInsertionSerie, 
						PreparedStatement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, serie.getNom());
			stmt.setString(2, serie.getNomOriginal());
			stmt.setInt(3, serie.getAnneeParution());
			stmt.setString(4, serie.getSynopsys());
			stmt.setInt(5, serie.getStatut().getId());
			stmt.setInt(6, serie.getPaysOrigine().getId());
			stmt.executeUpdate();
			serie.setId(extractPrimaryKey(connexion,stmt));
		}
	}
	
	public void updateSerie(Serie serie) throws SQLException{
		
		String requeteUpdateSerie ="UPDATE serie SET nom = ?, nomoriginal = ?, anneeparution = ?, synopsys = ?, idstatut = ?, idpaysorigine = ?"
				+" WHERE id = ?";
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement pstmt = connexion.prepareStatement(requeteUpdateSerie)){
			pstmt.setString(1,serie.getNom());
			pstmt.setString(2, serie.getNomOriginal());
			pstmt.setInt(3, serie.getAnneeParution());
			pstmt.setString(4,serie.getSynopsys());
			pstmt.setInt(5, serie.getStatut().getId());
			pstmt.setInt(6, serie.getPaysOrigine().getId());
			pstmt.setInt(7, serie.getId());
			if(pstmt.executeUpdate()!=0) {
				System.out.println(serie.getNom() + "a bien été mise a jour");
			}else {
				connexion.rollback();
				System.out.println(serie.getNom() + "n'a pas été mise a jour");
			}
		}
	}

	public void deleteSerie(Serie serie) throws SQLException {
		String requeteDeleteSerie = "DELETE serie FROM serie "
				+ "WHERE serie.id = " + serie.getId();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteDeleteSerie, 
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.executeUpdate(requeteDeleteSerie);
		}
	}

	private int extractPrimaryKey(Connection connexion, Statement stmt) throws SQLException {
		try(ResultSet resultSet = stmt.getGeneratedKeys()) {
			if(! resultSet.next()) {
				connexion.rollback();
				throw new SQLException("Aucune série insérée !");
			}
			return resultSet.getInt(1);
		}
	}


}
