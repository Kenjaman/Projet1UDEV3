package G7Netflix.jdbc;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import G7Netflix.modele.Public;

public class DAOPublic {

	private DataSource dataSource;
	
	public DAOPublic(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}
	
    public List<Public> getPublics() throws SQLException{
        List<Public> publics = new ArrayList<Public>();    
        String requeteGetPublic ="SELECT id, libelle, limiteage FROM public";
        try(Connection connexion = dataSource.getConnection();
        Statement stmt = connexion.createStatement();
        ResultSet result = stmt.executeQuery(requeteGetPublic)) {
	        while(result.next()) {
	            int id = result.getInt("id");
	            String libelle = result.getString("libelle");
	            int limiteAge = result.getInt("limiteage");
	            publics.add(new Public(id, libelle, limiteAge));
	        }
            return publics;
        }
    }
	
    public Public getPublics(Integer idEpisode) throws SQLException{
    	Public publics;
        String requeteGetPublic ="SELECT p.id, p.libelle, p.limiteage FROM public p "
        		+ "INNER JOIN episode e ON p.idpublic = p.id "
        		+ "WHERE e.id = " + idEpisode;
        try(Connection connexion = dataSource.getConnection();
        		Statement stmt = connexion.createStatement();
            ResultSet result = stmt.executeQuery(requeteGetPublic)){
                int id = result.getInt("id");
                String libelle = result.getString("libelle");
                int limiteAge = result.getInt("limiteage");
                publics = new Public(id, libelle, limiteAge);
            return publics;
        }
            
    }
    
    public Public getPublic(String publicName) throws SQLException {
    	Public publics;
    	String requeteGetPublic = "SELECT p.id, p.libelle, p.limiteage FROM public p WHERE p.libelle = " + publicName;
    	try (Connection connexion = dataSource.getConnection();
    		Statement stmt = connexion.createStatement();
    		ResultSet result = stmt.executeQuery(requeteGetPublic)) {
    			Integer id = result.getInt("id");
    			String libelle = result.getString("libelle");
    			Integer limiteAge = result.getInt("limiteage");
    			publics = new Public(id, libelle, limiteAge);
    		}
    	return publics;
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
