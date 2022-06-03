package sql;

public class directorBean {
	private int id;
	private String name;
	private int age;
	private String education;

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

	

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}


	public String toString() {
		String pattern = "Director Name = %s, Age = %d, Education = %s";
		String returnString = String.format(pattern, this.name, this.age, this.education);	

		return returnString;
	}

	
}


