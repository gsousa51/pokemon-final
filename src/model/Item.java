package model;

public abstract class Item {
	
	private String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the item's name.
	 * 
	 * @return item name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the message given when examining the item
	 * 
	 * @return examine message
	 */
	abstract public String examineMessage();
}
