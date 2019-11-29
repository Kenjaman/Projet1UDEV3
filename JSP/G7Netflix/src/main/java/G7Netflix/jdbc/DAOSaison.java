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
import G7Netflix.modele.Saison;
import G7Netflix.modele.Serie;
import G7Netflix.modele.Statut;

public class DAOSaison {

	private DataSource dataSource;
	
	public DAOSaison(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}
	
	public List<Saison> getSaisons(Serie serie) throws SQLException, DonneesInvalidesException{
		List<Saison> saisons = new ArrayList<Saison>();	
		String requeteGetSaison ="SELECT sai.id, sai.numero, sai.resume, sai.annee_diffusion, "
				+ "s.id, s.libelle, a.id, a.libelle FROM saison sai "
				+ "INNER JOIN statut s ON sai.idstatut = s.id "
				+ "INNER JOIN affectation a ON s.idaffectation = a.id "
				+ "WHERE idserie = " + serie.getId();
		try(Connection connexion = dataSource.getConnection();
				Statement stmt = connexion.createStatement();
				ResultSet result = stmt.executeQuery(requeteGetSaison)){
			while(result.next()) {
				int id = result.getInt("sai.id");
				int numero = result.getInt("sai.numero");
				String resume=result.getString("sai.resume");
				int anneeDiffusion = result.getInt("sai.annee_diffusion");
				Statut statut = new Statut(result.getInt("s.id"), result.getString("s.libelle"), 
						new Affectation(result.getInt("a.id"), result.getString("a.libelle")));
				saisons.add(new Saison(id, numero, resume, anneeDiffusion, statut, serie));
			}
			return saisons;
		}		
	}

	public Saison getSaison(Integer id, Serie serie) throws SQLException, DonneesInvalidesException {
		for(Saison saison : this.getSaisons(serie)) {
			if(saison.getId()==id)
				return saison;
		}
		return null;
	}

	public void updateSaison(Saison saison) throws SQLException{
		String requeteUpDateSaison = "UPDATE saison SET"
				+ " numero = ?,"
				+ " resumer = ?,"
				+ " annee_diffusion = ?,"
				+ " idstatut = ?,"
				+ " idserie = ?"
				+ " WHERE id = ?";
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement pstmt = connexion.prepareStatement(requeteUpDateSaison)){
			pstmt.setInt(1, saison.getNumero());
			pstmt.setString(2,saison.getResume());
			pstmt.setInt(3, saison.getAnneeDiffusion());
			pstmt.setInt(4, saison.getStatut().getId());
			pstmt.setInt(5, saison.getSerie().getId());
			pstmt.setInt(6, saison.getId());
			if(pstmt.executeUpdate()!=0)
				System.out.println(saison.getNumero() + "a bien été mise a jour");
			else {
				connexion.rollback();
				System.out.println("ca n'a pas marcher");
			}
				
			
		}
	}
	public void addSaison(Saison saison) throws SQLException {
		String requeteInsertionSaison = "INSERT INTO saison"
				+ " (numero, resume, anneediffusion, idstatut, idserie) values (?,?,?,?,?)";
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteInsertionSaison, 
				PreparedStatement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1, saison.getNumero());
			stmt.setString(2, saison.getResume());
			stmt.setInt(3, saison.getAnneeDiffusion());
			stmt.setInt(4, saison.getStatut().getId());
			stmt.setInt(5, saison.getSerie().getId());
			stmt.executeUpdate(requeteInsertionSaison);
			saison.setId(extractPrimaryKey(connexion,stmt));
			
		}
	}

	public void deleteSaison(Saison saison) throws SQLException {
		String requeteDeleteSaison = "DELETE FROM saison, episode "
				+ "INNER JOIN episode ON episode.idsaison = saison.id "
				+ "WHERE saison.id = " + saison.getId();
		try(Connection connexion = dataSource.getConnection();
				PreparedStatement stmt = connexion.prepareStatement(requeteDeleteSaison)){
		stmt.executeUpdate(requeteDeleteSaison);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private int extractPrimaryKey(Connection connexion, Statement stmt) throws SQLException {
		try(ResultSet resultSet = stmt.getGeneratedKeys()) {
			if(! resultSet.next()) {
				connexion.rollback();
				throw new SQLException("Aucune saison insérée !");
			}
			return resultSet.getInt(1);
		}
	}
	
}
