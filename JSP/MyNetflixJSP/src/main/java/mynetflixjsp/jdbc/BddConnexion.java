package mynetflixjsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BddConnexion {
	private static final String JDBC_URL="jdbc:mysql://localhost:3306/MyNetflix";
	private static final String LOGIN ="myneflix";
	private static final String PASSWORD = "@dmnetflix";
	
	public static void main(String[] args){
		UtilisateurDAO user;
		try(Connection connexion = DriverManager.getConnection(JDBC_URL,LOGIN,PASSWORD)){
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			user = new UtilisateurDAO(connexion);
			user.desactiverAncienUtilisateur();
			System.out.println(user.isAutorise("Kenjaman", "lecture"));
			System.out.println(user.isAutorise("Spoonless", "lecture"));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

