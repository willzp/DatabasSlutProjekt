package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class directors {
	private Connection connection;
	private ArrayList<directorBean> directors;
	private String selectAllActors = "select * from directors";
	private String addDirector = "insert into Directors (director_name, age, education) values (?, ?, ?)";
	private String removeDirectors = "delete from Directors where director_name = ?";
	private String updateDirectorAge = "update Directors set age = ? where director_name = ?";
	
	public directors(Connection cn) {
		this.connection = cn;
		this.directors = new ArrayList<directorBean>();
	}
	
	public ArrayList<directorBean> readDirectors() {
		
		try (PreparedStatement query = connection.prepareStatement(selectAllActors)) {
			runQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return directors;
	}
	
public void addDirector(String name, String age, String education) {
		
		try (PreparedStatement query = connection.prepareStatement(addDirector)) {
			query.setString(1, name);
			query.setString(2, age);
			query.setString(3, education);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public void removeDirectors(String directorName) {
	
	try (PreparedStatement query = connection.prepareStatement(removeDirectors)) {
		query.setString(1, directorName);
		query.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
    public void updateDirector(String age, String directorName) {
		
		try (PreparedStatement query = connection.prepareStatement(updateDirectorAge)) {
			query.setString(1, age);
			query.setString(2, directorName);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	private directorBean buildDirector(ResultSet result) {
		directorBean director = new directorBean();

		try {
			director.setId(result.getInt("director_id"));
			director.setName(result.getString("director_name"));
			director.setAge(result.getInt("age"));
			director.setEducation(result.getString("education"));
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return director;
	}
 	
 	private void buildDirectors(ResultSet result) throws SQLException {
 		while(result.next()) {  
			this.directors.add(buildDirector(result));
		}
 	}
	
 
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet result = query.executeQuery()) {
			buildDirectors(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}





