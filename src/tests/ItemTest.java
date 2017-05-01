package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HelixFossil;
import model.Item;
import model.Potion;
import model.SafariBall;

public class ItemTest {

	@Test
	public void testItems() {
		Item potion = new Potion();
		Item ball = new SafariBall();
		Item fossil = new HelixFossil();
		
		assertEquals("Restores the HP of a Pokémon by 50 points.", potion.examineMessage());
		assertEquals("Potion", potion.toString());
		assertEquals(20, ((Potion) potion).getHealAmount());
		assertEquals("A special ball that is used only in the Safari Zone.", ball.examineMessage());
		assertEquals("Safari Ball", ball.toString());
		assertEquals("A piece of ancient marine Pokémon's seashell.", fossil.examineMessage());
		assertEquals("Helix Fossil", fossil.toString());
		assertTrue(potion.equals(new Potion()));
		assertTrue(ball.equals(new SafariBall()));
		assertTrue(fossil.equals(new HelixFossil()));
		assertFalse(ball.equals(potion));
		assertFalse(potion.equals(fossil));
		assertFalse(fossil.equals(ball));
	}
}