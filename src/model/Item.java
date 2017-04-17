package model;

public abstract class Item {
	
	private String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	abstract String examineMessage();
}
