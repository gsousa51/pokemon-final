package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Pokemon;

public class PokemonTest {
	
	@Test
	public void testConstructor() {
		Pokemon mon = new Pokemon("Chansey", 50, 30, 250);
		assertFalse(mon.isAngry());
		assertFalse(mon.isEating());
		assertFalse(mon.isCaught(false));
		assertEquals("Chansey", mon.toString());
	}
	
	@Test
	public void testIsCaught() {
		Pokemon mon = new Pokemon("Chansey", 50, 30, 250);
		assertFalse(mon.isCaught(false));
		mon.hitWithRock(false);
		assertTrue(mon.isAngry());
		assertFalse(mon.isCaught(false));
		mon.hitWithRock(false);
		assertTrue(mon.isCaught(false));
		mon.hitWithBait(false);
		assertFalse(mon.isAngry());
		assertTrue(mon.isEating());
		assertFalse(mon.isCaught(false));
	}
	
	@Test
	public void testDoTurn() {
		Pokemon mon = new Pokemon("Chansey", 50, 30, 250);
		assertFalse(mon.doTurn(false));
		mon.hitWithBait(false);
		assertTrue(mon.doTurn(false));
		assertFalse(mon.doTurn(false));
		mon.hitWithRock(false);
		assertFalse(mon.doTurn(false));
		assertFalse(mon.doTurn(false));
	}
	
	@Test
	public void testHeal() {
		Pokemon mon = new Pokemon("Chansey", 50, 30, 250);
		int[] testHealth = new int[] {187, 250};
		int[] monHealth = mon.getHealth();
		for(int i=0; i<2; i++)
			assertEquals(testHealth[i], monHealth[i]);
		
		testHealth[0] += 20;
		mon.heal(20);
		monHealth = mon.getHealth();
		for(int i=0; i<2; i++)
			assertEquals(testHealth[i], monHealth[i]);
		
		testHealth[0] = 250;
		mon.heal(2000);
		monHealth = mon.getHealth();
		for(int i=0; i<2; i++)
			assertEquals(testHealth[i], monHealth[i]);
	}
	
	@Test
	public void testRandom() {
		Pokemon mon = new Pokemon("Chansey", 50, 30, 250);
		System.out.println(mon.isCaught());
		System.out.println(mon.isCaught());
		System.out.println(mon.isCaught());
		mon.hitWithRock();
		mon.hitWithRock();
		mon.hitWithRock();
		mon.hitWithBait();
		System.out.println("Pokemon escapes: " + mon.doTurn());
		System.out.println("Pokemon is caught: " + mon.isCaught());
		System.out.println("Pokemon escapes: " + mon.doTurn());
		System.out.println("Pokemon is caught: " + mon.isCaught());
		System.out.println("Pokemon escapes: " + mon.doTurn());
		System.out.println("Pokemon is caught: " + mon.isCaught());
		System.out.println("Pokemon escapes: " + mon.doTurn());
		System.out.println("Pokemon is caught: " + mon.isCaught());
		System.out.println("Pokemon escapes: " + mon.doTurn());
	}
}
