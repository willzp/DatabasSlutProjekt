package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class main_class {
  public static void main(String[] args) throws SQLException {
	  String constr = "jdbc:mysql://localhost:3306/Movies";
	  Connection conn = DriverManager.getConnection(constr, "root", "!cgh378Ka");
	  
	  genres genreTable = new genres(conn);
	  actors actorTable = new actors(conn);
	  directors directorTable = new directors(conn);
	  movies movieTable = new movies(conn);
	  
	  // Add values in tables
	    genreTable.addGenre("Scifi", "Fantasy");
	    actorTable.addActor("John", "67", "long hair");
	    directorTable.addDirector("Hans", "88", "harvard");
	    movieTable.addMovie("Scifi", "John", "Hans", "one man", "2011");
	  
	  // Update tables
	    genreTable.updateGenre("Fast pace", "Scifi");
	    actorTable.updateActor("77", "John");
	    directorTable.updateDirector("45", "Hans");
	    movieTable.updateMovie("2005", "one man");
	    
	    // delete a movie/genre/actor/director
	      movieTable.removeMovies("one man");
	      genreTable.removeGenres("Scifi");
	      actorTable.removeActors("John");
	      directorTable.removeDirectors("Hans");
	 
	    
	     
	  
	  // Read tables
	  ArrayList<genreBean> genres = genreTable.readGenres();
	  
	  for(int i=0; i<genres.size(); i++) {
		  System.out.println(genres.get(i).toString());
	  }
	  
      ArrayList<actorBean> actors = actorTable.readActors();
	  
	  for(int i=0; i<actors.size(); i++) {
		  System.out.println(actors.get(i).toString());
	  }
	  
      ArrayList<directorBean> directors = directorTable.readDirectors();
	  
	  for(int i=0; i<directors.size(); i++) {
		  System.out.println(directors.get(i).toString());
	  }
	  
      ArrayList<movieBean> movies = movieTable.readMovies();
	  
	  for(int i=0; i<movies.size(); i++) {
		  System.out.println(movies.get(i).toString());
	  }
	  
	  
	  conn.close();
	  
  }
}
