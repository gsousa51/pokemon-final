package model;

import java.util.ArrayList;
import java.util.Collections;

public class PokemonList {

	private ArrayList<Pokemon> list;
	private static PokemonList self;

	private PokemonList() {
		list = new ArrayList<Pokemon>();

		// add common pokemon
		for (int i = 0; i < 2; i++) {
			list.add(new Pokemon("Nidoran", 41, 235, 55));
			list.add(new Pokemon("Paras", 25, 190, 35));
			list.add(new Pokemon("Doduo", 75, 190, 35));
			list.add(new Pokemon("Venonat", 45, 190, 60));
			list.add(new Pokemon("Cubone", 35, 190, 50));
			list.add(new Pokemon("Nidorina", 56, 120, 70));
		}

		// add uncommon pokemon
		for (int i = 0; i < 2; i++) {
			list.add(new Pokemon("Ryhorn", 25, 120, 80));
			list.add(new Pokemon("Exeggcute", 40, 90, 60));
			list.add(new Pokemon("Parasect", 30, 75, 60));
		}

		// add rare pokemon
		list.add(new Pokemon("Chansey", 50, 30, 250));
	}
	
	public static synchronized PokemonList getInstance() {
		if (self == null)
			self = new PokemonList();
		return self;
	}

	/**
	 * Returns a random pokemon from the list
	 * 
	 * @return pokemon
	 */
	public Pokemon getPokemon() {
		Collections.shuffle(list);
		return list.get(0);
	}
}
