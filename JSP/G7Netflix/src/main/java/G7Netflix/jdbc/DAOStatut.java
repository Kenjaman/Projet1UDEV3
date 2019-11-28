package G7Netflix.jdbc;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import G7Netflix.modele.Affectation;
import G7Netflix.modele.Statut;

public class DAOStatut {

	private DataSource dataSource;

	public DAOStatut(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}

	public List<Statut> getStatuts(Affectation affectation) throws SQLException{
		List<Statut> statuts = new ArrayList<Statut>();    
		String requeteGetSerie ="SELECT s.id, s.libelle FROM statut s "
				+ "INNER JOIN affectation a ON s.idaffectation = a.id "
				+ "WHERE a.id = " + affectation.getId();
		try(Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSerie)){
			while(result.next()) {
				int id = result.getInt("s.id");
				String libelle = result.getString("s.libelle");
				statuts.add(new Statut(id, libelle, affectation));
			}
			return statuts;
		}
	}

	public Statut getStatut(Integer idStatut) throws SQLException{ // A revoir !!
		String requeteGetSerie ="SELECT s.id, s.libelle, s.idaffectation FROM statut s "
				+ "WHERE s.id = "+idStatut;
		try(Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSerie)){
			result.next();
			int id = result.getInt("s.id");
			String libelle = result.getString("s.libelle");
			Statut statut =  new Statut(id, libelle);

			return statut;
		}
	}
	//	public void addSerie(Serie serie) throws SQLException {
	//		String requeteInsertionSerie = "INSERT INTO serie"
	//				+ " (nom, nomoriginal, anneeparution, synopsys, idstatut, idpaysorigine) values (?,?,?,?)";
	//		try(Connection connexion = dataSource.getConnection();
	//				PreparedStatement stmt = connexion.prepareStatement(requeteInsertionSerie, 
	//				PreparedStatement.RETURN_GENERATED_KEYS)){
	//			stmt.setString(1, serie.getNom());
	//			stmt.setString(2, serie.getNomOriginal());
	//			stmt.setInt(3, serie.getAnneeParution());
	//			stmt.setString(4, serie.getSynopsys());
	//			stmt.setInt(5, serie.getStatut().getId());
	//			stmt.setInt(6, serie.getPaysOrigine().getId());
	//			stmt.executeUpdate(requeteInsertionSerie);
	//			serie.setId(extractPrimaryKey(connexion,stmt));
	//			
	//		}
	//	}
	//
	//
	//	private int extractPrimaryKey(Connection connexion, Statement stmt) throws SQLException {
	//		try(ResultSet resultSet = stmt.getGeneratedKeys()) {
	//			if(! resultSet.next()) {
	//				connexion.rollback();
	//				throw new SQLException("Aucune série insérée !");
	//			}
	//			return resultSet.getInt(1);
	//		}
	//	}


}
