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

public class DAOAffectation {

	private DataSource dataSource;
	
	public DAOAffectation(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}
	
	 public Affectation getAffectation(String type) throws SQLException {
	        String requeteAff="Select id, libelle from affectation where libelle='"+ type+"'";
	        try(Connection co = dataSource.getConnection();
	        		Statement stmt = co.createStatement();
	        			ResultSet result = stmt.executeQuery(requeteAff)){
	        	if(result.next()) {
	        		return new Affectation(result.getInt(1),result.getString(2));
	        	}
	        	
	        }
	        return null;
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
