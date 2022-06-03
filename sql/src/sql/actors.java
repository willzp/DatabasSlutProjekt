package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class actors {
	
	private Connection connection;
	private ArrayList<actorBean> actors;
	private String selectAllActors = "select * from actors";
	private String addActor = "insert into Actors (actor_name, age, trait) values (?, ?, ?)";
	private String removeActors = "delete from Actors  where actor_name = ?";
	private String updateActorAge = "update Actors set age = ? where actor_name = ?";
	
	public actors(Connection cn) {
		this.connection = cn;
		this.actors = new ArrayList<actorBean>();
	}
	
	public ArrayList<actorBean> readActors() {
		
		try (PreparedStatement query = connection.prepareStatement(selectAllActors)) {
			runQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actors;
	}
	
	public void addActor(String name, String age, String trait) {
		
		try (PreparedStatement query = connection.prepareStatement(addActor)) {
			query.setString(1, name);
			query.setString(2, age);
			query.setString(3, trait);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeActors(String actorName) {
		
		try (PreparedStatement query = connection.prepareStatement(removeActors)) {
			query.setString(1, actorName);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateActor(String age, String actorName) {
		
		try (PreparedStatement query = connection.prepareStatement(updateActorAge)) {
			query.setString(1, age);
			query.setString(2, actorName);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	private actorBean buildActor(ResultSet result) {
		actorBean actor = new actorBean();

		try {
			actor.setId(result.getInt("actor_id"));
			actor.setName(result.getString("actor_name"));
			actor.setTrait(result.getString("trait"));
			actor.setAge(result.getInt("age"));
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}
	
 	private void buildActors(ResultSet result) throws SQLException {
 		while(result.next()) {  
			this.actors.add(buildActor(result));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet result = query.executeQuery()) {
			buildActors(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}



