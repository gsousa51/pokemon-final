package model;

import java.io.Serializable;

public class HelixFossil extends Item implements Serializable{

	public HelixFossil() {
		super("Helix Fossil");
	}

	@Override
	public String examineMessage() {
		return "A piece of ancient marine Pokémon's seashell.";
	}
}
