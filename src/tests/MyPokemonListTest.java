package tests;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import model.AvailablePokemonList;
import model.MyPokemonList;
import model.Pokemon;

public class MyPokemonListTest {
	
	AvailablePokemonList list = AvailablePokemonList.getInstance();

	@Test
	public void testAddPokemon() {
		MyPokemonList pokedex = MyPokemonList.getInstance();
		pokedex.addPokemon(list.getPokemon());
		pokedex.addPokemon(list.getPokemon());
		pokedex.addPokemon(list.getPokemon());
		pokedex.addPokemon(list.getPokemon());
		assertEquals(4, pokedex.size());
	}
	
	@Test
	public void testIterator() {
		MyPokemonList pokedex = MyPokemonList.getInstance();
		pokedex.addPokemon(list.getPokemon());
		pokedex.addPokemon(list.getPokemon());
		Iterator<Pokemon> itr = pokedex.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
