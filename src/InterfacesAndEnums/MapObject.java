package InterfacesAndEnums;

import model.Item;
import model.Pokemon;

public abstract class MapObject {
	
	private boolean isWalkable;
	private Pokemon pokemon;
	private Item item;
	
	public MapObject(boolean isWalkable, Pokemon pokemon, Item item) {
		this.isWalkable = isWalkable;
		this.pokemon = pokemon;
		this.item = item;
	}
	
	public boolean isWalkable() {
		return isWalkable;
	}
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public Item getItem() {
		return item;
	}
}
