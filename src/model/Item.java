package model;

import java.io.Serializable;

/**
 * The Item abstract class
 * 
 * @author Brendon
 *
 */
public abstract class Item implements Serializable{
	
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
	
	public boolean equals(Item item) {
		return this.toString().equals(item.toString());
	}
	
	/**
	 * Returns the message given when examining the item
	 * 
	 * @return examine message
	 */
	abstract public String examineMessage();
}
