package G7Netflix.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import G7Netflix.modele.Saison;

public class DAOSaison {

	private Connection connexion;
	
	public DAOSaison(Connection connection) {
		super();
		this.connexion=connection;
	}
	
	public List<Saison> getSaisons() throws SQLException{
		List<Saison> saisons = new ArrayList<Saison>();	
		String requeteGetSaison ="Select id,"
				+ "numero,resume,annee_diffusion,idstatut,idserie from saison";
		try(Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSaison)){
			while(result.next()) {
				int id = result.getInt("id");
				int numero = result.getInt("numero");
				String resume=result.getString("resume");
				int anneeDiffusion = result.getInt("annee_diffusion");
				int idStatut = result.getInt("idStatut");
				int idSaison = result.getInt("idSerie");
				saisons.add(new Saison(id,numero,resume,anneeDiffusion,idStatut,idSaison));
			}
			return saisons;
		}
			
	}
	
	public void addSaison(Saison saison) throws SQLException {
		String requeteInsertionSaison = "Insert into saison"
				+ " (numero, idstatut, idserie, annee_diffusion) values (?,?,?,?)";
		try(PreparedStatement stmt = connexion.prepareStatement(requeteInsertionSaison, 
				PreparedStatement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1, saison.getNumero());
			stmt.setInt(2, saison.getIdStatut());
			stmt.setInt(3, saison.getIdSerie());
			stmt.setInt(4, saison.getAnneeDiffusion());
			stmt.executeUpdate(requeteInsertionSaison);
			saison.setId(extractPrimaryKey(stmt));
			
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
