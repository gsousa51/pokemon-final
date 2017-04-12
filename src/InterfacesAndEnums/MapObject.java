package InterfacesAndEnums;

import model.Item;
import model.Pokemon;

public abstract class MapObject {
	
	private boolean isWalkable;
	
	public MapObject(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
	
	public abstract String toString();
	
	
	public boolean isWalkable() {
		return isWalkable;
	}
	
	/**
	 * Override in grass to remove the pokemon 
	 * when it's caught
	 * 
	 * @author Morgan Henry
	 */
	public void removePokemon()
	{
	}
	
	
	/**
	 * Override in ground to remove the item
	 * when it's found
	 * 
	 * @author Morgan Henry
	 */
	public void removeItem()
	{
	}

	/**
	 * Override in grass to move get the pokemon
	 * 
	 * @author Morgan Henry
	 */
	public Pokemon getPokemon()
	{
	    return null;
	}
}
