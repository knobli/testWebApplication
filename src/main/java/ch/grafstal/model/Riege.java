package ch.grafstal.model;

public class Riege {
	
	private int id;
	private String name;
	
	public Riege() {
		super();
	}
	
	public Riege(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
