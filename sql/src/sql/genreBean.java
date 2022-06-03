package sql;

public class genreBean {
	private int id;
	private String name;
	private String description;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		String pattern = "Genre ID = %s, Genre = %s, Description = %s";
		String returnString = String.format(pattern, this.id, this.name, this.description);	

		return returnString;
	}
}
