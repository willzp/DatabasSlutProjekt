package sql;

public class movieBean {
	private int id;
	private int genreId;
	private int actorId;
	private int directorId ;
	private String title ;
	private String release;





	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public int getGenreId() {
		return genreId;
	}






	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}






	public int getActorId() {
		return actorId;
	}






	public void setActorId(int actorId) {
		this.actorId = actorId;
	}






	public int getDirectorId() {
		return directorId;
	}






	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}






	public String getTitle() {
		return title;
	}






	public void setTitle(String title) {
		this.title = title;
	}






	public String getRelease() {
		return release;
	}






	public void setRelease(String release) {
		this.release = release;
	}


	public String toString() {
		String pattern = "Movie id = %s, Genre id = %d, Actor id = %s, Director id = %s, Title = %s, Release date = %s" ;
		String returnString = String.format(pattern, this.id, this.genreId, this.actorId, this.directorId, this.title, this.release);	

		return returnString;
	}

}
