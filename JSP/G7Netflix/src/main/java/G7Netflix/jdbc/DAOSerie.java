package G7Netflix.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import G7Netflix.modele.Pays;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

public class DAOSerie {

	private Connection connexion;
	
	public DAOSerie(Connection connection) {
		super();
		this.connexion=connection;
	}
	
    public List<Serie> getSeries() throws SQLException{
        List<Serie> series = new ArrayList<Serie>();    
        String requeteGetSerie ="SELECT ser.id,"
                + " ser.nom, ser.nomoriginal, ser.anneeparution, ser.synopsys, idstatut, s.libelle, idpaysorigine, p.nom, p.code FROM serie ser"
                + " INNER JOIN statut s ON ser.idstatut = s.id"
                + " INNER JOIN pays p ON ser.idpaysorigine = p.id";
        try(Statement stmt = connexion.createStatement();
            ResultSet result = stmt.executeQuery(requeteGetSerie)){
                int i = 1;
                while(result.next()) {
                    int id = result.getInt("id");
                    String nom = result.getString("nom");
                    String nomOriginal = result.getString("nomoriginal");
                    int anneeParution = result.getInt("anneeparution");
                    String synopsys = result.getString("synopsys");
                    Statut statut = new Statut(result.getInt("idstatut"), result.getString("s.libelle"));
                    Pays paysOrigine = new Pays(result.getInt("idpaysorigine"), result.getString("p.nom"), result.getInt("p.code"));
                    series.add(new Serie(id, nom, nomOriginal, anneeParution, synopsys, statut, paysOrigine));
                }
            return series;
        }
            
    }
	
	public void addSerie(Serie serie) throws SQLException {
		String requeteInsertionSerie = "INSERT INTO serie"
				+ " (nom, nomoriginal, anneeparution, synopsys, idstatut, idpaysorigine) values (?,?,?,?)";
		try(PreparedStatement stmt = connexion.prepareStatement(requeteInsertionSerie, 
				PreparedStatement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, serie.getNom());
			stmt.setString(2, serie.getNomOriginal());
			stmt.setInt(3, serie.getAnneeParution());
			stmt.setString(4, serie.getSynopsys());
			stmt.setInt(5, serie.getStatut().getId());
			stmt.setInt(6, serie.getPaysOrigine().getId());
			stmt.executeUpdate(requeteInsertionSerie);
			serie.setId(extractPrimaryKey(stmt));
			
		}
	}

	public Connection getConnexion() {
		return connexion;
	}
	
	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}

	private int extractPrimaryKey(PreparedStatement stmt) throws SQLException {
		try(ResultSet resultSet = stmt.getGeneratedKeys()) {
			if(! resultSet.next()) {
				connexion.rollback();
				throw new SQLException("Aucune série insérée !");
			}
			return resultSet.getInt(1);
		}
	}

	
}
