package InterfacesAndEnums;

import model.Item;
import model.Pokemon;

public abstract class MapObject {
	
	private boolean isWalkable;
	
	public MapObject(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
	
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
	
	
}
