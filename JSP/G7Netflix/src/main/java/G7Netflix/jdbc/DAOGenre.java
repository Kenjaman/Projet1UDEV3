package G7Netflix.jdbc;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import G7Netflix.modele.Genre;
import G7Netflix.modele.Serie;

public class DAOGenre {

	private DataSource dataSource;
	
	public DAOGenre(DataSource dataSource) {
		super();
		this.dataSource=dataSource;
	}
	
    public List<Genre> getGenres(Serie serie) throws SQLException{
        String requeteGetSerie ="SELECT g.id, g.libelle FROM genre "
        		+ "INNER JOIN appartient a ON a.idgenre = g.id "
        		+ "INNER JOIN serie s ON a.idserie = " + serie.getId();
        try(Connection connexion = dataSource.getConnection();
        		Statement stmt = connexion.createStatement();
            ResultSet result = stmt.executeQuery(requeteGetSerie)){
                while(result.next()) {
                    int id = result.getInt("g.id");
                    String libelle = result.getString("g.libelle");
                    genres.add(new Genre(id, libelle));
                }
            return genres;
        }
            
    }
    
    public List<Genre> getGenres() throws SQLException{
        List<Genre> genres = new ArrayList<Genre>();    
        String requeteGetSerie ="SELECT g.id, g.libelle FROM genre";
        try(Connection connexion = dataSource.getConnection();
        		Statement stmt = connexion.createStatement();
            ResultSet result = stmt.executeQuery(requeteGetSerie)){
                while(result.next()) {
                    int id = result.getInt("g.id");
                    String libelle = result.getString("g.libelle");
                    genres.add(new Genre(id, libelle));
                }
            return genres;
        }
            
    }
    
    public Genre getGenre(Integer id) throws SQLException {
    	for(Genre genre : this.getGenres()) {
    		if(genre.getId()==id)
    			return genre;
    	}
		return null;
    }
	
//	public void addGenre(Genre genre) throws SQLException {
//		String requeteInsertionGenre = "INSERT INTO genre"
//				+ " (libelle) values (?,?,?,?)";
//		try(Connection connexion = dataSource.getConnection();
//				PreparedStatement stmt = connexion.prepareStatement(requeteInsertionGenre, 
//				PreparedStatement.RETURN_GENERATED_KEYS)){
//			stmt.setString(1, genre.getLibelle());
//			stmt.executeUpdate(requeteInsertionGenre);
//			genre.setId(extractPrimaryKey(connexion,stmt));
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
