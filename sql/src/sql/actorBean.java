package sql;

public class actorBean {
	private int id;
	private String name;
	private String trait;
	private int age;

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

	public void setTrait(String trait) {
		this.trait = trait;
	}

	public String getTrait() {
		return this.trait;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public String toString() {
		String pattern = "Actor Name = %s, Age = %d, Trait = %s";
		String returnString = String.format(pattern, this.name, this.age, this.trait);	

		return returnString;
	}

}
