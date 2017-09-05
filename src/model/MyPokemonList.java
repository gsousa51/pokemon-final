package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains all the pokemon the trainer has caught during the game.
 * 
 * @author Brendon
 *
 */
public class MyPokemonList implements Iterable<Pokemon>, Serializable {

	private static MyPokemonList self;
	private ArrayList<Pokemon> pokedex;

	private MyPokemonList() {
		pokedex = new ArrayList<Pokemon>();
	}

	public static synchronized MyPokemonList getInstance() {
		if (self == null)
			self = new MyPokemonList();
		return self;
	}

	public void addPokemon(Pokemon pokemon) {
		pokedex.add(pokemon);
	}

	public int size() {
		return pokedex.size();
	}

	@Override
	public Iterator<Pokemon> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<Pokemon> {

		private int currentIndex;

		public MyIterator() {
			currentIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < pokedex.size();
		}

		@Override
		public Pokemon next() {
			Pokemon pokemon = pokedex.get(currentIndex);
			currentIndex++;
			return pokemon;
		}
	}
}
