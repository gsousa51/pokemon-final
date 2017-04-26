package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AvailablePokemonList implements Serializable{

	private ArrayList<String> list;
	private static AvailablePokemonList self;

	private AvailablePokemonList() {
		list = new ArrayList<String>();

		// add common pokemon
		for (int i = 0; i < 2; i++) {
			list.add("Nidoran");
			list.add("Paras");
			list.add("Doduo");
			list.add("Venonat");
			list.add("Cubone");
			list.add("Nidorina");
		}

		// add uncommon pokemon
		for (int i = 0; i < 2; i++) {
			list.add("Ryhorn");
			list.add("Exeggcute");
			list.add("Parasect");
		}

		// add rare pokemon
		list.add("Chansey");
	}
	
	public static synchronized AvailablePokemonList getInstance() {
		if (self == null)
			self = new AvailablePokemonList();
		return self;
	}

	/**
	 * Returns a random pokemon from the list
	 * 
	 * @return pokemon
	 */
	public Pokemon getPokemon() {
		Collections.shuffle(list);
		String pokeName = list.get(0);
		switch (pokeName) {
		case "Nidoran":
			return new Pokemon("Nidoran", 41, 235, 55);
		case "Paras":
			return new Pokemon("Paras", 25, 190, 35);
		case "Doduo":
			return new Pokemon("Doduo", 75, 190, 35);
		case "Venonat":
			return new Pokemon("Venonat", 45, 190, 60);
		case "Cubone":
			return new Pokemon("Cubone", 35, 190, 50);
		case "Nidorina":
			return new Pokemon("Nidorina", 56, 120, 70);
		case "Ryhorn":
			return new Pokemon("Ryhorn", 25, 120, 80);
		case "Exeggcute":
			return new Pokemon("Exeggcute", 40, 90, 60);
		case "Parasect": 
			return new Pokemon("Parasect", 30, 75, 60);
		case "Chansey":
			return new Pokemon("Chansey", 50, 30, 250);
		default:
			return null;
		}
	}
}
