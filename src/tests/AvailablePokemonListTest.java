package tests;

import org.junit.Test;

import model.AvailablePokemonList;

public class AvailablePokemonListTest {

	@Test
	public void test() {
		AvailablePokemonList list = AvailablePokemonList.getInstance();
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		list = AvailablePokemonList.getInstance();
		System.out.println();
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
		System.out.println(list.getPokemon());
	}

}
