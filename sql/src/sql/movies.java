package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class movies {
	
	private Connection connection;
	private ArrayList<movieBean> movies;
	private String selectAllActors = "select * from movies";
	private String addMovie = "INSERT IGNORE INTO Movies VALUES (null, (SELECT genre_id FROM Genre WHERE genre_name = ?),\n"
			+ "           (SELECT actor_id FROM Actors WHERE actor_name =? ),\n"
			+ "           (SELECT director_id FROM Directors WHERE director_name =?),\n"
			+ "           ? ,\n"
			+ "           ? );";
	private String removeMovies = "delete from Movies where title = ?";
	private String updateMovieTitle = "update Movies set release_date = ? where title = ?";
	
	public movies(Connection cn) {
		this.connection = cn;
		this.movies = new ArrayList<movieBean>();
	}
	
	public ArrayList<movieBean> readMovies() {
		
		try (PreparedStatement query = connection.prepareStatement(selectAllActors)) {
			runQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movies;
	}
	
public void addMovie(String genreName, String actorName, String directorName, String title, String releaseDate) {
		
		try (PreparedStatement query = connection.prepareStatement(addMovie)) {
			query.setString(1, genreName);
			query.setString(2, actorName);
			query.setString(3, directorName);
			query.setString(4, title);
			query.setString(5, releaseDate);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
   public void removeMovies(String movietitle) {
	
	try (PreparedStatement query = connection.prepareStatement(removeMovies)) {
		query.setString(1, movietitle);
		query.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
   public void updateMovie(String releaseDate, String title ) {
		
		try (PreparedStatement query = connection.prepareStatement(updateMovieTitle)) {
			query.setString(1, releaseDate);
			query.setString(2, title);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	private movieBean buildMovies(ResultSet result) {
		movieBean movie = new movieBean();

		try {
			movie.setId(result.getInt("movie_id"));
			movie.setGenreId(result.getInt("genre_id"));
			movie.setActorId(result.getInt("actor_id"));
			movie.setDirectorId(result.getInt("director_id"));
			movie.setTitle(result.getString("title"));
			movie.setRelease(result.getString("release_date"));
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movie;
	}
 	
 	private void buildMovie(ResultSet result) throws SQLException {
 		while(result.next()) {  
			this.movies.add(buildMovies(result));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet result = query.executeQuery()) {
			buildMovie(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}




