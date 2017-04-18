package model;

public class Potion extends Item{
	
	private int healAmount;

	public Potion() {
		super("Potion");
		healAmount = 20;
	}

	@Override
	public String examineMessage() {
		return "Restores the HP of a Pokémon by 50 points.";
	}
	
	/**
	 * Returns the amount this item heals
	 * 
	 * @return heal amount
	 */
	public int getHealAmount() {
		return healAmount;
	}
}
