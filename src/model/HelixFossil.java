package model;

public class HelixFossil extends Item {

	public HelixFossil() {
		super("Helix Fossil");
	}

	@Override
	String examineMessage() {
		return "A piece of ancient marine Pokémon's seashell.";
	}
}
