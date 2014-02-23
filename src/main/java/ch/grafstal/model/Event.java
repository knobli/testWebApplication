package ch.grafstal.model;

import java.util.Set;

public class Event {
	
	private int id;
	private String name;
	private String desc;
	private Member responsible;
	private Location location;
	private Set<Riege> riegen;

	public Event() {
		super();
	}
	
	public Event(String name, String desc, Member responsible, Location location, Set<Riege> riegen) {
		super();
		this.name = name;
		this.desc = desc;
		this.responsible = responsible;
		this.location = location;
		this.riegen = riegen;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Member getResponsible() {
		return responsible;
	}

	public void setResponsible(Member responsible) {
		this.responsible = responsible;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Riege> getRiegen() {
		return riegen;
	}

	public void setRiegen(Set<Riege> riegen) {
		this.riegen = riegen;
	}
	
	@Override
	public String toString() {
		StringBuffer returnStringB = new StringBuffer();
		returnStringB.append(name + ":\n");
		for(Riege riege : riegen){
			returnStringB.append(" -" + riege.getName() + "\n");
		}
		return returnStringB.toString();
	}
	
	
}
