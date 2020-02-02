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
		String requeteAff="SELECT id, libelle FROM affectation WHERE libelle='" + type + "'";
		try(Connection co = dataSource.getConnection();
				Statement stmt = co.createStatement();
				ResultSet result = stmt.executeQuery(requeteAff)){
			result.next();
			int id = result.getInt("id");
			String libelle = result.getString("libelle");
			Affectation affectation = new Affectation(id,libelle);
			System.out.println(affectation);
			return affectation;

		}
	}
}

