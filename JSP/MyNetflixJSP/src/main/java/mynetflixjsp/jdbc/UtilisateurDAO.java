package testJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

	private Connection connexion;

	public UtilisateurDAO(Connection c) throws SQLException {
		this.connexion=c;

	}

	public void desactiverAncienUtilisateur() throws SQLException {
		try(Statement stmt = connexion.createStatement()){
			stmt.executeUpdate("update utilisateur SET actif = 0 where (year(current_date)-10)>year(dateInscription)");
		}
	}

	public List<Utilisateur> getUtilisateurs() throws SQLException{
		List<Utilisateur> listUser = new ArrayList<Utilisateur>();
		String request = "Select * from Utilisateur";
		try (java.sql.Statement stmt = connexion.createStatement();
				java.sql.ResultSet resultSet = stmt.executeQuery(request);) {
			while(resultSet.next()) {
				Utilisateur user = new Utilisateur(resultSet.getInt("id"),
						resultSet.getString("login"),
						resultSet.getDate("dateInscription"),
						resultSet.getBoolean("actif"));
				listUser.add(user);
			}
			return listUser;

		}
	}

	public boolean isAutorise(String login, String droit) throws SQLException {
		String request = "select count(1) from utilisateur u "
				+ "inner join utilisateur_droit ud on u.id = ud.id_utilisateur "
				+ "inner join droit d on ud.id_droit=d.id "
				+ "where u.actif = 1 and d.libelle = ? and u.login = ?";

		try (java.sql.PreparedStatement pstmt = connexion.prepareStatement(request)) {
			pstmt.setString(1, droit);
			pstmt.setString(2, login);
			try(ResultSet resultat = pstmt.executeQuery();){
				resultat.next();
				return resultat.getInt(1)!=0;
			}
		}
	}

	public void addUser(Utilisateur utilisateur, String... noms) throws SQLException {
		boolean isOk=false;
		connexion.setAutoCommit(false);
		String requeteInsertUser = "Insert into Utilisateur(login, dateInscription,actif) values(?,?,?)";
		// Inserer utilisateur
		try(java.sql.PreparedStatement pstmt= connexion.prepareStatement(requeteInsertUser, PreparedStatement.RETURN_GENERATED_KEYS)){
			pstmt.setString(1,utilisateur.getLogin());
			pstmt.setDate(2, new java.sql.Date(utilisateur.getInscription().getTime()));
			pstmt.setBoolean(3, utilisateur.isActif());
			pstmt.executeUpdate();
			try(ResultSet resultSet = pstmt.getGeneratedKeys()){
				if(!resultSet.next()) {
					connexion.rollback();
					return;
				}
				int cle = resultSet.getInt(1);
				utilisateur.setId(cle);
			}


		}
		// Inserer dans utilisateur_droit
		String requeteDroit = "Insert into Utilisateur_Droit (id_utilisateur, id_droit) "
				+ "select ?, d.id from Droit d where d.libelle = ?";
		try(PreparedStatement pstmtdroit = connexion.prepareStatement(requeteDroit)){
			pstmtdroit.setInt(1, utilisateur.getId());
			for(String nomDroit : noms){
				pstmtdroit.setString(2, nomDroit);
				pstmtdroit.executeQuery();
			}
			isOk=true;
			//commit
		}finally {
			if(isOk) {
				connexion.commit();
			}else {
				connexion.rollback();
			}
		}

	}
}
