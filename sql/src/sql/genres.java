package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class genres {
	private Connection connection;
	private ArrayList<genreBean> genres;
	
	private String selectAllGenres = "select * from Genre";
	private String addGenre = "insert into Genre (genre_name, genre_description) values (?, ?)";
	private String removeGenre = "delete from Genre where genre_name = ?";
	private String updateGenreDescription = "update Genre set genre_description = ? where genre_name = ?";
	
	
	public genres(Connection cn) {
		this.connection = cn;
		this.genres = new ArrayList<genreBean>();
	}
	
	public ArrayList<genreBean> readGenres() {
		
		try (PreparedStatement query = connection.prepareStatement(selectAllGenres)) {
			runQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	
	public void addGenre(String name, String description) {

		try (PreparedStatement query = connection.prepareStatement(addGenre)) {
			query.setString(1, name);
			query.setString(2, description);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeGenres(String genreName) {
		
		try (PreparedStatement query = connection.prepareStatement(removeGenre)) {
			query.setString(1, genreName);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGenre(String description, String genreName) {
		
		try (PreparedStatement query = connection.prepareStatement(updateGenreDescription)) {
			query.setString(1, description);
			query.setString(2, genreName);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
 	private genreBean buildGenre(ResultSet result) {
		genreBean genre = new genreBean();

		try {
			genre.setId(result.getInt("genre_id"));
			genre.setName(result.getString("genre_name"));
			genre.setDescription(result.getString("genre_description"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genre;
	}
	
 	private void buildGenres(ResultSet result) throws SQLException {
 		while(result.next()) {  
			this.genres.add(buildGenre(result));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet result = query.executeQuery()) {
			buildGenres(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}
