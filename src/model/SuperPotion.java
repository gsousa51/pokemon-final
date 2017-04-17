package model;

public class SuperPotion extends Item{
	
	private int healAmount;

	public SuperPotion() {
		super("Super Potion");
		healAmount = 50;
	}

	@Override
	String examineMessage() {
		return "Restores the HP of a Pokémon by 50 points.";
	}
	
	public int getHealAmount() {
		return healAmount;
	}
}
